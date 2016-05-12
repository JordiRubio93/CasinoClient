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
	private static ServerComunication sc;
	private LoginValidator loginValidator;
	
	
	public GameManager(Manager manager){
		this.manager=manager;
		loginValidator = new LoginValidator();
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
			HorsesManager hm = new HorsesManager(sc);
			manager.setPanel(hm.getGame());
	}
	
	
	public void executaSala(){		
		if(type.equals("PlayRoulette")){
			RouletteManager rm = new RouletteManager(sc);
			//rm.executaPartida(listUsers);
		}else{
			HorsesManager hm = new HorsesManager(sc);
			//hm.executaCursa(listUsers);
		}
	}
	
	
	
	public PublicUser getPublicUser() {
		return publicUser;
	}
	public void setPublicUser(PublicUser publicUser) {
		this.publicUser = publicUser;
	}
	public void executaCasino(){		
		executaSala();	
	}
}
