package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.horses.HorsesIntro;
import model.Constants;
import view.Dialeg;
import view.GameView;

public class BetButtonController implements ActionListener {
	private GameView gV;
	private HorsesIntro hIntro;

	public BetButtonController(GameView gv, HorsesIntro hIntro) {
		this.setGameView(gv);
		this.hIntro = hIntro;
	}

	public GameView getGameView() {
		return gV;
	}

	public void setGameView(GameView gv) {
		this.gV = gv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!Constants.apostaFeta){
			hIntro.executaIntro();
		}
		else new Dialeg().setWarningText("Ja has apostat abans!");;
	}
	
	public HorsesIntro getHorsesIntro(){
		return hIntro;
	}
}
