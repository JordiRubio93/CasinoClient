/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package view;

import java.util.ArrayList;

import view.blackjack.BlackjackView;
import view.cashevo.CashEvoView;
import view.horses.HorsesView;
import view.horses.PickHorseView;
import view.roulette.RouletteView;
import view.statistics.CashRankingWindow;
import view.statistics.Graphics;
import view.statistics.StatisticsWindow;

/**
 * The Class SplashScreen.
 * (Gestiona la vista del Splash Screen.)
 */
public class SplashScreen {
	private ArrayList<BaseJPanel> panels;
	private Boolean loaded = Boolean.FALSE;
	private TranslucentWindow translucentWindow;

	/**
	 * Instantiates a new splash screen.
	 */
	public SplashScreen() {
		translucentWindow = new TranslucentWindow();
		new Thread(translucentWindow).run();
		panels = new ArrayList<BaseJPanel>();
		panels.add(new Graphics()); // 0
		panels.add(new RouletteView()); // 1
		panels.add(new HorsesView(new PickHorseView())); // 2
		panels.add(new BlackjackView()); // 3
		panels.add(new StatisticsWindow()); // 4
		panels.add(new MainWindow(new ConfigPanel())); // 5
		panels.add(new LoginWindow()); // 6
		panels.add(new CashRankingWindow()); // 7
		panels.add(new CashEvoView());// 8
		loaded = Boolean.TRUE;
	}

	/**
	 * Gets loaded.
	 *
	 * @return loaded
	 */
	public Boolean getLoaded() {
		return loaded;
	}

	/**
	 * Gets translucent window.
	 *
	 * @return translucent window
	 */
	public TranslucentWindow getTranslucentWindow() {
		return translucentWindow;
	}

	/**
	 * Sets translucent window.
	 *
	 * @param translucentWindow
	 */
	public void setTranslucentWindow(TranslucentWindow translucentWindow) {
		this.translucentWindow = translucentWindow;
	}

	/**
	 * Gets panels.
	 *
	 * @return panels
	 */
	public ArrayList<BaseJPanel> getPanels() {
		return panels;
	}
}
