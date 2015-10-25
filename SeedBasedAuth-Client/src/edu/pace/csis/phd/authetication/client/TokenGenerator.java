package edu.pace.csis.phd.authetication.client;



public class TokenGenerator extends pj2.lib.edu.rit.util.Random {
	
	//**** SEQ NUM is what will be stored on the client and will be pulled everytime a new Token to be generated 
	public static int squenceNum = 0; //will be pulled form the db ..  
	    
	 public static int getSquenceNum() {
		//will be pulled form the db ..
		 /**
		  * @JR this method to be updates so that it pulls out the Seq Num from where it is stored in the mobile device
		  */
		return squenceNum;
	}

	public static void setSquenceNum(int squenceNum) {
		TokenGenerator.squenceNum = squenceNum;
	}

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
 
 generateToken(seed);
 
	 
	}
	
public static Long generateToken(Long seed){
	
		TokenGenerator Tn = new TokenGenerator( seed);	
		 Tn.skipTo(getSquenceNum());	
		 Long token = Tn.nextLong();
	 System.out.println( " seed is : "+ seed  +" Token is :  " + token);
	return token;
		

}
/**
 * Updating Sequence Number for the next call to generate the next PRN
 */
public static void updateSequenceNumber(){
	/*
	 * This method will update and store sequence number on the client side for next use  
	 */
	/*
	 * 
	 * @JR this method need to be updated to store the NEXT sequ number on the mobile device
	 */
	setSquenceNum(getSquenceNum()+1);

	//This will include a step for saving the new seqNum on the client side 
	System.out.println("New SeqNumeber is: " +TokenGenerator.getSquenceNum());
	
	
}
	
}
