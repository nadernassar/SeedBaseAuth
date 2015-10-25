package edu.pace.csis.phd.sbauth.login;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;

import edu.pace.csis.phd.sbauth.model.Token;

public class Login {


     public static void  Identify(Long URLToken, Long UNMToken, int seqNum ) throws IOException, URISyntaxException{
    	 String METHODNAME="Login.Identify";
    	 	ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(getBaseURI());
		   
			 
		    Token token = new Token(URLToken,UNMToken,seqNum);
		    String authChallenge =   target.path("rest").path("token")
		    		.path(token.getURLToken())
		    		.path(token.getUNMToken())
		    		.path(token.getSeq())
		    		.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		    System.out.println("@" +METHODNAME+": "+authChallenge);
		    
		  
			 
		   Authenticate.GeneratePassword(authChallenge);
		    
		  // System.out.println("Debug2..."+target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(String.class));
		//    System.out.println(  target.path("rest").path("token")		    		.path(token.getURLToken())		    		.path(token.getUNMToken())		    		.path(token.getSeq())		    		.request()		    		.accept(MediaType.TEXT_PLAIN)		    		.get(String.class));

		    
		/*
		 *     Response res = target.path("rest").path("token")
		 
		    		.path(token.getURLToken())
		    		.path(token.getUNMToken())
		    		.path(token.getSeq())
		    		.request().accept(MediaType.TEXT_PLAIN).get(Response.class);	 
		    System.out.println("response.location  >>  "+ res.getLocation());
		    		//   .path(todo.getId()).accept(MediaType.APPLICATION_XML)	        .put(ClientResponse.class, todo);
		    */
		 }

		  private static URI getBaseURI() {

		    return UriBuilder.fromUri("http://localhost:8080/SeedBaseAuth").build();

		  }
			/**
			 * @param args
			 * @throws URISyntaxException 
			 * @throws IOException 
			 */
			public static void main(String[] args) throws IOException, URISyntaxException {
				 
		     Long URLToken = 1111L;
		     Long UNMToken = 2222L;
		     int SEQ = 0;
		     Identify(URLToken,UNMToken,SEQ);
		     
			}

}
