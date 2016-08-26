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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.Dialeg;
import controller.Constants;
import controller.Manager;

/**
 * The Class PickHorseView.
 * (Aquí s'escull el cavall pel qual vols apostar.)
 */
public class PickHorseView extends JFrame {
	private static final long serialVersionUID = 1L;
	// main controler identify label
	public static final String next = "  >  ";
	public static final String previous = "  <  ";
	private Manager manager;
	private String[] opcions;
	private int index = 0;
	private JButton jbDreta;
	private JButton jbEsquerra;
	private JButton jbCavall;

	private Dialeg dialeg;
	private JPanel jpEleccio;
	private JTextField jtfAmount;

	/**
	 * Instantiates a new pick horse view.
	 */
	public PickHorseView() {
		initElements();
		dialeg = new Dialeg();
		propietats();
	}

	/**
	 * Instantiates a new pick horse view.
	 *
	 * @param manager
	 */
	public PickHorseView(Manager manager) {
		this.manager = (manager);
		initElements();
		registerController();
		dialeg = new Dialeg();
		propietats();
	}

	/**
	 * Load info.
	 *
	 * @param manager
	 */
	public void loadInfo(Manager manager) {
		opcions = new String[Constants.nHorses];
		for (int i = 0; i < opcions.length; i++) {
			opcions[i] = manager.getGameManager().getHorsesList().get(i).getName();
		}
	}

	/**
	 * Initializes elements.
	 */
	protected void initElements() {

		setLayout(new BorderLayout());

		// boto principal
		jbCavall = new JButton();
		jbCavall.setHorizontalAlignment(JButton.CENTER);
		jbCavall.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbCavall.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbCavall.setFont(new Font("Serif", Font.BOLD, 22));
		jbCavall.setBorder(BorderFactory.createEmptyBorder());
		jbCavall.putClientProperty("action", "This Horse");
		jbCavall.putClientProperty("mouse", "CENTRAL");

		// boto dret
		jbDreta = new JButton(next);
		jbDreta.putClientProperty("action", next);
		jbDreta.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbDreta.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbDreta.setFont(new Font("Serif", Font.BOLD, 26));
		jbDreta.setBorder(BorderFactory.createEmptyBorder());
		jbDreta.putClientProperty("mouse", "R");

		// boto esquerra
		jbEsquerra = new JButton(previous);
		jbEsquerra.putClientProperty("action", previous);
		jbEsquerra.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbEsquerra.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbEsquerra.setFont(new Font("Serif", Font.BOLD, 26));
		jbEsquerra.setBorder(BorderFactory.createEmptyBorder());
		jbEsquerra.putClientProperty("mouse", "L");

		// crem un panell per gestionar la quantitat de les apostes
		jpEleccio = new JPanel(new BorderLayout());
		jpEleccio.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jpEleccio.setBorder(BorderFactory.createEtchedBorder());
		JLabel jlIntro = new JLabel(" Money to bet : ");
		jlIntro.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		jtfAmount = new JTextField();
		jpEleccio.add(jlIntro, BorderLayout.WEST);
		jpEleccio.add(jtfAmount, BorderLayout.CENTER);

		// agreguem
		add(jbCavall, BorderLayout.CENTER);
		add(jbDreta, BorderLayout.EAST);
		add(jbEsquerra, BorderLayout.WEST);
		add(jpEleccio, BorderLayout.SOUTH);
	}

	/**
	 * (Propietats pel panell.)
	 */
	public void propietats() {
		setSize(300, 100);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	/**
	 * Gets amount.
	 *
	 * @return the amount
	 */
	public String getAmount() {
		return jtfAmount.getText();
	}

	/**
	 * (Pinta el boto.)
	 * 
	 * @param jBoto
	 */
	public void pintaBoto(JButton jBoto){
		jBoto.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		if(jBoto.equals(jbCavall)) jBoto.setBackground(new Color(0.56f, 0.73f, 0.56f, 1.0f));
		else jBoto.setBackground(new Color(0.43f, 0.57f, 0.85f, 1.0f));
	}
	
	/**
	 * (Despinta el boto.)
	 * 
	 * @param jBoto
	 */
	public void despintaBoto(JButton jBoto){
		jBoto.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jBoto.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
	}
	
	public JButton getR(){
		return jbDreta;
	}
	public JButton getL(){
		return jbEsquerra;
	}
	public JButton getCentral(){
		return jbCavall;
	}
	
	/**
	 * Passa dreta.
	 */
	public void passaDreta() {
		if (index == 11)
			index = 0;
		else
			index++;
		actualitzaText();
	}

	/**
	 * Passa esquerra.
	 */
	public void passaEsquerra() {
		if (index == 0)
			index = 11;
		else
			index--;
		actualitzaText();
	}

	/**
	 * (Actualitza text de l'etiqueta.)
	 */
	private void actualitzaText() {
		jbCavall.setText(opcions[index]);
	}

	/**
	 * (Obre dialeg de confirmació.)
	 */
	public void obreDialeg() {
		dialeg.setConfirmText("Are you sure you want to bet " + jtfAmount.getText() + " € for this horse?");
	}

	/**
	 * Gets dialeg.
	 *
	 * @return dialeg
	 */
	public Dialeg getDialeg() {
		return dialeg;
	}

	/**
	 * Gets horse name.
	 *
	 * @return horse name
	 */
	public String getHorseName() {
		return jbCavall.getText();
	}

	/**
	 * Sets manager.
	 *
	 * @param manager
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
		registerController();
	}

	/**
	 * Register controller.
	 */
	public void registerController() {
		// aprofitem per carregar les dades
		loadInfo(manager);
		jbEsquerra.addActionListener(manager.getController());
		jbDreta.addActionListener(manager.getController());
		jbCavall.addActionListener(manager.getController());
		jbCavall.addMouseListener(manager.getMouseListener());
		jbDreta.addMouseListener(manager.getMouseListener());
		jbEsquerra.addMouseListener(manager.getMouseListener());
	}

	/**
	 * Clean.
	 */
	public void clean() {
		index = 0;
		jbCavall.setText(opcions[index]);
		jtfAmount.setText(new String());
	}
}
