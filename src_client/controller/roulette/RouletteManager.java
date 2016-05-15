package controller.roulette;

import java.util.LinkedList;

import controller.Manager;
import controller.listeners.BetButtonController;
import controller.listeners.ExitButtonController;
import model.Constants;
import model.struct.bet.RouletteBet;
import model.struct.user.PublicUser;
import view.roulette.RouletteView;

public class RouletteManager {
	private Manager manager;
	private RouletteView game;
	private LinkedList<RouletteBet> apostesRuleta;

	public RouletteManager(Manager manager) {
		this.manager = manager;
		apostesRuleta = new LinkedList<RouletteBet>();
	}
	
	public void executaPartida(LinkedList<PublicUser> listUsers){
		game = (RouletteView) manager.getPanel();
		game.actualitzaTemps();
		game.setVisible(true);
		
		game.ompleLlista(listUsers);
		
		BetButtonController bbc = new BetButtonController(game, null, manager.getServer(), Constants.GAME_ROULETTE);
		ExitButtonController ebc = new ExitButtonController(game, manager.getServer());
		game.registerController(bbc, ebc);
	}

	public RouletteView getGame() {
		return new RouletteView(this);
	}

	public void setGame(RouletteView game) {
		this.game = game;
	}
	
	public void afegeixAposta(RouletteBet bet){
		apostesRuleta.add(bet);
	}

	public void eliminaAposta(RouletteBet bet){
		for(int i = 0; i < apostesRuleta.size(); i++){
			if(apostesRuleta.get(i).equals(bet)){
				apostesRuleta.remove(i);
				break;
			}
		}
	}
	
	public LinkedList<RouletteBet> getApostesRuleta() {
		return apostesRuleta;
	}
}
