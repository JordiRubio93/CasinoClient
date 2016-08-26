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

package view.horses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Calcul;
import model.struct.horses.HorseData;
import view.GameView;
import controller.Constants;

/**
 * The Class HorsesView.
 * (Classe encarregada de la vista de la cursa de cavalls.)
 */
public class HorsesView extends GameView {
	private static final long serialVersionUID = 1L;
	private Stadium jpStadium; // Hereta de Tapet
	private GridLayout gridLayout;
	private JPanel jpCarrils;
	private LinkedList<HorseAnimation> list; // Animacions per l'sprite
	private Point[] coord; // Coordenades xy segons el cavall corresponent
	private PickHorseView phv; // Vista per escollir el cavall pel qual apostes

	/**
	 * Instantiates a new horses view.
	 *
	 * @param phv
	 */
	public HorsesView(PickHorseView phv) {
		initElements();
		createDaemonTime(); // Crea el control de temps per la cursa
		this.phv = phv;
	}

	/**
	 * (Inicialitza els elements del panell.)
	 */
	protected void initElements() {
		super.initElements();
		jpStadium = new Stadium(width, height, Constants.PATH_TAPET);
		jpStadium.setLayout(new BorderLayout());
		add(jpStadium, BorderLayout.CENTER);
		jbBet.putClientProperty("action", "BET_H");
		jbPredict.putClientProperty("action", "EXIT_H");
	}

	/**
	 * (Estableix la cursa a lloc abans de que comenci.)
	 */
	public void setCursa() {
		jpStadium.setImatge(Constants.PATH_CARRILS);
		jpStadium.setDimensions();
		gridLayout = new GridLayout(Constants.nHorses, 1);
		gridLayout.setVgap(-80);
		jpCarrils = new JPanel(gridLayout);
		for (int i = 0; i < Constants.nHorses; i++) {
			JLabel jlPos = new JLabel("   " + String.valueOf(Constants.nHorses - i) + "  ");
			jlPos.setHorizontalAlignment(JLabel.LEFT);
			jlPos.setFont(new Font("Serif", Font.BOLD, 34));
			jlPos.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
			jpCarrils.add(jlPos);
		}
		jpCarrils.setBackground(new Color(0, 0, 0, 0));
		jpStadium.add(jpCarrils, BorderLayout.WEST);
		add(jpStadium, BorderLayout.CENTER);
		showCursa(true);
	}

	/**
	 * (Mostra o oculta els carrils.)
	 *
	 * @param show
	 */
	public void showCursa(boolean show) {
		jpCarrils.setVisible(show);
	}

	/**
	 * (Inicialitza els cavalls per a la cursa.)
	 *
	 * @param dades
	 */
	public void initHorses(LinkedList<HorseData> dades) {
		list = new LinkedList<HorseAnimation>();
		coord = new Point[12];
		// A partir de les dades requerides, calcula la nova posició
		for (int i = 0; i < Constants.nHorses; i++) {
			list.add(new HorseAnimation(dades.get(i).getColor(), i));
			coord[i] = new Point(Calcul.calculaX(dades.get(i).getSegons(), true), Calcul.calculaY(i));
		}
		jpStadium.setList(list);
		jpStadium.setCoordList(coord);
		jpStadium.setReady(true);
		jpStadium.repaint();
	}

	/**
	 * (Anima i trasllada els cavalls: fa que corrin.)
	 *
	 * @param i
	 * @param x
	 * @param y
	 */
	public void runHorses(int i, int x, int y) {
		jpStadium.getList().get(i).run(); // Mou els cavalls en sí, les seves potes, els anima
		jpStadium.setCoord(x, y, i); // Trasllada els cavalls de posició perquè avancin
	}

	/**
	 * (Inicialitza l'etiqueta del compte enrere.)
	 */
	public void setCounter() {
		jlCount = new JLabel("...");
		jlCount.setHorizontalAlignment(JLabel.CENTER);
		jlCount.setVerticalAlignment(JLabel.CENTER);
		jlCount.setFont(new Font("Serif", Font.BOLD, 70));
		jlCount.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlCount.setBackground(Color.RED);
		jpStadium.add(jlCount, BorderLayout.CENTER);
	}

	/**
	 * (Mostra o oculta l'etiqueta del compte enrere.)
	 *
	 * @param show
	 */
	public void showCounter(boolean show) {
		jlCount.setVisible(show);
	}

	/**
	 * (Actualitza l'etiqueta del compte enrere.)
	 *
	 * @param num
	 */
	public void actualitzaCounter(int num) {
		jlCount.setText(String.valueOf(num));
	}

	/**
	 * (Pinta la pantalla en vermell en els últims segons abans de la cursa.)
	 *
	 * @param
	 */
	public void paintRed(boolean flag) {
		jlCount.setOpaque(flag);
	}

	/**
	 * Show this window.
	 */
	public void showPhv() {
		phv.clean();
		phv.setVisible(true);
	}

	/**
	 * Gets phv.
	 *
	 * @return phv
	 */
	public PickHorseView getPhv() {
		return phv;
	}

	/**
	 * @see view.GameView#registerController()
	 */
	@Override
	public void registerController() {
		// registrar el frame
		super.registerController();
		phv.setManager(getManager());
	}

	/**
	 * Enable bet.
	 */
	public void enableBet() {
		jbBet.setEnabled(true);
	}

	/**
	 * @see view.GameView#disableBet()
	 */
	public void disableBet() {
		phv.setVisible(false);
		jbBet.setEnabled(false);
	}

	/**
	 * (Reinicia la vista.)
	 */
	public void reset() {
		removeAll();
		initElements();
		super.registerController();
		createDaemonTime();
		revalidate();
		repaint();
	}
}
