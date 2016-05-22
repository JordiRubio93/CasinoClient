package view;

import java.util.ArrayList;

import view.blackjack.BlackjackView;
import view.cavalls.HorsesView;
import view.cavalls.PickHorseView;
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
		panels.add(new Graphics()); //0
		panels.add(new RouletteView()); //1
		panels.add(new HorsesView(new PickHorseView())); //2
		panels.add(new BlackjackView()); //3
		panels.add(new StatisticsWindow()); //4	
		panels.add(new MainWindow(new ConfigPanel())); //5
		panels.add(new LoginWindow()); //6
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
