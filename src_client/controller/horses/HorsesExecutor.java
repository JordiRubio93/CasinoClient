package controller.horses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.LinkedList;

import controller.Constants;
import controller.Manager;
import model.Calcul;
import model.Order;
import model.struct.horses.HorseData;
import network.segment.HorseBetting;
import network.segment.InitHorses;
import network.segment.Segment;
import view.cavalls.HorsesView;

public class HorsesExecutor implements Runnable {
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private Manager manager;
	private HorsesView game;
	private int seconds;
	private LinkedList<HorseData> end;
	private PickHorseController hIntro;

	public HorsesExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager) {
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		active = true;
		this.manager = manager;
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
	}

	@Override
	public void run() {
		try {
			while (active) {
				switch (obtenirInstruccio().getClass().getSimpleName()) {
				case "HorseBetting":System.out.println(1);
					System.err.println(
						((HorseBetting) s).gethBet().getHorse() + " aposta " + ((HorseBetting) s).gethBet().getAmount() ); // game.ompleLlista(listUsers);
					break;
				case "InitHorses":System.out.println(2);
					System.err.println("cash bixis " +((InitHorses) s).isApostable());
					//game.showCounter(false);
					game.setCursa();
					game.initHorses(((InitHorses) s).getList());
					corre(((InitHorses) s).getList());
					break;
				default:
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

	private void corre(LinkedList<HorseData> end) {
		Thread thread = new Thread() {
			public void run() {
				seconds = 0;
				while (seconds < 30) {
					seconds++;
					for (int i = 0; i < Constants.nHorses; i++) {
						if (seconds % 2 == 0) {
							game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), true), Calcul.calculaY(i));
						} else {
							game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), false), Calcul.calculaY(i));
						}
					}
					try {
						Thread.sleep(Constants.DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				String winner = new Order().max(end).getName();
				game.acabaPartida(winner);
			}
		};
		thread.start();
		
		// get.enviarTrama(new GameOver());
	}

	public int getSeconds() {
		return seconds;
	}
}
