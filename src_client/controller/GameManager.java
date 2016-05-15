package controller;

import controller.horses.HorsesManager;
import controller.listeners.BlackjackButtonsController;
import controller.roulette.RouletteManager;
import model.LoginValidator;
import model.struct.user.PublicUser;
import network.ServerComunication;

public class GameManager {
	private PublicUser publicUser;
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
		RouletteManager rm = new RouletteManager(manager);
		manager.setPanel(rm.getGame());
		rm.executaPartida(null);
	}
	
	public void executaHorses(){
		HorsesManager hm = new HorsesManager(manager);
		manager.setPanel(hm.getGame());
		hm.executaCursa(null);
		
	}
	
	public void executaBlackjack(){
		BlackjackButtonsController bj = new BlackjackButtonsController();
		manager.setPanel(bj.getMainView());
		bj.startGame();
	}
	
	public PublicUser getPublicUser() {
		return publicUser;
	}
	
	public void setPublicUser(PublicUser publicUser) {
		this.publicUser = publicUser;
	}
}
