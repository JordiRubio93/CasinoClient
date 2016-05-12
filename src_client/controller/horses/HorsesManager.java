package controller.horses;

import java.io.IOException;
import java.util.LinkedList;

import controller.listeners.BetButtonController;
import model.Calcul;
import model.Constants;
import model.Order;
import model.struct.horses.HorseData;
import model.struct.user.User;
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
	private InitHorses initH;
	private int sec;
	private HorsesView game;
	
	public void executaCursa(LinkedList<User> listUsers) {		
		
		game.actualitzaTemps();
		game.setVisible(true);
		
		game.ompleLlista(listUsers);
		
		try {
			sc.enviarTrama(new Play("horses"));
			initH = (InitHorses) sc.obtenirTrama();
			end = initH.getList();
			
			HorsesIntro hIntro = new HorsesIntro(end);
			BetButtonController bbc = new BetButtonController(game, hIntro);
			game.registerController(bbc);
			
			sc.enviarTrama(new Seconds(0));
			sec = ((Seconds) sc.obtenirTrama()).getSegons();
			
			game.setHorseCounter();
			
			do{
				sec++;
				Thread.sleep(1000);
				game.actualitzaCounter(49-sec);
			}while(sec < 50);
			
			game.showCounter(false);
			game.setCursa(colors);
			game.initHorses(end);
			
			corre();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void corre(){
		try {
			sec = 0;
			while(sec < 30){
				sec++;
				for(int i = 0; i < Constants.nHorses; i++){
					if(sec%2 == 0){
						game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), true), Calcul.calculaY(i));
					}else{
						game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), false), Calcul.calculaY(i));
					}
				}
				
				Thread.sleep(Constants.DELAY);
			}
		
			game.acabaPartida(new Order().max(end).getName());
			sc.enviarTrama(new GameOver());
		} catch (InterruptedException | IOException e) {}	
	}
	
	public HorsesManager(ServerComunication sc){
		this.sc = sc;
		game = new HorsesView();
		sec = 0;
	}

	public HorsesView getGame() {
		return game;
	}

	public void setGame(HorsesView game) {
		this.game = game;
	}
	
	
}
