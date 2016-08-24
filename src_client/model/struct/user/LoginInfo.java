package model.struct.user;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private Boolean pwhash; //ho nesecitem per el recuerdame!
	
	public LoginInfo(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.pwhash = Boolean.FALSE;
	}
	
	public LoginInfo(String email, String password, Boolean pwhash) {
		super();
		this.email = email;
		this.password = password;
		this.pwhash = pwhash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String pw) {
		this.password = pw;
		this.pwhash = Boolean.FALSE;
	}
	
	public String getPassword() {
		return password;
	}

	public void EncryptPassword() {
		if (!pwhash) {
			try {
				this.password = toSHA1(getPassword().getBytes("UTF-8"));
				pwishashed();
			} catch (UnsupportedEncodingException e) {
				//manager.showMessage(e.getMessage());
			}
		}
	}

	public void pwishashed() {
		this.pwhash = Boolean.TRUE;
	}
	public Boolean getPwhash() {
		return pwhash;
	}
	//TODO MOVE
	public String toSHA1(byte[] convertme) {
	    //MessageDigest md = null;
	    String digestStr = null;
	    try {
	    	MessageDigest digest = MessageDigest.getInstance("SHA-1");
	    	digest.update(convertme);
	    	byte[] digestBytes = digest.digest();
	    	digestStr = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
	       // md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        
	    } 
	    return digestStr.toLowerCase();
	}
	
	public String toString(){
		return email + "@@" + password + "@@" + pwhash.toString();
	}
}
