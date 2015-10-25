package edu.pace.csis.phd.sbauth.server.generator;

import edu.pace.csis.phd.sbauth.dao.SeedDAO;
import edu.pace.csis.phd.sbauth.model.Seed;
import edu.pace.csis.phd.sbauth.model.Token;



public class TokenGenerator extends pj2.lib.edu.rit.util.Random {
	
	/*
	 * SEQ NUM is what will be stored on the client and will be pulled everytime a new Token to be generated 
	 * On the server, we will store n+1 tokens for validation & sequence number to bbe able to drive n+1
	 */
	public static int squenceNum = 0; //will be pulled form the db ..  
	    
	
	public TokenGenerator(Long seed) {
		super(seed);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param args
	 * @param seed 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* NN-Comment: Seed range -8999999999999999999L to 8999999999999999999L  that is 17999999999999999998 seeds 
		 * which is  2^64> 17999999999999999998 > 2^63 seeds
		 */
 Long seed = 391152465741199399L; 
 GenerateToken(seed);
 for (int z=0; z<10; z++){
	 setSquenceNum(z);
	 GenerateNextToken(seed, z);
 }
	 
	}
	
	/**
	 * 
	 * @param seed
	 * @return
	 */
public static Long GenerateToken(Long seed){
	
	TokenGenerator Tn = new TokenGenerator( seed);	
	Tn.skipTo(getSquenceNum());	
	Long token =  Tn.nextLong();
	 System.out.println( " seed is : "+ seed +", seqNum is; "+ getSquenceNum() +", Token is :  " + token);
	
		return token;

}

/**
 * Generate Next Token to be stored in the db
 * @param seed
 * @return
 */
public static Long GenerateNextToken(Long seed, int seqNum){
	setSquenceNum(seqNum);
	TokenGenerator Tn = new TokenGenerator(seed);		
	Tn.skipTo(getSquenceNum());		
	
	Long nextToken = Tn.nextLong();
	 System.out.println( " seed is : "+ seed +", seqNum is; "+ getSquenceNum() +", nextToken is:  " + nextToken);
	return nextToken;

}
/**
 * 
 * @param seed
 * @param seqNum
 * @return
 */
public static Long GenerateRandomToken(Long seed, int seqNum){
	final String  METHODNAME= "GenerateRandomToken";
	TokenGenerator Tn = new TokenGenerator(seed);		
	Tn.skipTo(seqNum);		
	Long randomToken = Tn.nextLong();
	 System.out.println( METHODNAME + " seed is : "+ seed +", seqNum is; "+ seqNum +", RandomtToken is:  " + randomToken);
	 //return Math.abs(randomToken);
	 return randomToken;

}


/**
 * 
 * @param token
 * @return
 */
public static Token GenerateNextTokens(Token token) {
	// TODO Auto-generated method stub
	  Token nxtToken= new Token();
	  int nxtSeqNum = token.getSeq()+1;
	int uid = token.getUserId();
	Seed seed = SeedDAO.FindSeedByUserId(token.getUserId());
	
	nxtToken.setURLToken(GenerateNextToken(seed.getURLSeed(),nxtSeqNum));
	nxtToken.setUNMToken(GenerateNextToken(seed.getUNMSeed(),nxtSeqNum));
	nxtToken.setSeq(token.getSeq()+1);
	nxtToken.setUserId(token.getUserId());
	
	return nxtToken;
}
/**
 * 
 * @return
 */
public static int getSquenceNum() {
	//will be pulled form the db ..  
	return squenceNum;
}

public static void setSquenceNum(int squenceNum) {
	TokenGenerator.squenceNum = squenceNum;
}


	
}
