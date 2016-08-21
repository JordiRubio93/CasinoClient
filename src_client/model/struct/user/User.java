package model.struct.user;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private LoginInfo loginInfo;
	private double cash;
	private Date register;
	private Date lastLogin;
	private Date birthday;
	private boolean gender; //male true false female
	
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
	
	public User(String name, String surname, String password, double cash, String email, Date register, Date lastLogin,
			Date birthday, boolean gender) {
		super();
		this.name = name;
		this.surname = surname;
		this.loginInfo = new LoginInfo(email, password);
		this.cash = cash;
		this.register = register;
		this.lastLogin = lastLogin;
		this.birthday = birthday;
		this.gender = gender;
	}

	public User(String email,String password) {
		this.loginInfo = new LoginInfo(email, password);
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
		return this.loginInfo.getEmail();
	}
	
	public String getPassword() {
		return this.loginInfo.getPassword();
	}	
	
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean gender) {
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
	public PublicUser getPublicUser(){
		return new PublicUser(getSurname(),getGender());
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public void changePassword(String pw){
		this.loginInfo.setPassword(pw);
	}
	public void changeEmail(String email){
		this.loginInfo.setEmail(email);
	}
	public void EncryptPassword() {
		loginInfo.EncryptPassword();
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}