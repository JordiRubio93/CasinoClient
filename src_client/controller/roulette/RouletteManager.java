package controller.roulette;

import java.io.IOException;
import java.util.LinkedList;

import model.Constants;
import model.struct.user.User;
import network.ServerComunication;
import network.segment.GameOver;
import view.roulette.RouletteView;

public class RouletteManager {
	private ServerComunication sc;
	private RouletteView game;

	public RouletteManager(ServerComunication sc) {
		this.sc = sc;
		game = new RouletteView();
		game.creaRuleta();
		game.setVisible(true);
	}
	
	public void executaPartida(LinkedList<User> listUsers){
		//game = new RouletteView();
		//game.actualitzaTemps();
		
		
		//game.ompleLlista(listUsers);
		
		try {
			game.creaRuleta();
			Thread.sleep(Constants.MINUT);
			game.acabaPartida();
			sc.enviarTrama(new GameOver());
		} catch (IOException | InterruptedException e) {}
	}

	public RouletteView getGame() {
		return game;
	}

	public void setGame(RouletteView game) {
		this.game = game;
	}
	
}
