package com.eyequeue.lolabilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.eyequeue.lolabilities.model.AbilityType;
import com.eyequeue.lolabilities.model.AllData;
import com.eyequeue.lolabilities.model.ChampImage;
import com.eyequeue.lolabilities.model.ChampionRecord;
import com.eyequeue.lolabilities.util.Variables;
import com.google.gson.Gson;

@Path("data")
public class GetData {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllData(@QueryParam("timestamp") String timestamp) {
		AllData allData = new AllData();
		Gson gson = new Gson();
		try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Variables.MYSQL_URL, Variables.MYSQL_USER, Variables.MYSQL_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id, name, details, created_at, last_updated_at from ability_type where last_updated_at > " + timestamp + ";");
            List<AbilityType> abilityTypes = new ArrayList<>();
            while (rs.next()){
                AbilityType abilityType = new AbilityType();
                abilityType.setId(rs.getInt("id"));
                abilityType.setName(rs.getString("name"));
                abilityType.setDetails(rs.getString("details"));
                abilityType.setCreatedAt(rs.getLong("created_at"));
                abilityType.setLastUpdatedAt(rs.getLong("last_updated_at"));
                abilityTypes.add(abilityType);
            }
            rs = statement.executeQuery("select id, name, image, details, created_at, last_updated_at from champ where last_updated_at > " + timestamp + ";");
            List<ChampionRecord> champs = new ArrayList<>();
            while (rs.next()){
                ChampionRecord champ = new ChampionRecord();
                champ.setId(rs.getInt("id"));
                champ.setName(rs.getString("name"));
                champ.setImage(rs.getString("image"));
                champ.setDetails(rs.getString("details"));
                champ.setCreatedAt(rs.getLong("created_at"));
                champ.setLastUpdatedAt(rs.getLong("last_updated_at"));
                champs.add(champ);
            }
            
            rs = statement.executeQuery("select id, `type`, image, created_at, last_updated_at from images where last_updated_at > " + timestamp + ";");
            List<ChampImage> champImages = new ArrayList<>();
            while (rs.next()){
                ChampImage champImage = new ChampImage();
                champImage.setId(rs.getInt("id"));
                champImage.setType(rs.getString("type"));
                champImage.setImage(rs.getString("image"));
                champImage.setCreatedAt(rs.getLong("created_at"));
                champImage.setLastUpdatedAt(rs.getLong("last_updated_at"));
                champImages.add(champImage);
            }
            String patch = "";
            String imagesLink = "";
            rs = statement.executeQuery("select id, `name`, `value`, created_at, last_updated_at from variable;");
            while(rs.next()) {
            	if(rs.getString("name").equals("patch")) {
            		patch = rs.getString("value");
            	}
            	else if(rs.getString("name").equals("images_link")) {
            		imagesLink = rs.getString("value");
            	}
            }
            
            rs = statement.executeQuery("select max(last_updated_at) as 'timestamp' from (select last_updated_at from champ union select last_updated_at from ability_type union select last_updated_at from variable) t;");
            long lastUpdated = 0;
            while(rs.next()) {
            	lastUpdated = rs.getLong("timestamp");
            }
            rs.close();
            statement.close();
            con.close();

            allData.setAbilityTypes(abilityTypes);
            allData.setChampions(champs);
            allData.setImages(champImages);
            allData.setPatch(patch);
            allData.setImagesLink(imagesLink);
            allData.setLastUpdated(lastUpdated);
            
            String abilityTypesJson = gson.toJson(abilityTypes);
            System.out.println(abilityTypesJson);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
		return gson.toJson(allData);
	}
	
	@POST
	@Path("champ")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String editChamp(String champStr) {
		ChampionRecord champ = new Gson().fromJson(champStr, ChampionRecord.class);
		try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Variables.MYSQL_URL, Variables.MYSQL_USER, Variables.MYSQL_PASSWORD);
            PreparedStatement stmt = con.prepareStatement("update champ set details = ?, last_updated_at = ? where id = ?");
            stmt.setString(1, champ.getDetails());
            stmt.setLong(2, System.currentTimeMillis());
            stmt.setInt(3, champ.getId());
            stmt.executeUpdate();
            stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "[]";
	}

}
