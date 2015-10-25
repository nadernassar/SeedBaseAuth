package edu.pace.csis.phd.sbauth.server.generator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import edu.pace.csis.phd.sbauth.server.query.Queries;

public class Initializer implements Serializable {
	public static  String  BASEPATH = "c:\\root\\images\\";
    public static String IMAGESOURCE  ="image (10000).jpg"; 
    public static int seedArrSize=8;
   

	

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		 
		init();
		
	}
	
	/**
	 * First initialization process, 
	 * Read passFile,
	 * -Generate Seeds
	 * -Save seeds
	 *- Generate n tokens
	 *- Compare n Tokens from client
	 *- if match, generate n+1 tokens
	 *- Save n & n+1 tokens in the db
	 * @throws SQLException 
	 *
	 */
	public static void init() throws SQLException{
		Long urlSeed, unmSeed, urlToken, unmToken, nextURLToken, nextUNMToken;
		
		
		readRootFile();
		//Generate Seeds for url & unm
			 urlSeed = generateURLSeed();
			 unmSeed =generateUNSeed();
		
			
		// Generate token
			 urlToken= TokenGenerator.GenerateToken(urlSeed); 
			 unmToken= TokenGenerator.GenerateToken(unmSeed); 
		
		//Compare Tokens from the client
	if	(ValidateTokens()){
		//save seeds in db
			SaveSeeds(urlSeed,unmSeed );	
			int uid = Queries.FindUserBySeeds(urlSeed, unmSeed);
		
		//Save tokens in db, the last argument indicates that token expired
			SaveTokens(urlToken,unmToken,0,uid );
			Queries.ExpireTokens(urlToken, unmToken, 0);
		
		//Generate & Save next tokens in tokens db		
			 nextURLToken= TokenGenerator.GenerateNextToken(urlSeed,1);
			 nextUNMToken= TokenGenerator.GenerateNextToken(unmSeed,1);
		 
		//Save tokens in db, the last argunment indicates that token NOT expired
			 SaveTokens(nextURLToken,nextUNMToken, 1,uid );
	}else{
	
		//Handle invlid tokens
	}
	}
	
	
	/**
	 * Validate Tokens generated on server side with tokens from client side
	 */
	public static boolean  ValidateTokens(){
		/**
		 * Compare tokens from the client with generated token from the server 
		 */
		
		return true;
	}
	
	
	
	/**
	 * Updating Sequence Number for the next call to generate the next PRN
	 */
	public static void SaveSeeds(Long urlSeed, Long unmSeed ){
		/*
		 * This method will insert the new seeds into db seeds table  
		 * 
		 */
		try {
			Queries.InsertSeeds(urlSeed, unmSeed);
			
			
		} catch (SQLException e) {
			System.out.println("ERROR INSERTIING SEEDS");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	/**
	 * 	Updating Sequence Number for the next call to generate the next PRN
	 */
	public static void SaveTokens(Long urlToken, Long unmToken , int seq, int userId){
		/*
		 * This method will insert the new tokens into db seed table  
		 */
		try {
			
			Queries.InsertTokens(urlToken, unmToken,seq,userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  Reads input file into byte Array
	 * @return
	 */
	
	private static  byte[] readRootFile(){

	try {
		      
			  File fi = new File(BASEPATH + IMAGESOURCE);
			 setFileContent( Files.readAllBytes(fi.toPath()));
//			  System.out.println( "File length is: " + fileContent.length);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return getFileContent();
	}
	
	
	

	/**
	 * Generate Seed from input file byte array
	 * @param fileContent
	 * @return
	 */
	private static Long   generateURLSeed(){
		final  String METHODNAME ="generateURLSeed";
		
		StringBuffer seedSB = new StringBuffer();

		  //NN-Comment: Add validation for file size
		  
		for (int i=getFileContent().length -1 ; i>(getFileContent().length-getSeedArrSize()) ; i--){			
			  seedSB.append(Math.abs(getFileContent()[i]));
		  }
		  System.out.println(METHODNAME+" :URL Seed is: "+seedSB.toString());
		  	
		  	Long seed= Long.parseLong(seedSB.toString(),10);
		return seed;
		
	}

	/**
	 * Generate Seed from input file byte array
	 * @param fileContent
	 * @return
	 */
	private static Long   generateUNSeed(){
		final  String METHODNAME ="generateUNSeed";
		StringBuffer seedSB = new StringBuffer();

		  //NN-Comment: Add validation for file size
		  
		for (int i=0 ; i<getSeedArrSize() ; i++){			
			  seedSB.append(Math.abs(getFileContent()[i]));
		  }
		  System.out.println(METHODNAME+ ":UNM Seed is: "+seedSB.toString());
		  	Long seed= new Long(new String(seedSB.toString()));
		  	
		return seed;
		
	}
	
	/***Getters and Setters **/
	public static String getBASEPATH() {
		return BASEPATH;
	}

	public static void setBASEPATH(String bASEPATH) {
		BASEPATH = bASEPATH;
	}

	public static String getIMAGESOURCE() {
		return IMAGESOURCE;
	}

	public static void setIMAGESOURCE(String iMAGESOURCE) {
		IMAGESOURCE = iMAGESOURCE;
	}

	public static int getSeedArrSize() {
		return seedArrSize;
	}

	public static void setSeedArrSize(int seedArrSize) {
		Initializer.seedArrSize = seedArrSize;
	}
	
	public static byte[] getFileContent() {
		return fileContent;
	}

	public static void setFileContent(byte[] fileContent) {
		Initializer.fileContent = fileContent;
	}
	
	//public static BufferedImage bufferedImage = null;
	 public static byte[] fileContent = null;

	public Initializer() {
		// TODO Auto-generated constructor stub
		super();
	}
}
