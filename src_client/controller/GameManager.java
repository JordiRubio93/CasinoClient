package controller;

import model.LoginValidator;
import model.struct.user.PublicUser;
import model.struct.user.User;
import network.ServerComunication;

public class GameManager {
	private User user;
	private Manager manager;
	private ServerComunication sc;
	private LoginValidator loginValidator;
	
	public GameManager(Manager manager){
		this.manager=manager;
		setSc(manager.getServer());
		loginValidator = new LoginValidator();
	}

	public ServerComunication getSc() {
		return sc;
	}

	public void setSc(ServerComunication sc) {
		this.sc = sc;
	}

	public Boolean comprovaLoginPW(String pw){
		return (loginValidator.validatePasswordFormat(pw));
	}
	
	public Boolean comprovaLoginMail(String email){
		return (loginValidator.validateEmailFormat(email));
	}
	
	public void executaRuleta(){
	}
	
	public void executaHorses(){
	}
	
	public void executaBlackjack(){
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
