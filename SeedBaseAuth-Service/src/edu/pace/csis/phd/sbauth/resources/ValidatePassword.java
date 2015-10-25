package edu.pace.csis.phd.sbauth.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.pace.csis.phd.sbauth.dao.SecretDAO;
import edu.pace.csis.phd.sbauth.model.Secret;
import edu.pace.csis.phd.sbauth.servlets.RedirectURL;



	@Path("/password/{password}")
	@Consumes(MediaType.APPLICATION_XML)
	public class ValidatePassword   {
		public ValidatePassword(){}

		/**
		 * 
		 * @param password
		 * @return
		 */
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Secret  ValidateParameter(@PathParam("password") String password ) {
			String METHODNAME ="ValidateParameter";
			
			System.out.println(METHODNAME+":.. Password is .." +password);
			
			String rsp=null;
			Secret secret = new Secret(password);
			int userId = 0;
			userId = SecretDAO.FindUserByChallenge(password);
			if(userId==0){
				rsp=  "Hello Jersey" + "user NOT found; user ID is "+ userId;
			
			}else{
				rsp=  "Hello Jersey" + "VALIDATED, user FOUND found; user ID is "+ userId;
				secret.setUserId(userId);
				SecretDAO.ExpireSecret(password);
			}
			
			System.out.println(METHODNAME+":  "+rsp);
			//RedirectURLImpl  redirect= new RedirectURLImpl() ;
			
			
			/////////////////////////////////////////////////////
		    /*

			URL gwtServlet = null;
		    try {
		    	//gwtServlet = new URL("http://localhost:8080/SeedBaseAuth/Login.jsp");
		    	 gwtServlet = new URL("http://localhost:8080/SeedBaseAuth/servlets/RedirectURL");
		    	// gwtServlet = new URL("RedirectURL");
		        HttpURLConnection servletConnection = (HttpURLConnection) gwtServlet.openConnection();
		        servletConnection.setRequestMethod("GET");
		        servletConnection.setDoOutput(true);
		    
		        ObjectOutputStream objOut = new ObjectOutputStream(servletConnection.getOutputStream());
		        objOut.writeObject(secret);
		        objOut.flush();
		        objOut.close();
		    
		        InputStream response = servletConnection.getInputStream();
		         
		    } catch (MalformedURLException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		       */
			return secret;
		}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
