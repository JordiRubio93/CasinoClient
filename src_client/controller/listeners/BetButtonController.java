package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Constants;
import controller.horses.HorsesIntro;
import controller.roulette.RouletteManager;
import network.segment.RouletteBetting;
import network.segment.Seconds;
import view.Dialeg;

public class BetButtonController implements ActionListener {
	private HorsesIntro hIntro;
	private int game;
	private RouletteManager rManager;

	public BetButtonController(RouletteManager rManager, HorsesIntro hIntro, int game) {
		this.hIntro = hIntro;
		this.game = game;
		this.rManager = rManager;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			hIntro.getSc().enviarTrama(new Seconds(0));
			int sec = ((Seconds) hIntro.getSc().obtenirTrama()).getSegons();
			
			if(sec >= 50) new Dialeg().setWarningText("You can no longer bet!");
			
			else{
				if(!Constants.apostaFeta){
					if(game == Constants.GAME_HORSES) hIntro.executaIntro();
					if(game == Constants.GAME_ROULETTE){
						hIntro.getSc().enviarTrama(new RouletteBetting("David", rManager.getApostesRuleta()));
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
