package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.LinkedList;

import model.Calcul;
import model.struct.horses.HorseData;
import network.segment.Check;
import network.segment.InitHorses;
import network.segment.NotifyBet;
import network.segment.Segment;
import view.Dialeg;
import view.cavalls.HorsesView;

public class HorsesExecutor implements Runnable {
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private HorsesView game;
	private int seconds;

	public HorsesExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager) {
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		active = true;
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);

	}

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
							+ " ha apostat " + aposta.getAposta().getAmount() + " a " + aposta.getAposta().getAmount());// TODO
																														// CARDS
					break;
				case "InitHorses":
					game.setCursa();
					InitHorses ih = ((InitHorses) s);
					game.initHorses(ih.getList());
					corre(ih.getList());
					String winner = "The winner horse is... " + ih.getList().get(getWinner(ih.getList())).getName()
							+ " !\n" + " Has guanyat" + ih.getGuanys();
					game.acabaPartida(winner);
					break;
				default:
					System.err.println("pero esto que coño es?");
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

	// TODO DURAR 10 segons per enunciaat!!
	private void corre(LinkedList<HorseData> end) {
		Thread thread = new Thread() {
			public void run() {
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
			}
		};
		thread.start();
		// get.enviarTrama(new GameOver());
	}

	/**
	 * David idioto no diu si es '>' o <!! se supone que mes petit
	 * 
	 * @param ih
	 *            lista de datos de caballos
	 * @return index ganador
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

	public int getSeconds() {
		return seconds;
	}
}
