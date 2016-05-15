package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.horses.HorsesIntro;
import model.Constants;
import network.ServerComunication;
import network.segment.RouletteBetting;
import network.segment.Seconds;
import view.Dialeg;
import view.GameView;
import view.roulette.RouletteView;

public class BetButtonController implements ActionListener {
	private GameView gV;
	private HorsesIntro hIntro;
	private ServerComunication sc;
	private int game;

	public BetButtonController(GameView gv, HorsesIntro hIntro, ServerComunication sc, int game) {
		this.setGameView(gv);
		this.hIntro = hIntro;
		this.sc = sc;
		this.game = game;
	}

	public GameView getGameView() {
		return gV;
	}

	public void setGameView(GameView gv) {
		this.gV = gv;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			sc.enviarTrama(new Seconds(0));
			int sec = ((Seconds) sc.obtenirTrama()).getSegons();
			
			if(sec >= 50) new Dialeg().setWarningText("You can no longer bet!");
			
			else{
				if(!Constants.apostaFeta){
					if(game == Constants.GAME_HORSES) hIntro.executaIntro();
					if(game == Constants.GAME_ROULETTE){
						sc.enviarTrama(new RouletteBetting("David", ((RouletteView)gV).getRouletteManager().getApostesRuleta()));
					}
				}
				else new Dialeg().setWarningText("You have already bet once!");
			}
		}catch (IOException e) {}
	}
	
	public HorsesIntro getHorsesIntro(){
		return hIntro;
	}
}
