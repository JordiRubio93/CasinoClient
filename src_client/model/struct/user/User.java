package model.struct.user;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Boolean gender; //male true false female
	private Date register;
	private Date birthday;
	private double cash;
	
	public PublicUser getPublicUser(){
		return new PublicUser(getSurname(),getGender(),getCash());
	}
	
	public User(String name, String surname, String email, String password, Boolean gender, Date register,
			Date birthday, double cash) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.register = register;
		this.birthday = birthday;
		this.cash = cash;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Date getRegister() {
		return register;
	}
	public void setRegister(Date register) {
		this.register = register;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
}
