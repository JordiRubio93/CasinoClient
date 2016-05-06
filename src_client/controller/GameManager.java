package controller;

import java.io.IOException;
import java.util.LinkedList;

import controller.horses.HorsesManager;
import controller.roulette.RouletteManager;
import model.struct.users.PublicUser;
import network.ServerComunication;
import network.segment.LoginUser;

public class GameManager {
	private String type;
	private static ServerComunication sc;
	private LinkedList<PublicUser> listUsers;
	
	public GameManager(String s){
		type=s;
	}
	public void executaSala(){		
		if(type.equals("ruleta")){
			RouletteManager rm = new RouletteManager(sc);
			rm.executaPartida(listUsers);
		}else{
			HorsesManager hm = new HorsesManager(sc);
			hm.executaCursa(listUsers);
		}
	}
	
	public void executaCasino(){
		sc = new ServerComunication();
		
		listUsers = new LinkedList<PublicUser>();
		PublicUser u1 = new PublicUser("Resources/avatar.png", "Xavi Solé", 32, 'M', "Spain");
		listUsers.add(u1);
		PublicUser u2 = new PublicUser("Resources/avatar.png", "Edu de Torres", 27, 'M', "Spain");
		listUsers.add(u2);
		
		try {
			sc.enviarTrama(new LoginUser());
			sc.obtenirTrama();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
			executaSala();
		
	}
}
