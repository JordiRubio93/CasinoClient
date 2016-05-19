package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import network.ServerComunication;
import network.segment.GameOver;

public class ExitButtonController implements ActionListener {
	private ServerComunication sc;

	public ExitButtonController(ServerComunication sc) {
		this.sc = sc;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			
			
			
			
			sc.enviarTrama(new GameOver());
		} catch (IOException e) {}
	}
}
