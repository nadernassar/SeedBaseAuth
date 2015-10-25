package edu.pace.csis.phd.sbauth.server.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.pace.csis.phd.sbauth.model.Secret;
import edu.pace.csis.phd.sbauth.model.Seed;

public class Queries{
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/sbauth";

	   //  Database credentials
	    static final String USER = "root";
	   	static final String PASS = "";	   
	    static final String COMMA=",";
	    static final int EXPIRED = 1;
	    private static String sql = null;
	   	
	    public static Connection getConn() {
			return conn;
		}
		public static void setConn(Connection conn) {
			Queries.conn = conn;
		}
		public static Statement getStmt() {
			return stmt;
		}
		public static void setStmt(Statement stmt) {
			Queries.stmt = stmt;
		}

		static  Connection conn = null;
	    static Statement stmt = null;
	   
	   public static void ConnectionOpen() {
	
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
 
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      setConn( DriverManager.getConnection(DB_URL,USER,PASS));

	      System.out.println("Creating statement...");
	      setStmt(conn.createStatement());
	     
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	   
	}//end main
	/**
	 * 
	 * @param URLToken
	 * @param UNMToken
	 * @param Seq
	 * @param SeedId
	 * @throws SQLException 
	 */
	public static void InsertTokens(Long URLToken, Long UNMToken, int Seq, int UserId) throws SQLException{
		ConnectionOpen();
		     
	      sql = "INSERT INTO TOKENS (URLTOKEN, UNMTOKEN, SEQUENCE, USERID, EXPIRED)  VALUES (" +
	      URLToken+COMMA+
	      UNMToken+COMMA+
	      Seq+COMMA+
	      UserId+COMMA+
	      0+")"
	      ;
	      System.out.println("Executing the following statement >> " + sql);
	      getStmt().executeUpdate(sql);

	      System.out.println("Sucessfully Inserting to Tokens table");
	  	ConnectionClose();
		
	}
	/**
	 * 
	 * @param URLSeed
	 * @param UNMSeed
	 * @throws SQLException 
	 */
	
public static void InsertSeeds(Long URLSeed, Long UNMSeed) throws SQLException{
	ConnectionOpen();
	sql = "INSERT INTO SEEDS (URLSEED,UNMSEED, EXPIRED) VALUES (" +
      URLSeed+COMMA+
      UNMSeed+COMMA+
      0+")"
      ;
      System.out.println("Executing the following statement >> " + sql);
      getStmt().executeUpdate(sql);

      System.out.println("Sucessfully Inserting to Seeds table");
  	ConnectionClose();
	}
/**
 * 
 * @param URLToken
 * @param UNMToken
 * @param Seq
 * @throws SQLException 
 */

public static int FindUserByTokens(Long URLToken, Long UNMToken, int Seq) throws SQLException{
	ConnectionOpen();
	sql= "SELECT USERID FROM TOKENS WHERE URLToken = "+ URLToken+" AND UNMToken = "+ UNMToken +" and SEQUENCE = " +Seq +" and EXPIRED <>" + EXPIRED   ;
	 System.out.println("Executing the following statement >> " + sql);

	ResultSet rs = getStmt().executeQuery(sql);
	int UID =0;
	while (rs.next()){
	UID= rs.getInt("USERID");
	}
	rs.close();
	ConnectionClose();
	return UID;
	
}
public static int FindUserBySeeds(Long URLSeed, Long UNMSeed) throws SQLException{
	ConnectionOpen();
	sql= "SELECT USERID FROM SEEDS WHERE URLSeed ="+ URLSeed+" AND UNMSeed ="+ UNMSeed   ;
	 System.out.println("Executing the following statement >> " + sql);
	ResultSet rs = getStmt().executeQuery(sql);
	
	int UID =0;
	while (rs.next()){
	UID= rs.getInt("USERID");
	}
	rs.close();
	ConnectionClose();
	return UID;
	
}
public static Seed  FindSeedByUserId(int userid) throws SQLException{
	Seed seed = new Seed();
	ConnectionOpen();
	sql= "SELECT URLSEED, UNMSEED FROM SEEDS WHERE USERID = "+ userid   ;
	 System.out.println("Executing the following statement >> " + sql);
	ResultSet rs = getStmt().executeQuery(sql);
	
	
	while (rs.next()){
		seed.setUNMSeed(rs.getLong("UNMSEED"));
		seed.setURLSeed(rs.getLong("URLSEED"));
		seed.setUserId(userid);
	}
	rs.close();
	ConnectionClose();
	return seed;
	
}
/**
 * 
 * @param URLToken
 * @param UNMToken
 * @param Seq
 * @throws SQLException 
 */

public static void ExpireTokens(Long URLToken, Long UNMToken, int Seq) throws SQLException{
	ConnectionOpen();
	  sql = "UPDATE TOKENS SET EXPIRED = " + EXPIRED + " WHERE URLTOKEN = "+ URLToken+" AND UNMTOKEN = "+UNMToken+" AND SEQUENCE= " +Seq ;
	  System.out.println("Executing the following statement >> " + sql);
      getStmt().executeUpdate(sql);

      System.out.println("Sucessfully EXPIRED to URLToken:"+URLToken + "UNMTOKEN : "+ UNMToken );
     ConnectionClose();
}
/**
 * 
 * @param URLSeed
 * @param UNMSeed
 * @throws SQLException 
 */
public static void ExpireSeeds(Long URLSeed, Long UNMSeed) throws SQLException{
	ConnectionOpen();
	sql = "UPDATE SEEDS SET EXPIRED = " + EXPIRED + " WHERE URLSEED = "+ URLSeed+" AND UNSEED ="+UNMSeed +" AND EXPIRED = 0 ";
	  System.out.println("Executing the following statement >> " + sql);
    getStmt().executeUpdate(sql);

    System.out.println("Sucessfully EXPIRED to URLSeed:"+URLSeed + "UNMSeed : "+ UNMSeed );
    ConnectionClose();
}


public static void main(String args[]) throws SQLException{
	ConnectionOpen();
	//InsertTokens(2222L,3333L,0,1001 );
	int UID = FindUserByTokens(1111L,2222L, 0);
	 System.out.println("UID IS "+ UID);
	ConnectionClose();
	
}

public static void ConnectionClose() throws SQLException{

	//STEP 6: Clean-up environment    
getStmt().close();
getConn().close();
System.out.println("Goodbye!");
}
/**
 * 
 * @param userId
 * @return
 * @throws SQLException 
 */
public static String FindSecretbyUserId(int userId) throws SQLException {
	// TODO Auto-generated method stub
	ConnectionOpen();
	sql= "SELECT VALUE FROM SECRETS WHERE USERID ="+ userId +" EXPIRED = "+ 0  ;
	 System.out.println("Executing the following statement >> " + sql);
	ResultSet rs = getStmt().executeQuery(sql);
	
	String secret= null;
	while (rs.next()){
		secret= rs.getString("VALUE");
	}
	rs.close();
	ConnectionClose();
	return secret;
	
}
/**
 * 
 * @param secret
 * @throws SQLException 
 */
public static void InsertSecret(Secret secret) throws SQLException {
	// TODO Auto-generated method stub
	ConnectionOpen();
    
    sql = "INSERT INTO SECRETS (USERID, CHALLENGE, EXPIRED)  VALUES (" +
    secret.getUserId()+COMMA+
    "'"+secret.getChallenge()+"'" +COMMA+    
    0+")"
    ;
    System.out.println("Executing the following statement >> " + sql);
    getStmt().executeUpdate(sql);

    System.out.println("Sucessfully Inserting to Secret table");
	ConnectionClose();
	

}
/**
 * 
 * @param challenge
 * @param value
 * @return
 * @throws SQLException 
 */
public static int FindUserByChallenge(String challenge) throws SQLException {
	// TODO Auto-generated method stub
	
	ConnectionOpen();
	sql= "SELECT USERID FROM SECRETS  WHERE CHALLENGE ='"+ challenge +"' AND EXPIRED = " + 0  ;
	 System.out.println("Executing the following statement >> " + sql);
	ResultSet rs = getStmt().executeQuery(sql);
	
	int UID =0;
	while (rs.next()){
	UID= rs.getInt("USERID");
	}
	rs.close();
	ConnectionClose();
	return UID;
}
/**
 * 
 * @param challenge
 * @param value
 * @throws SQLException 
 */
public static void ExpireSecretByChallenge(String challenge) throws SQLException {
	// TODO Auto-generated method stub
	ConnectionOpen();
	sql = "UPDATE SECRETS SET EXPIRED = " + EXPIRED + " WHERE CHALLENGE = '"+ challenge +"' AND EXPIRED = "+ 0;
	  System.out.println("Executing the following statement >> " + sql);
    getStmt().executeUpdate(sql);

    System.out.println("Sucessfully EXPIRED to challenge:"+challenge  );
    ConnectionClose();
	
}
/**
 * 
 * @param userId
 * @throws SQLException 
 */
public static void ExpireSecretbyUserId(int userId) throws SQLException {
	// TODO Auto-generated method stub
	ConnectionOpen();
	sql = "UPDATE SECRETS SET EXPIRED = " + EXPIRED + " WHERE USERID = "+ userId +" AND EXPIRED = "+ 0;
	  System.out.println("Executing the following statement >> " + sql);
    getStmt().executeUpdate(sql);

    System.out.println("Sucessfully EXPIRED secret for User :"+userId );
    ConnectionClose();
	
}
 
}
