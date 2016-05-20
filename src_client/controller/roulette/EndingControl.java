package controller.roulette;

import java.io.IOException;

import controller.Manager;
import network.segment.Seconds;
import view.roulette.RouletteView;
import network.segment.GameOver;
import network.segment.InitRoulette;
import network.segment.Play;

public class EndingControl implements Runnable {
	private Manager manager;
	private RouletteView view;
	private int num;

	public EndingControl(Manager manager, RouletteView view) {
		this.manager = manager;
		this.view = view;
	}

	@Override
	public void run() {
		try {
			while(true){
				manager.getServer().enviarTrama(new Seconds(0));
				int sec = ((Seconds)manager.getServer().obtenirTrama()).getSegons();
				
				if(sec >= 49) break;
			}
			
			view.insereixGif();
			
			//TODO	manager.setPanel(view);

			manager.getServer().enviarTrama(new Play("roulette"));
			num = ((InitRoulette)manager.getServer().obtenirTrama()).getNum();

			Thread.sleep(10000);
			
			AmericanRoulette americanRoulette = new AmericanRoulette();
						for(int i = 0; i < americanRoulette.getCaselles().size(); i++){
				if(americanRoulette.getCaselles().get(i).getNumero() == num){
					view.acabaPartida(num, americanRoulette.getColorCaselles()[i]);
					break;
				}
			}
			
			manager.getServer().enviarTrama(new GameOver());
			manager.getGameManager().closeRuleta();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
