package com.eyequeue.lolabilities;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myfriend")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/{friend}")
    @Produces(MediaType.TEXT_HTML)
    public String getIt(@PathParam("friend") String friend) {
    	if(friend.equals("jfire")) {
    		return "<!DOCTYPE html>\r\n" + 
    				"<html>\r\n" + 
    				"<head>\r\n" + 
    				"<title>My Friend</title>\r\n" + 
    				"</head>\r\n" + 
    				"<body>\r\n" + 
    				"\r\n" + 
    				"<h1>J ANIKI(IHUG)</h1>\r\n" + 
    				"\r\n" + 
    				"</body>\r\n" + 
    				"</html>";
    	}
    	else if(friend.equals("kawaii")) {
    		return "<!DOCTYPE html>\r\n" + 
    				"<html>\r\n" + 
    				"<head>\r\n" + 
    				"<title>My Friend</title>\r\n" + 
    				"</head>\r\n" + 
    				"<body>\r\n" + 
    				"\r\n" + 
    				"<h1>KAWAII KYUUUN(HUG)</h1>\r\n" + 
    				"\r\n" + 
    				"</body>\r\n" + 
    				"</html>";
    	}
    	else {
    		return "You are not my friend!";
    	}
    }
}
