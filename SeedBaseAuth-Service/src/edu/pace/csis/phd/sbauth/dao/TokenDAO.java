package edu.pace.csis.phd.sbauth.dao;

import java.sql.SQLException;

import edu.pace.csis.phd.sbauth.model.Token;
import edu.pace.csis.phd.sbauth.server.query.Queries;

public class TokenDAO {
	public TokenDAO(){}
	
	public static void insertToken(Token token){
		
		try {
			Queries.InsertTokens(token.getURLToken(), token.getUNMToken(), token.getSeq(),token.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Token getTokenRecord(Long urlToken,Long unmToken, int seq ){
		Token token = null;
		return token;
	}
	
	public static int FindUserByTokens(Token token ){
		final String METHODNAME="FindUserByTokens"; 
		
		System.out.println(METHODNAME+" Entry  >> " );
		int userId = 0 ;
		try {
			System.out.println( METHODNAME+" DEBUG #2 >> " );
			Queries.ConnectionOpen();		
			
			System.out.println(METHODNAME+ " DEBUG #3 >> " );
			 userId = Queries.FindUserByTokens(token.getURLToken(), token.getUNMToken(), token.getSeq());
			
			 System.out.println(METHODNAME+ " DEBUG #4 >> " );
			 Queries.ConnectionClose();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(METHODNAME+" Exit  >> " );
		return userId;
	}
public static void expireTokens(Token token){
	try {
		Queries.ExpireTokens(token.getURLToken(), token.getUNMToken(), token.getSeq());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
