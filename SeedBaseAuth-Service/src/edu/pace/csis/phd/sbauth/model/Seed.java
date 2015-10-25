package edu.pace.csis.phd.sbauth.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Seed {
	
	public Seed(){
		
	}
	public Seed (Long urlseed, Long unmseed){
		this.URLSeed=urlseed;
		this.UNMSeed=unmseed;
	}
	public Seed (Long urlseed, Long unmseed, int userid){
		this.URLSeed=urlseed;
		this.UNMSeed=unmseed;
		this.setUserId(userid);
	}
	
	/**
	 * 
	 */
	private Long URLSeed , UNMSeed;
	private int UserId;

	public Long getURLSeed() {
		return URLSeed;
	}

	public void setURLSeed(Long uRLSeed) {
		URLSeed = uRLSeed;
	}

	public Long getUNMSeed() {
		return UNMSeed;
	}

	public void setUNMSeed(Long uNMSeed) {
		UNMSeed = uNMSeed;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	
	
}
