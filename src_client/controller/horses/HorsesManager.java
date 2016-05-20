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
import network.segment.Seconds;
import view.cavalls.HorsesView;

public class HorsesManager {
	private LinkedList<HorseData> end;
	private ServerComunication sc;
	private Manager manager;
	private InitHorses initH;
	private int time;
	private HorsesView game;
	private HorsesIntro hIntro;
	private HorsesExecutor horsesExecutor;
	
	
	public HorsesManager(Manager manager, InitHorses initHorses) {
		this.manager = manager;
		initH = initHorses;
		time = 0;
		executaCursa();
	}
	
	public void executaCursa() {
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
		game.actualitzaTemps();
		hIntro = new HorsesIntro(end, manager);
		
		horsesExecutor = new HorsesExecutor(manager.getServer().getObjectIn(), manager.getServer().getObjectOut(), manager);
		try {
			manager.getServer().enviarTrama(new Seconds(0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time = ((Seconds) manager.getServer().obtenirTrama()).getSegons();
		
		
		horsesExecutor.run();
	}	
			
			/*sc.enviarTrama(new Seconds(0));
			time = ((Seconds) sc.obtenirTrama()).getSegons();
			
			game.setCounter();

			do{
				time++;
				
				new Sleeper(this, Constants.MINUT).run();
				
				game.actualitzaCounter(49-time);
			}while(time < 50);
			
			game.showCounter(false);
			game.setCursa();
			game.initHorses(end);
			
			corre();*/
		
	
	
	public HorsesIntro getIntro() {
		return hIntro;
	}

	private void corre(){
		try {
			time = 0;
			while(time < 30){
				time++;
				for(int i = 0; i < Constants.nHorses; i++){
					if(time%2 == 0){
						game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), true), Calcul.calculaY(i));
					}else{
						game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), false), Calcul.calculaY(i));
					}
				}
				
				new Sleeper(this, Constants.DELAY).run();
			}
		
			String winner = new Order().max(end).getName();
			
			game.acabaPartida(winner);
			sc.enviarTrama(new GameOver());
		} catch (IOException e) {}	
	}
}
