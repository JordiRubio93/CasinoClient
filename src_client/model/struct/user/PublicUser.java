package model.struct.user;

import java.io.Serializable;

public class PublicUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private String surname;
	private Boolean gender; //male true false female
	private double cash;

	public PublicUser(String surname, Boolean gender, double cash) {
		super();
		this.surname = surname;
		this.gender = gender;
		this.cash = cash;
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
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
}
