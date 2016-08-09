package view;

import java.util.ArrayList;

import view.blackjack.BlackjackView;
import view.cavalls.HorsesView;
import view.cavalls.PickHorseView;
import view.roulette.RouletteView;
import view.statistics.CashRankingWindow;
import view.statistics.Graphics;
import view.statistics.StatisticsWindow;
/**
 * 
 * <p>
 * <b> Classe: SplashScreen </b> <br/>
 * </p>
 * 
 * Finestra de Splash que es fa servir mentre es carreguen els panells
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class SplashScreen{

	private ArrayList<BaseJPanel> panels;
	private Boolean loaded = Boolean.FALSE;
	private TranslucentWindow translucentWindow;
	
	public SplashScreen(){
		translucentWindow = new TranslucentWindow();
		new Thread(translucentWindow).run();
		panels = new ArrayList<BaseJPanel>();
		panels.add(new Graphics()); //0
		panels.add(new RouletteView()); //1
		panels.add(new HorsesView(new PickHorseView())); //2
		panels.add(new BlackjackView()); //3
		panels.add(new StatisticsWindow()); //4	
		panels.add(new MainWindow(new ConfigPanel())); //5
		panels.add(new LoginWindow()); //6
		panels.add(new CashRankingWindow()); //7
		loaded = Boolean.TRUE;
	}
	
	public Boolean getLoaded() {
		return loaded;
	}
	
	public TranslucentWindow getTranslucentWindow() {
		return translucentWindow;
	}

	public void setTranslucentWindow(TranslucentWindow translucentWindow) {
		this.translucentWindow = translucentWindow;
	}

	public ArrayList<BaseJPanel> getPanels() {
		return panels;
	}
}
