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
import java.util.Arrays;

import javax.imageio.ImageIO;

public class TokenIdentfier implements Serializable {
	public static  String  BASEPATH = "c:\\junk\\";
    public static String IMAGESOURCE  ="boat.jpg"; 
    public static int seedArrSize=11;
   

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
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
	 *
	 */
	public static void init(){
		Long urlSeed, unmSeed, urlToken, unmToken, nextURLToken, nextUNMToken;
		
		
		readPassFile();
		//Generate Seeds for url & unm
		 urlSeed = generateURLSeed();
		 unmSeed =generateUNSeed();
		
			
		// Generate token
		 urlToken= TokenGenerator.GenerateToken(urlSeed); 
		 unmToken= TokenGenerator.GenerateToken(unmSeed); 
		
		//Copare Tokens from the client
	if	(ValidateTokens()){
		//save seeds in db
		SaveSeeds(urlSeed,unmSeed );	
		
		//Save tokens in db, the last argunment indicates that token expired
		SaveTokens(urlToken,unmToken, 1 );
		
		//Generate & Save next tokens in tokens db		
		 nextURLToken= TokenGenerator.GenerateNextToken(urlSeed,1);
		 nextUNMToken= TokenGenerator.GenerateNextToken(unmSeed,1);
		 
		//Save tokens in db, the last argunment indicates that token NOT expired
		 SaveTokens(nextURLToken,nextUNMToken, 0 );
	}else{
	
		//Handle invlid tokens
	}
	}
	
	
	/**
	 * Validate Tokens generated on server side with tokens from client side
	 */
	public static boolean  ValidateTokens(){
		
		return true;
	}
	
	
	
	/**
	 * Updating Sequence Number for the next call to generate the next PRN
	 */
	public static void SaveSeeds(Long urlSeed, Long unmSeed ){
		/*
		 * This method will insert the new seeds into db seed table  
		 */
		
	}
	 
	/**
	 * 	Updating Sequence Number for the next call to generate the next PRN
	 */
	public static void SaveTokens(Long urlToken, Long unmToken , int expired){
		/*
		 * This method will insert the new tokens into db seed table  
		 */
		
	}
	
	/**
	 *  Reads input file into byte Array
	 * @return
	 */
	
	private static  byte[] readPassFile(){

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
		
		StringBuffer seedSB = new StringBuffer();

		  //NN-Comment: Add validation for file size
		  
		for (int i=getFileContent().length -1 ; i>(getFileContent().length-getSeedArrSize()) ; i--){			
			  seedSB.append(Math.abs(getFileContent()[i]));
		  }
		  System.out.println("UNM Seed is: "+seedSB.toString());
		  	Long seed= new Long(seedSB.toString());
		return seed;
		
	}

	/**
	 * Generate Seed from input file byte array
	 * @param fileContent
	 * @return
	 */
	private static Long   generateUNSeed(){
		
		StringBuffer seedSB = new StringBuffer();

		  //NN-Comment: Add validation for file size
		  
		for (int i=0 ; i<getSeedArrSize() ; i++){			
			  seedSB.append(Math.abs(getFileContent()[i]));
		  }
		  System.out.println("URL Seed is: "+seedSB.toString());
		  	Long seed= new Long(seedSB.toString());
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
		TokenIdentfier.seedArrSize = seedArrSize;
	}
	
	public static byte[] getFileContent() {
		return fileContent;
	}

	public static void setFileContent(byte[] fileContent) {
		TokenIdentfier.fileContent = fileContent;
	}
	
	//public static BufferedImage bufferedImage = null;
	 public static byte[] fileContent = null;

	public TokenIdentfier() {
		// TODO Auto-generated constructor stub
		super();
	}
}
