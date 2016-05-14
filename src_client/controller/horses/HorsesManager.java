package controller.horses;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import controller.Manager;
import controller.listeners.BetButtonController;
import model.Calcul;
import model.Constants;
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
	//private HorsesBet aposta;
	private ServerComunication sc;
	private Manager manager;
	private InitHorses initH;
	private int time;
	private HorsesView game;
	
	public void executaCursa(LinkedList<PublicUser> listUsers) {
		game = (HorsesView) manager.getPanel();
		game.actualitzaTemps();
		game.setVisible(true);
		
		game.ompleLlista(listUsers);
		
		try {
			sc = game.getManager().getServer();
			sc.enviarTrama(new Play("horses"));
			initH = (InitHorses) sc.obtenirTrama();
			end = initH.getList();
			
			HorsesIntro hIntro = new HorsesIntro(end, sc);
			BetButtonController bbc = new BetButtonController(game, hIntro, sc);
			game.registerController(bbc);
			
			sc.enviarTrama(new Seconds(0));
			time = ((Seconds) sc.obtenirTrama()).getSegons();
			
			game.setCounter();

			new Timer().scheduleAtFixedRate(new TimerTask(){
				public void run() {
					if(time < 49){
						time++;
						game.actualitzaCounter(49-time);
					}else{
						game.showCounter(false);
						game.setCursa(colors);
						game.initHorses(end);
						
						corre();
						
						this.cancel();
					}
				}
			}, 0, 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public HorsesManager(Manager manager) {
		this.manager = manager;
		time = 0;
	}

	public HorsesView getGame() {
		return new HorsesView();
	}

	public void setGame(HorsesView game) {
		this.game = game;
	}
}
