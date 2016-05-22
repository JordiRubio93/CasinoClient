package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import model.Bet;
import network.segment.Check;
import network.segment.Disconnect;
import network.segment.InitRoulette;
import network.segment.NotifyBet;
import network.segment.Segment;
import view.Dialeg;
import view.roulette.RouletteView;

/**
 * 
 * <p>
 * <b> Classe: RouletteExecutor </b> <br/>
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class RouletteExecutor implements Runnable {
	//Atributs de la classe
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private RouletteView game;
	private Bet aposta;
	private Manager manager;
	
	/**
	 * Constructor del RouletteExecutor.
	 */
	public RouletteExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager) {
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		this.manager = manager;
		active = true;
		game = (RouletteView) manager.getPanel(Constants.R_VIEW_NAME);
		System.out.println("ready");
	}//Tancament del constructor

	@Override
	public void run() {
		try {
			while (active) {
				switch (obtenirInstruccio().getClass().getSimpleName()) {
				case "Check":
					if(!((Check) s).isOk()) new Dialeg().setWarningText("Bet refused");
					else  new Dialeg().setWarningText("Bet accepted");	
					break;	
				case "NotifyBet":
					NotifyBet aposta = ((NotifyBet) s);
					System.err.println("Agreguem al panell lateral " + aposta.getPublicUser().getSurname()
							+ " ha apostat" + aposta.getAposta().getAmount() + " al numero " + aposta.getAposta().getAmount());			
					game.addAtList(aposta.getPublicUser(), aposta.getAposta());
					break;
				case "InitRoulette":
					InitRoulette resultat = ((InitRoulette) s);
					mostragif();
					String winner = "The winner number is... " + resultat.getWinner() + " !";
					manager.showPanel(Constants.MAIN_VIEW_NAME);
					Dialeg d = new Dialeg();
					d.setWarningText(winner +"\nThanks for playing!");
					manager.getServer().enviarTrama(new Disconnect());
					System.exit(0);
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
	
	/**
	 * Getter de Aposta.
	 */
	public Bet getAposta() {
		return aposta;
	}

	/**
	 * Setter de Aposta.
	 */
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

	/**
	 * Metode que no retorna res i que s'encarrega de mostrar el gif.
	 */
	private void mostragif() {
		game.insereixGif();
		// get.enviarTrama(new GameOver());
	}//Tancament del metode
}//Tancament de la classe
