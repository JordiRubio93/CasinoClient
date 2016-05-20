package controller.horses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import controller.Constants;
import controller.Manager;
import model.Calcul;
import model.Order;
import model.Sleeper;
import model.struct.horses.HorseData;
import network.segment.HorseBetting;
import network.segment.InitHorses;
import network.segment.Segment;
import network.segment.StartGame;
import view.cavalls.HorsesView;

public class HorsesExecutor implements Runnable {
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private Manager manager;
	private HorsesView game;
	private int time = 0;
	private LinkedList<HorseData> end;

	public HorsesExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager) {
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		active = true;
		this.manager = manager;
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
	
	}

	@Override
	public void run() {
		while (active) {
			try {
				if (obtenirInstruccio() instanceof HorseBetting) {
					System.err.println(
					((HorseBetting) s).gethBet().getHorse() + " aposta " + ((HorseBetting) s).gethBet().getAmount() ); // game.ompleLlista(listUsers);
				} else if (obtenirInstruccio() instanceof InitHorses) {
					game.showCounter(false);
					game.setCursa();
					game.initHorses(((InitHorses) s).getList());
				}
				 else if (obtenirInstruccio() instanceof StartGame){
					 corre();
				 }
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void close() {
		active = false;
	}

	public synchronized Segment obtenirInstruccio() throws ClassNotFoundException, IOException {
		s = (Segment) objectIn.readObject();
		System.out.println(s.toString());
		return s;
	}

	private void corre() {
		end = manager.getGameManager().getHorses().getIntro().getList();
		time = 0;
		while (time < 30) {
			time++;
			for (int i = 0; i < Constants.nHorses; i++) {
				if (time % 2 == 0) {
					game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), true), Calcul.calculaY(i));
				} else {
					game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), false), Calcul.calculaY(i));
				}
			}

			new Sleeper(this, Constants.DELAY).run();
		}

		String winner = new Order().max(end).getName();

		game.acabaPartida(winner);
		// get.enviarTrama(new GameOver());
	}

}
