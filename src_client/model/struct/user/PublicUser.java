package model.struct.user;

import java.io.Serializable;

public class PublicUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private String surname;
	private boolean gender; //male true false female

	public PublicUser(String surname, boolean gender) {
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
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
}