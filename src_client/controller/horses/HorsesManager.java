package controller.horses;

import java.io.IOException;
import java.util.LinkedList;

import controller.Constants;
import controller.Manager;
import model.Calcul;
import model.Order;
import model.struct.horses.HorseData;
import model.struct.user.PublicUser;
import network.ServerComunication;
import network.segment.GameOver;
import network.segment.InitHorses;
import network.segment.Play;
import network.segment.Seconds;
import view.cavalls.HorsesView;

public class HorsesManager {
	private LinkedList<HorseData> end;
	private LinkedList<String> colors;
	private ServerComunication sc;
	private Manager manager;
	private InitHorses initH;
	private int time;
	private HorsesView game;
	private HorsesIntro hIntro;
	
	public HorsesManager(Manager manager) {
		this.manager = manager;
		time = 0;
	}
	
	public void executaCursa(LinkedList<PublicUser> listUsers) {
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
		game.actualitzaTemps();
		
		//game.ompleLlista(listUsers);
		
		try {
			sc = game.getManager().getServer();
			
			sc.enviarTrama(new Play("horses"));
			initH = (InitHorses) sc.obtenirTrama();
			end = initH.getList();
			
			hIntro = new HorsesIntro(end, manager);
			
			sc.enviarTrama(new Seconds(0));
			time = ((Seconds) sc.obtenirTrama()).getSegons();
			
			game.setCounter();

			do{
				time++;
				Thread.sleep(1000);
				game.actualitzaCounter(49-time);
			}while(time < 50);
			
			game.showCounter(false);
			game.setCursa(colors);
			game.initHorses(end);
			
			corre();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
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
				
				Thread.sleep(Constants.DELAY);
			}
		
			String winner = new Order().max(end).getName();
			
			game.acabaPartida(winner);
			sc.enviarTrama(new GameOver());
		} catch (InterruptedException | IOException e) {}	
	}
}
