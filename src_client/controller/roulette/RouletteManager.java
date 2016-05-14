package controller.roulette;

import java.util.LinkedList;

import controller.Manager;
import model.struct.user.PublicUser;
import view.roulette.RouletteView;

public class RouletteManager {
	private Manager manager;
	private RouletteView game;

	public RouletteManager(Manager manager) {
		this.manager = manager;
	}
	
	public void executaPartida(LinkedList<PublicUser> listUsers){
		game = (RouletteView) manager.getPanel();
		game.actualitzaTemps();
		game.setVisible(true);
		
		game.ompleLlista(listUsers);
	}

	public RouletteView getGame() {
		return new RouletteView();
	}

	public void setGame(RouletteView game) {
		this.game = game;
	}
}
