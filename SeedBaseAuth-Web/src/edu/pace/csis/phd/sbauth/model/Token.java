package edu.pace.csis.phd.sbauth.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {

	/** 
	 * 
	 */
	private String URLToken, UNMToken, Seq, UserId;
	
	public Token(){
		
	}
	public Token(Long urltoken, Long unmtoken, int seq, int userid)
	{
		this.URLToken=urltoken.toString();
		this.UNMToken=unmtoken.toString();
		this.Seq=new Integer(seq).toString();
		this.UserId=new Integer(userid).toString();
	}
	public Token(Long urltoken, Long unmtoken, int seq)
	{
		this.URLToken=urltoken.toString();
		this.UNMToken=unmtoken.toString();
		this.Seq=new Integer(seq).toString();
		
	}
	public String getURLToken() {
		return URLToken;
	}

	public void setURLToken(String uRLToken) {
		URLToken = uRLToken;
	}

	public String getUNMToken() {
		return UNMToken;
	}

	public void setUNMToken(String uNMToken) {
		UNMToken = uNMToken;
	}

	public String getSeq() {
		return Seq;
	}

	public void setSeq(String seq) {
		Seq = seq;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}
}
