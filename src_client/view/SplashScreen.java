package view;

import java.util.ArrayList;

import view.blackjack.BlackjackView;
import view.cavalls.HorsesView;
import view.roulette.RouletteView;
import view.statistics.Graphics;
import view.statistics.StatisticsWindow;

public class SplashScreen{

	private ArrayList<BaseJPanel> panels;
	private Boolean loaded = Boolean.FALSE;
	private TranslucentWindow translucentWindow;
	
	public SplashScreen(){
		translucentWindow = new TranslucentWindow();
		new Thread(translucentWindow).run();
		panels = new ArrayList<BaseJPanel>();
		panels.add(new Graphics());
		panels.add(new RouletteView());
		panels.add(new HorsesView());
		panels.add(new BlackjackView());
		panels.add(new StatisticsWindow());
		//panels.add(null);		
		panels.add(new MainWindow(new ConfigPanel()));
		panels.add(new LoginWindow());
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
