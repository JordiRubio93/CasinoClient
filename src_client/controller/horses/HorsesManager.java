package controller.horses;

import controller.Constants;
import controller.Manager;
import network.ServerComunication;
import view.cavalls.HorsesView;

public class HorsesManager {
	private ServerComunication sc;
	private Manager manager;
	private int time;
	private HorsesView game;
	private PickHorseController hIntro;
	private HorsesExecutor horsesExecutor;
	
	public HorsesManager(Manager manager) {
		this.manager = manager;
		time = 0;
		executaCursa();		
	}
	
	public void executaCursa() {
		hIntro = new PickHorseController(manager);
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
		
		//fil que gestiona el temps y les apostes externes al client
		horsesExecutor = new HorsesExecutor(manager.getServer().getObjectIn(), manager.getServer().getObjectOut(), manager);
		new Thread(horsesExecutor).start();
	}
	
	public PickHorseController getIntro() {
		return hIntro;
	}
	
	public void showPick() {
		getIntro().executaIntro();
	}

	public HorsesExecutor getHorsesExecutor() {
		return horsesExecutor;
	}
}
