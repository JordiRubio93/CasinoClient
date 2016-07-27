package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import model.Calcul;
import model.struct.horses.HorseData;
import network.segment.Check;
import network.segment.Disconnect;
import network.segment.InitHorses;
import network.segment.NotifyBet;
import network.segment.Seconds;
import network.segment.Segment;
import view.Dialeg;
import view.cavalls.HorsesView;

/**
 * 
 * <p>
 * <b> Classe: HorsesExecutor </b> <br/>
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
public class HorsesExecutor implements Runnable {
	//Atributs de la classe
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private HorsesView game;
	private int seconds;
	private Manager manager;
	private int sec;

	/**
	 * Constructor pel HorsesExecutor.
	 * @param objectIn, objectOut, manager (respectivament, l'objecte d'entrada, el de sortida i el manager que els controla)
	 */
	public HorsesExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager) {
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		this.manager = manager;
		active = true;
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
	}//Tancament del constructor

	@Override
	public void run() {
		try {
			while (active) {
				switch (obtenirInstruccio().getClass().getSimpleName()) {
				case "Check":
					if (!((Check) s).isOk())
						new Dialeg().setWarningText("Bet refused");
					else
						new Dialeg().setWarningText("Bet accepted");
					break;
				case "NotifyBet":
					NotifyBet aposta = ((NotifyBet) s);
					System.err.println("Agreguem al panell lateral " + aposta.getPublicUser().getSurname()
							+ " ha apostat " + aposta.getAposta().getAmount() + " a " + aposta.getAposta().getAmount());
					game.addAtList(aposta.getPublicUser(), aposta.getAposta());
					
					break;
				case "InitHorses":
					game.setCursa();
					InitHorses ih = ((InitHorses) s);
					game.initHorses(ih.getList());
					corre(ih.getList());
					String winner = "The winner horse is... " + ih.getList().get(getWinner(ih.getList())).getName().toUpperCase()	+ " !";
					game.acabaPartida(winner);
					manager.showPanel(Constants.MAIN_VIEW_NAME);
					Dialeg d = new Dialeg();
					d.setWarningText(winner +"\nThanks for playing!");
					manager.getServer().enviarTrama(new Disconnect());
					System.exit(0);
					
					break;
				case "Seconds":
					sec = ((Seconds) s).getSegons();
					if(sec <= 45 && sec >= 0){
						game.setCounter();
						game.setActual(sec);
						
						new Timer().scheduleAtFixedRate(new TimerTask(){
							public void run() {
								if(sec < 45){
									sec++;
									game.actualitzaCounter(45-sec);
									game.actualitzaProgressBar(sec);
								}else{
									game.showCounter(false);
									this.cancel();
								}
								
								if((45-sec) == 1 || (45-sec) == 3 || (45-sec) == 5) game.paintRed(true);
								else if((45-sec) < 5) game.paintRed(false);
							}
						}, 0, 1000);
					}
					
					break;
				default:
					System.err.println("pero esto que co�o es?");
					break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metode que no retorna res i que s'encarrega d'aturar l'executor.
	 */
	public void close() {
		active = false;
	}//Tancament del metode

	
	public synchronized Segment obtenirInstruccio() throws ClassNotFoundException, IOException {
		s = (Segment) objectIn.readObject();
		System.out.println(Calendar.getInstance().getTime().toString() + " soc un " + s.getClass());
		return s;
	}
	
	/**
	 * Metode que no retorna res, rep una LinkedList<HorseData> i s'encarrega de tractar el moviment de correr dels cavalls.
	 * @param end (Llista de HorseData)
	 */
	private void corre(LinkedList<HorseData> end) {
		seconds = 0;
		while (seconds < 30) {
			seconds++;
			for (int i = 0; i < Constants.nHorses; i++) {
				if (seconds % 2 == 0)
					game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), true), Calcul.calculaY(i));
				else
					game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), false), Calcul.calculaY(i));
			}
			try {
				Thread.sleep(Constants.DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// get.enviarTrama(new GameOver());
	}//Tancament del metode

	/**
	 * Metode que retorna un int, rep una LinkedList<HorseData> i s'encarrega de retornar el guanyador.
	 * 
	 * @param ih (lista de datos de caballos)
	 * @return win (index ganador)
	 */
	public int getWinner(LinkedList<HorseData> ih) {
		int win = 0;
		int min = Integer.MIN_VALUE;
		for (int i = 0; i < ih.size(); i++) {
			if (ih.get(i).getSegons() > min) {
				min = ih.get(i).getSegons();
				win = i;
			}
		}
		return win;
	}

	/**
	 * Getter de Seconds.
	 */
	public int getSeconds() {
		return seconds;
	}//Tancament del Getter
}//Tancament de la classe
