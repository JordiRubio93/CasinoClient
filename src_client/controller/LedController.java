package controller;

import java.awt.Color;
import java.io.IOException;

import network.ServerComunication;
import network.segment.Running;
import view.MainWindow;

public class LedController implements Runnable {
	private MainWindow view;
	private ServerComunication sc;
	private boolean running;
	
	public LedController(MainWindow view, ConfigurationFile cf) {
		this.view = view;
		this.sc = new ServerComunication(view.getManager(), cf);
	}

	@Override
	public void run() {
		try {
			sc.establirConnexio(Boolean.TRUE);
			while(true){
				sc.enviarTrama(new Running(false, 1));
				running = ((Running)sc.obtenirTrama()).isRunning();
				if(running) view.setLEDColor(Color.GREEN);
				else view.setLEDColor(Color.RED);
				Thread.sleep(500);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ServerComunication getSC(){
		return sc;
	}
}
