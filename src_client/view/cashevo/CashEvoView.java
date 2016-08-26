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

package view.cashevo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.BaseJPanel;
import controller.Constants;

/**
 * The Class CashEvoView.
 */
public class CashEvoView extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private JButton backButton = new JButton("Back");
	private JPanel panelTop = new JPanel();
	private JPanel panel = new JPanel(new BorderLayout());
	private LineChart chart;
	private JPanel panelBottom = new JPanel(new GridLayout());
	private JButton jbAll = new JButton("All");
	private JButton jbGlobal = new JButton("Global");
	private JButton jbRoulette = new JButton("Roulette");
	private JButton jbHorses = new JButton("Horse race");
	private JButton jbBJ = new JButton("Blackjack");

	/**
	 * Instantiates a new cash evo view.
	 */
	public CashEvoView() {
		initElements();
	}

	@Override
	public void registerController() {
		backButton.addActionListener(getManager().getController());
		jbAll.addActionListener(getManager().getController());
		jbGlobal.addActionListener(getManager().getController());
		jbRoulette.addActionListener(getManager().getController());
		jbHorses.addActionListener(getManager().getController());
		jbBJ.addActionListener(getManager().getController());
	}

	@Override
	protected void initElements() {
		setLayout(new BorderLayout());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		backButton.setFont(Constants.boldFont);
		backButton.setBackground(Constants.coolGreen);
		backButton.setContentAreaFilled(true);
		backButton.setBorderPainted(false);
		backButton.setPreferredSize(new Dimension((int) (width * 0.18), (int) (height * 0.06)));

		panelTop.setBackground(Color.BLACK);
		panelTop.add(backButton, 0);

		jbAll.setBackground(Color.WHITE);
		jbAll.setForeground(Color.BLACK);
		jbAll.putClientProperty("action", "ALL BUTTON");
		jbGlobal.setBackground(Constants.coolRed);
		jbGlobal.setForeground(Color.WHITE);
		jbGlobal.putClientProperty("action", "GLOBAL BUTTON");
		jbRoulette.setBackground(Constants.coolOrange);
		jbRoulette.setForeground(Color.WHITE);
		jbRoulette.putClientProperty("action", "ROULETTE BUTTON");
		jbHorses.setBackground(Constants.coolDarkGreen);
		jbHorses.setForeground(Color.WHITE);
		jbHorses.putClientProperty("action", "HORSES BUTTON");
		jbBJ.setBackground(Constants.coolIndigo);
		jbBJ.setForeground(Color.WHITE);
		jbBJ.putClientProperty("action", "BJ BUTTON");

		panelBottom.add(jbAll);
		panelBottom.add(jbGlobal);
		panelBottom.add(jbRoulette);
		panelBottom.add(jbHorses);
		panelBottom.add(jbBJ);

		chart = new LineChart(width, height, Constants.PATH_WALL);
		chart.setLayout(new BorderLayout());
		panel.add(chart, BorderLayout.CENTER);

		add(panelTop, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(panelBottom, BorderLayout.SOUTH);
	}

	/**
	 * Adds point.
	 *
	 * @param x
	 * @param y
	 * @param first
	 * @param joc
	 */
	public void addPoint(float x, float y, boolean first, int joc) {
		chart.addPoint(x, y, first, joc);
	}

	/**
	 * Adds string.
	 *
	 * @param s
	 * @param joc
	 */
	public void addString(String s, int joc) {
		chart.addString(s, joc);
	}

	/**
	 * (Elimina tots els punts de la gràfica.)
	 *
	 * @param joc
	 */
	public void removeAllPoints(int joc) {
		chart.removeAllPoints(joc);
	}

	/**
	 * Gets panel height.
	 *
	 * @return panel height
	 */
	public int getPanelHeight() {
		return panel.getHeight();
	}

	/**
	 * Sets lines.
	 *
	 * @param show
	 * @param joc
	 */
	public void setLines(boolean show, int joc) {
		chart.addLines(show, joc);
	}

	/**
	 * Sets user name.
	 *
	 * @param name
	 */
	public void setUserName(String name) {
		chart.setUserName(name);
	}

	/**
	 * Sets back.
	 * (Decideix a quina pantalla s'ha d'anar al clicar Back.)
	 *
	 * @param ranking
	 */
	public void setBack(boolean ranking) {
		if (ranking)
			backButton.putClientProperty("action", "Back To Cash Evo");
		else
			backButton.putClientProperty("action", "Home");
	}

	/**
	 * (Reseteja el gràfic.)
	 */
	public void reset() {
		chart.reset();
	}
}
