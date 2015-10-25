package edu.pace.csis.phd.sbauth.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {

	/** 
	 * 
	 */
	private Long URLToken, UNMToken;
	private int Seq, UserId;
	
	public Token(){
		
	}
	public Token(Long urltoken, Long unmtoken, int seq, int userid)
	{
		this.URLToken=urltoken;
		this.UNMToken=unmtoken;
		this.Seq=seq;
		this.UserId=userid;
	}
	public Token(Long urltoken, Long unmtoken, int seq)
	{
		this.URLToken=urltoken;
		this.UNMToken=unmtoken;
		this.Seq=seq;
		
	}
	public Long getURLToken() {
		return URLToken;
	}

	public void setURLToken(Long uRLToken) {
		URLToken = uRLToken;
	}

	public Long getUNMToken() {
		return UNMToken;
	}

	public void setUNMToken(Long uNMToken) {
		UNMToken = uNMToken;
	}

	public int getSeq() {
		return Seq;
	}

	public void setSeq(int seq) {
		Seq = seq;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
}
