package edu.pace.csis.phd.sbauth.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Secret {

	/** 
	 * 
	 */
	private String Challenge=null; 
	private int  UserId=0;
	
	public Secret(){
		
	}
	public Secret(String challenge, int userId)
	{
		this.Challenge=challenge;
		this.UserId=userId;
	}
	public Secret(String challenge)
	{
		this.Challenge=challenge;
		
	}
	public Secret( int userid)
	{
		
		this.UserId=userid;
		
	}
	public String getChallenge() {
		return Challenge;
	}
	public void setChallenge(String challenge) {
		Challenge = challenge;
	}
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	
}
