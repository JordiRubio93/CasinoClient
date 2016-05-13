package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.horses.HorsesIntro;
import model.Constants;
import network.ServerComunication;
import network.segment.Seconds;
import view.Dialeg;
import view.GameView;

public class BetButtonController implements ActionListener {
	private GameView gV;
	private HorsesIntro hIntro;
	private ServerComunication sc;

	public BetButtonController(GameView gv, HorsesIntro hIntro, ServerComunication sc) {
		this.setGameView(gv);
		this.hIntro = hIntro;
		this.sc = sc;
	}

	public GameView getGameView() {
		return gV;
	}

	public void setGameView(GameView gv) {
		this.gV = gv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			sc.enviarTrama(new Seconds(0));
			int sec = ((Seconds) sc.obtenirTrama()).getSegons();
			
			if(sec >= 50) new Dialeg().setWarningText("Ja no pots apostar!");
			
			else{
				if(!Constants.apostaFeta){
					hIntro.executaIntro();
				}
				else new Dialeg().setWarningText("Ja has apostat abans!");
			}
		}catch (IOException e1) {}
	}
	
	public HorsesIntro getHorsesIntro(){
		return hIntro;
	}
}
