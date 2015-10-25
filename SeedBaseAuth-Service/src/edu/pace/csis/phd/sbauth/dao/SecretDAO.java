package edu.pace.csis.phd.sbauth.dao;

import java.sql.SQLException;

import edu.pace.csis.phd.sbauth.model.Secret;
import edu.pace.csis.phd.sbauth.model.Token;
import edu.pace.csis.phd.sbauth.server.query.Queries;

public class SecretDAO {
	public SecretDAO(){}
	
	public static void InsertSecret(Secret secret){
		
		try {
			Queries.InsertSecret(secret);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String  FindSecretbyUserId(int userId ) throws SQLException{
		String secret = Queries.FindSecretbyUserId(userId);
		return secret;
	}
	
	public static int FindUserByChallenge(String  challenge ){
		final String METHODNAME="FindUserBySecret"; 
		
		System.out.println(METHODNAME+" Entry  >> " );
		int userId = 0 ;
		try {
			System.out.println( METHODNAME+" DEBUG #2 >> " );
			Queries.ConnectionOpen();		
			
			System.out.println(METHODNAME+ " DEBUG #3 >> " );
			 userId = Queries.FindUserByChallenge(challenge);
			
			 System.out.println(METHODNAME+ " DEBUG #4 >> " );
			 Queries.ConnectionClose();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(METHODNAME+" Exit  >> " );
		return userId;
	}
public static void ExpireSecret(String challenge ){
	try {
		Queries.ExpireSecretByChallenge(challenge );
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public static void ExpireSecretbyUserId(int userId){
		try {
			Queries.ExpireSecretbyUserId(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
}
}
