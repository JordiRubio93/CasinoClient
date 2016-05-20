package controller.horses;

import java.io.IOException;
import java.util.LinkedList;

import controller.Constants;
import controller.Manager;
import model.Calcul;
import model.Order;
import model.Sleeper;
import model.struct.horses.HorseData;
import network.ServerComunication;
import network.segment.GameOver;
import network.segment.InitHorses;
import view.cavalls.HorsesView;

public class HorsesManager {
	private LinkedList<HorseData> end;
	private ServerComunication sc;
	private Manager manager;
	private int time;
	private HorsesView game;
	private PickHorseController hIntro;
	private HorsesExecutor horsesExecutor;
	
	public HorsesManager(Manager manager, InitHorses initHorses) {
		this.manager = manager;
		this.end = initHorses.getList();
		time = 0;
		executaCursa();		
	}
	
	public void executaCursa() {
		hIntro = new PickHorseController(end, manager);
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
		//fill que gestiona el temps y les apostes externes al client
		horsesExecutor = new HorsesExecutor(manager.getServer().getObjectIn(), manager.getServer().getObjectOut(), manager);
		new Thread(horsesExecutor).start();
	}	
			
			/*sc.enviarTrama(new Seconds(0));
			time = ((Seconds) sc.obtenirTrama()).getSegons();
			
			game.setCounter();

			
			game.showCounter(false);
			game.setCursa();
			game.initHorses(end);
			
			corre();*/
		
	
	
	public PickHorseController getIntro() {
		return hIntro;
	}

	

	public void showPick() {
		getIntro().executaIntro();
		// TODO Auto-generated method stub
		
	}
}
