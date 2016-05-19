package model.struct.user;

import java.io.Serializable;

public class PublicUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private String surname;
	private Boolean gender; //male true false female

	public PublicUser(String surname, Boolean gender) {
		super();
		this.surname = surname;
		this.gender = gender;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
}