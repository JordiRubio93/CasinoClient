package controller;

import controller.horses.HorsesManager;
import controller.roulette.RouletteManager;
import model.LoginValidator;
import model.struct.user.PublicUser;
import network.ServerComunication;

public class GameManager {
	private PublicUser publicUser;
	private String type;
	private Manager manager;
	private ServerComunication sc;
	private LoginValidator loginValidator;
	
	public GameManager(Manager manager){
		this.manager=manager;
		setSc(manager.getServer());
		loginValidator = new LoginValidator();
	}

	public void setSc(ServerComunication sc) {
		this.sc = sc;
	}

	public void setGame(String s){
		type=s;
	}
	
	public Boolean comprovaLoginPW(String pw){
		return (loginValidator.validatePasswordFormat(pw));
	}
	
	public Boolean comprovaLoginMail(String email){
		return (loginValidator.validateEmailFormat(email));
	}
	
	public void executaRuleta(){
		RouletteManager rm = new RouletteManager(sc);
		manager.setPanel(rm.getGame());
	}
	
	public void executaHorses(){
		HorsesManager hm = new HorsesManager(manager);
		manager.setPanel(hm.getGame());
		hm.executaCursa(null);
	}
	
	public PublicUser getPublicUser() {
		return publicUser;
	}
	
	public void setPublicUser(PublicUser publicUser) {
		this.publicUser = publicUser;
	}
}
