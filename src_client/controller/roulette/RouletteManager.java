package controller.roulette;

import java.io.IOException;
import java.util.LinkedList;

import model.Constants;
import model.struct.users.PublicUser;
import network.ServerComunication;
import network.segment.GameOver;
import network.segment.Play;
import view.roulette.RouletteView;

public class RouletteManager {
	private ServerComunication sc;
	private RouletteView game;

	public RouletteManager(ServerComunication sc) {
		this.sc = sc;
	}
	
	public void executaPartida(LinkedList<PublicUser> listUsers){
		game = new RouletteView();
		game.actualitzaTemps();
		game.setVisible(true);
		
		game.ompleLlista(listUsers);
		
		try {
			sc.enviarTrama(new Play("roulette"));
			
			game.creaRuleta();
			
			Thread.sleep(Constants.MINUT);
			
			game.acabaPartida();
			sc.enviarTrama(new GameOver());
		} catch (IOException | InterruptedException e) {}
	}
}
