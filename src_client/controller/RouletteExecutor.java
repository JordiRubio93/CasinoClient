package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import network.segment.Check;
import network.segment.Segment;
import view.Dialeg;
import view.roulette.RouletteView;

public class RouletteExecutor implements Runnable {
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private RouletteView game;
	private int seconds;
	
	public RouletteExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager) {
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		active = true;
		game = (RouletteView) manager.getPanel(Constants.R_VIEW_NAME);
	}

	@Override
	public void run() {
		try {
			while (active) {
				switch (obtenirInstruccio().getClass().getSimpleName()) {
				case "Check":
					if(!((Check) s).isOk()) new Dialeg().setWarningText("Aposta Denegada");
					else  new Dialeg().setWarningText("Aposta Acceptada");	
					break;
					
				case "RouletteBetting":
					System.err.println("Apostat");//TODO CARDS
					break;
				case "xxxx":
					mostragif();
					break;
				default:
					System.out.println("");
					break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		active = false;
	}

	public synchronized Segment obtenirInstruccio() throws ClassNotFoundException, IOException {
		s = (Segment) objectIn.readObject();
		System.out.println(Calendar.getInstance().getTime().toString() + " soc un " + s.getClass());
		return s;
	}

	private void mostragif() {
		Thread thread = new Thread() {
			public void run() {
				game.insereixGif();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("DONE");
			}
		};
		thread.start();		
		// get.enviarTrama(new GameOver());
	}
}
