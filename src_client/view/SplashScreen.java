package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import view.blackjack.BlackjackView;
import view.cavalls.HorsesView;
import view.roulette.RouletteView;
import view.statistics.Graphics;
import view.statistics.StatisticsWindow;

public class SplashScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private ArrayList<BaseJPanel> panels;
	private Boolean loaded = Boolean.FALSE;
	
	public SplashScreen(/*manager?*/){
		//TODO pintar...
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension splashSize = new Dimension(screenSize.width/2, screenSize.height/2);
		
		propietats(splashSize);
		panels = new ArrayList<BaseJPanel>();
		panels.add(new Graphics());
		panels.add(new RouletteView());
		panels.add(new HorsesView());
		panels.add(new BlackjackView());
		panels.add(new StatisticsWindow());
		panels.add(new GameView());		
		panels.add(new MainWindow(new ConfigPanel()));
		panels.add(new LoginWindow());
		
		loaded = Boolean.TRUE;
	}
	
	public Boolean getLoaded() {
		return loaded;
	}

	private void propietats(Dimension splashSize){
		setSize(splashSize);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public ArrayList<BaseJPanel> getPanels() {
		return panels;
	}
}
