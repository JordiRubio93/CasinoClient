package model.struct.user;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String password;
	private double cash;
	private String email;
	private Date register;
	private Date lastLogin;
	private Date birthday;
	private Boolean gender; //male true false female
	
	/**CREATE TABLE IF NOT EXISTS Clients (
	ID_Client int not null auto_increment PRIMARY KEY,
    Nom varchar(20),
    Cognom varchar(20),
	Contrasenya varchar(40) not null,
	Saldo float,
	Data_Registre DATETIME,
	Data_LastLogin DATETIME,
    Data_Naixement DATE,
    email varchar(50) unique,
	sexe char
)*/
		
	public PublicUser getPublicUser(){
		return new PublicUser(getSurname(),getGender());
	}
	
	
	
	
	public Date getLastLogin() {
		return lastLogin;
	}




	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}




	public User(String name, String surname, String password, double cash, String email, Date register, Date lastLogin,
			Date birthday, Boolean gender) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.cash = cash;
		this.email = email;
		this.register = register;
		this.lastLogin = lastLogin;
		this.birthday = birthday;
		this.gender = gender;
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
