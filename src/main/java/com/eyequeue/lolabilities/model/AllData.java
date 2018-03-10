package com.eyequeue.lolabilities.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AllData {
	
	List<ChampionRecord> champions;
	List<AbilityType> abilityTypes;
    List<ChampImage> images;
    String patch;
    @SerializedName("images_link")
    String imagesLink;
	long lastUpdated;
	
	public List<ChampionRecord> getChampions() {
		return champions;
	}
	public void setChampions(List<ChampionRecord> champions) {
		this.champions = champions;
	}
	public List<AbilityType> getAbilityTypes() {
		return abilityTypes;
	}
	public void setAbilityTypes(List<AbilityType> abilityTypes) {
		this.abilityTypes = abilityTypes;
	}

    public List<ChampImage> getImages() {
        return images;
    }

    public void setImages(List<ChampImage> images) {
        this.images = images;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getImagesLink() {
        return imagesLink;
    }

    public void setImagesLink(String imagesLink) {
        this.imagesLink = imagesLink;
    }

    public long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	

}
