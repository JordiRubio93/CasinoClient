package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import network.ServerComunication;
import network.segment.GameOver;
import view.GameView;

public class ExitButtonController implements ActionListener {
	private ServerComunication sc;
	private GameView game;

	public ExitButtonController(GameView game, ServerComunication sc) {
		this.sc = sc;
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			game.exit();
			sc.enviarTrama(new GameOver());
		} catch (IOException e) {}
	}
}
