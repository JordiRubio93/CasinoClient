package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import model.Bet;
import network.segment.Check;
import network.segment.InitRoulette;
import network.segment.NotifyBet;
import network.segment.Segment;
import view.Dialeg;
import view.roulette.RouletteView;

public class RouletteExecutor implements Runnable {
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private RouletteView game;
	private Bet aposta;
	
	public RouletteExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager) {
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		active = true;
		game = (RouletteView) manager.getPanel(Constants.R_VIEW_NAME);
		System.out.println("ready");
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
				case "NotifyBet":
					NotifyBet aposta = ((NotifyBet) s);
					System.err.println("Agreguem al panell lateral " + aposta.getPublicUser().getSurname()
							+ " ha apostat" + aposta.getAposta().getAmount() + " al numero " + aposta.getAposta().getAmount());// TODO																									// CARDS
					break;
				case "InitRoulette":
					System.out.println("tenim guanyador");
					InitRoulette resultat = ((InitRoulette) s);
					mostragif();
					String winner = "The winner is... " + resultat.getWinner() + " !\n" + " Has guanyat"
							+ resultat.getGuanys();
					mostragif();
					new Dialeg().setWarningText(winner);
					break;
				default:
					System.err.println("no se que ma arribat");
					break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Bet getAposta() {
		return aposta;
	}

	public void setAposta(Bet aposta) {
		this.aposta = aposta;
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
		game.insereixGif();
		// get.enviarTrama(new GameOver());
	}
}
