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
	
	public LedController(MainWindow view) {
		this.view = view;
		this.sc = new ServerComunication(null, new ConfigurationFile("localhost", 2012));
	}

	@Override
	public void run() {
		try {
			sc.establirConnexio();
			
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
