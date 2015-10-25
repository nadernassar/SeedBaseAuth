package edu.pace.csis.phd.sbauth.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import edu.pace.csis.phd.sbauth.dao.SecretDAO;
import edu.pace.csis.phd.sbauth.dao.SeedDAO;
import edu.pace.csis.phd.sbauth.dao.TokenDAO;
import edu.pace.csis.phd.sbauth.model.Secret;
import edu.pace.csis.phd.sbauth.model.Seed;
import edu.pace.csis.phd.sbauth.model.Token;
import edu.pace.csis.phd.sbauth.server.*;
import edu.pace.csis.phd.sbauth.server.generator.TokenGenerator;
import edu.rit.util.Random;

@Path("/token/{urltoken}/{unmtoken}/{seq}")
@Consumes(MediaType.APPLICATION_XML)
public class TokenResource   {

	/**
	 * 
	 * @param urltoken
	 * @param unmtoken
	 * @param seq
	 * @return
	 *  */
	
	
	//Application integration     
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String IdentifyUser(@PathParam("urltoken") String urltoken, @PathParam("unmtoken") String unmtoken, @PathParam("seq")  String seq ) {
		
		Token token = new Token (new Long(urltoken), new Long(unmtoken), new Integer (seq).intValue());
		int userId=TokenDAO.FindUserByTokens(token);
		System.out.println("IdentifyUser UID IS 00> "+ userId);
		
		if(userId==0){
			return "Hello Jersey" + "user NOT found; user ID is "+ userId ;
		
		}else{
			System.out.println("IdentifyUser UID IS FOUND> "+ userId);
			
			token.setUserId(userId);
			ProcessToken(token);		
			String authChallenge= InitAuthentication(token);
			//authChallenge = "u15|m100|m322|u6097";
		System.out.println("Hello Jersey" + "user found; user ID is "+ userId + "  Starting authtication now..."+authChallenge );
			return authChallenge;
		 
		}
	}
	
	private String InitAuthentication(Token token) {
		final String METHODNAME="InitAuthentication";
		
		//Generate four random seq number
		// Generate challenge Value and store in Secrets db with challenge and user Id
		// send ONLY the  challenge  back to client to generate tokens.
		// Secret record will be expired if no response was received after 60 sec or correct challenge value was received from the client and validated
		// TODO Auto-generated method stub
		
		 final String  URLTOKEN ="U";
		 final String  UNMToken ="M";
		 final String  PIPE="|";
		 String authChallenge = null;
		 String secretValue=null;
		 int tokenPeriod = (int) Math.pow(2,64);
		 java.util.Random randomGenerator = new  java.util.Random();
		 int rnd1=randomGenerator.nextInt(tokenPeriod);
		 int rnd2= randomGenerator.nextInt(tokenPeriod);
		 int rnd3=randomGenerator.nextInt(tokenPeriod);
		 int rnd4=randomGenerator.nextInt(tokenPeriod);
		 Seed seed = SeedDAO.FindSeedByUserId(token.getUserId());

		 StringBuffer sb = new StringBuffer();
		 sb.append(TokenGenerator.GenerateRandomToken(seed.getURLSeed(), rnd1));
		 sb.append(TokenGenerator.GenerateRandomToken(seed.getUNMSeed(), rnd2));
		 sb.append(TokenGenerator.GenerateRandomToken(seed.getUNMSeed(), rnd3));
		 sb.append(TokenGenerator.GenerateRandomToken(seed.getURLSeed(), rnd4));
		 secretValue=sb.toString();
				 
		 authChallenge= rnd1+PIPE+
				 		rnd2+PIPE+
				 		rnd3+PIPE+
				 		rnd4;
		 System.out.println("@"+METHODNAME+"AUTHCHALLENGE IS :"+URLTOKEN+rnd1+PIPE+	UNMToken+rnd2+PIPE+	UNMToken+rnd3+PIPE+	URLTOKEN+rnd4);
		 Secret secret = new Secret(secretValue,token.getUserId()	);
		 SecretDAO.ExpireSecretbyUserId(token.getUserId());
		 SecretDAO.InsertSecret(secret);
		 System.out.println(METHODNAME+"authChallenge is " + authChallenge + " secretValue is " + secretValue);
		 
		return authChallenge;
	}

	/**
	 *  User Identified , return user ID
	 *  Expire tokens, 
	 *  generate new tokens for the nextSeqNum
	 *   insert save token 
	 *   
	 *   			
	 * @param token
	 */
	  public static  void ProcessToken(Token token) {
		// TODO Auto-generated method stub
		final String METHODNAME="ProcessToken";
		System.out.println(METHODNAME+ " Entry >> " );
		 
		   System.out.println( METHODNAME+" Generating NextTokens> " );
		   Token nextToken =TokenGenerator.GenerateNextTokens(token);			
			
			System.out.println(  METHODNAME+" Expiring current Token DEBUG # 2 >> " );
			TokenDAO.expireTokens(token);
			
			System.out.println(METHODNAME+ " Inserting next Token DEBUG # 3 >> " );
			TokenDAO.insertToken(nextToken);
			
			 System.out.println(METHODNAME+ " Exit >> " );
		
	}

		

	@GET
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Token getToken() {
		  Token token = null;
		  /*
		   * 
	    Token token = TokenDAO.instance.getModel().get(tokenid);
	    if(token==null)
	      throw new RuntimeException("Get: Token with " + id +  " not found");
	    return token;
	      */
		  //token = new Token(urlToken,unmToken,seq);
	    return  token;
	  }
	  
	  // for the browser
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public Token getTokenHTML() {
		  /*
	    Token token = TokenDAO.instance.getModel().get(id);
	    if(token==null)
	      throw new RuntimeException("Get: Token with " + id +  " not found");
	    return token;
	      */
	    return getToken();
	  }
	  
	  @PUT
	  @Consumes(MediaType.APPLICATION_XML)
	  public Response putToken(JAXBElement<Token> token) {
	    Token c = token.getValue();
	    return putAndGetResponse(c);
	  }
	  
	  @DELETE
	  public void deleteToken() {
		  /*
	    Token c = TokenDAO.instance.getModel().remove(id);
	    if(c==null)
	      throw new RuntimeException("Delete: Token with " + id +  " not found");
	      */
	  }
	  
	  
	  private Response putAndGetResponse(Token token) {
		  final String METHODNAME="putAndGetResponse";
		  System.out.println(METHODNAME+ " Entry >> " );
	    Response res = null;
	    /**
	    if(TokenDAO.instance.getModel().containsKey(token.getUNMToken())) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    **/
	   // TokenDAO.instance.getModel().put(token.getUNMToken(), token);
	    
	     res.status(Response.Status.OK)
                
                .entity("The podcast you specified has been fully updated created AT THE LOCATION you specified")
                .entity(token)
                .header("Location", "http://localhost:8888/demo-rest-jersey-spring/podcasts/"
                                + String.valueOf("foo")).build();
	    System.out.println(METHODNAME+ " Exit >> " );
	    return res;
	  }

}
