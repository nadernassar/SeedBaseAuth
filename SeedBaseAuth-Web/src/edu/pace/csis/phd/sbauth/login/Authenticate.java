package edu.pace.csis.phd.sbauth.login;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import edu.pace.csis.phd.sbauth.generator.TokenGenerator;

import org.glassfish.jersey.client.ClientConfig;

public class Authenticate  implements AuthenticateImpl {
	public  static  Long urlSeed, unmSeed = 0L;
	
	public Authenticate(){ 
		
	}
	public static void GeneratePassword(String authChallenge ) throws IOException, URISyntaxException{
		final String METHODNAME="Authenticate.GeneratePassword";
		final  String PIPE ="|";
		StringTokenizer st = new StringTokenizer(authChallenge, PIPE ); 
		int rnd1= (new Integer(st.nextToken())).intValue();
		int rnd2= (new Integer(st.nextToken())).intValue();
		int rnd3= (new Integer(st.nextToken())).intValue();
		int rnd4= (new Integer(st.nextToken())).intValue();
		
		
		 StringBuffer sb = new StringBuffer();
		 sb.append(TokenGenerator.GenerateRandomToken(getUrlSeed(), rnd1));
		 sb.append(TokenGenerator.GenerateRandomToken(getUnmSeed(), rnd2));
		 sb.append(TokenGenerator.GenerateRandomToken(getUnmSeed(), rnd3));
		 sb.append(TokenGenerator.GenerateRandomToken(getUrlSeed(), rnd4));
		 
		 String password =sb.toString();
				 
		  System.out.println("@"+METHODNAME+":"+"About to submit Password:"+ password);
		  SubmitPassword(password);		 
		 
	 }
	
	/**
	 * 
	 * @param pw
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	private static void SubmitPassword(String pw ) throws IOException, URISyntaxException{
		/*
		 * ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI());
	
		// Response rsp =   target.path("rest").path("password").path(pw).request().accept(MediaType.TEXT_PLAIN).get(Response.class);
		//    System.out.println(rsp.toString());
	    // String landingPage = target.path("rest").path("password").path(pw).request().accept(MediaType.TEXT_PLAIN).get(String.class);
	    
	    //String landingPage =   target.path("rest").path("password").path(pw).toString();
	    
		    Response response =   target.path("rest").path("password").path(pw).request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		    System.out.println(response.toString());
		    if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
				}else{
		    String landingPage = "http://localhost:8080/SeedBaseAuth/Login.jsp?";
		    URLRedirect(landingPage);	
			
				}
				*/
	    String url="http://localhost:8080/SeedBaseAuth/servlets/RedirectURL?pw="+pw;
	    	 if (Desktop.isDesktopSupported()) {
		        	System.out.println("@ URLRedirect Client Windows, url is >>>" + url);
		        	
		            // Windows
		            Desktop.getDesktop().browse(new URI(url));
		            
		        } else {
		            // Ubuntu
		            Runtime runtime = Runtime.getRuntime();
		            runtime.exec("/usr/bin/firefox -new-window " + url);
		        }
		    System.out.println("@ SubmitPasword: EXIT");
	}
	
	/**
	 * 
	 * @param landingPage
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	private static void URLRedirect(String landingPage ) throws IOException, URISyntaxException{
		 System.out.println("@ URLRedirect Client");
		 String url = landingPage;

	        if (Desktop.isDesktopSupported()) {
	        	System.out.println("@ URLRedirect Client Windows, url is >>>" + url);
	        	
	            // Windows
	            Desktop.getDesktop().browse(new URI(url));
	            
	        } else {
	            // Ubuntu
	            Runtime runtime = Runtime.getRuntime();
	            runtime.exec("/usr/bin/firefox -new-window " + url);
	        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 ClientConfig config = new ClientConfig();

		    Client client = ClientBuilder.newClient(config);

		    WebTarget target = client.target(getBaseURI());

		 //  System.out.println("Debug1..."+target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(Response.class).toString());
		    System.out.println("Debug1..."+target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(Response.class));

		    System.out.println("Debug2..."+target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(String.class));

		    System.out.println("Debug3..."+target.path("rest").path("hello").request().accept(MediaType.TEXT_XML).get(String.class));

		    System.out.println("Debug4..."+target.path("rest").path("hello").request().accept(MediaType.TEXT_HTML).get(String.class));

		  }

		  private static URI getBaseURI() {

		    return UriBuilder.fromUri("http://localhost:8080/SeedBaseAuth").build();

		  }

		  public  static Long getUrlSeed() {
				return urlSeed;
			}

			public static void setUrlSeed(Long s) {
				urlSeed = s;
			}

			public static  Long getUnmSeed() {
				return unmSeed;
			}

			public static void setUnmSeed(Long n) {
				unmSeed = n;
			}
			


}
