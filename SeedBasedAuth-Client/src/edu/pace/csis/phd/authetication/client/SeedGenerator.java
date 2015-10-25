package edu.pace.csis.phd.authetication.client;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

public class SeedGenerator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2298176463633699663L;
	public static  String  BASEPATH = "c:\\junk\\";
   public static String IMAGESOURCE  ="boat.jpg";  //obj1__0.ppm boat.jpg
    public static int seedArrSize=11;
   

	

	public SeedGenerator() {
		// TODO Auto-generated constructor stub
		super();
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
			
		// TODO Auto-generated method stub
		readPassFile();
		Long urlSeed, unmSeed, urlToken, unmToken;
		 urlSeed = generateURLSeed();
		 unmSeed =generateUNSeed();
		
		 urlToken= TokenGenerator.generateToken(urlSeed); 
		 unmToken= TokenGenerator.generateToken(unmSeed); 
		//@JR Display the URLToken and the unmTokens after generated on the screen, along with log off button
		 
		 TokenGenerator.updateSequenceNumber();
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
		  System.out.println("URL Seed is: "+seedSB.toString());
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
		  System.out.println("UNM Seed is: "+seedSB.toString());
		  	Long seed= new Long(seedSB.toString());
		return seed;
		
	}
	
	/**** Getters and Setters***/
	public static byte[] getFileContent() {
		return fileContent;
	}

	public static void setFileContent(byte[] fileContent) {
		SeedGenerator.fileContent = fileContent;
	}
	
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
		SeedGenerator.seedArrSize = seedArrSize;
	}
	//public static BufferedImage bufferedImage = null;
	 public static byte[] fileContent = null;
}
