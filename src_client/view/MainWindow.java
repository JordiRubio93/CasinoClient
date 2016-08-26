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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Constants;

/**
 * The Class MainWindow.
 */
public class MainWindow extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private final String logout = "Log Out";

	private Tapet background;
	private JPanel panelTop = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JButton rouletteButton;
	private JButton blackjackButton;
	private JButton horseButton;
	private JButton statisticsButton;
	private JButton configButton = new JButton(logout);
	private ConfigPanel c;
	private JLabel led = new JLabel("  •");

	/**
	 * Instantiates a new main window.
	 *
	 * @param c
	 */
	public MainWindow(ConfigPanel c) {
		initElements();
		this.c = c;
		add(c, BorderLayout.EAST);
		lateralPanel(false);
	}

	/**
	 * Inicialitza els elements.
	 */
	protected void initElements() {
		setLayout(new BorderLayout());
		setBackground(Color.GRAY);

		BufferedImage img1 = null;
		BufferedImage img2 = null;
		BufferedImage img3 = null;
		BufferedImage img4 = null;
		BufferedImage img5 = null;

		try {
			img1 = ImageIO.read(new File("Resources/roulette.png"));
			img2 = ImageIO.read(new File("Resources/horses.png"));
			img3 = ImageIO.read(new File("Resources/blackjack.png"));
			img4 = ImageIO.read(new File("Resources/statistics.png"));
			img5 = ImageIO.read(new File("Resources/userConfiguration.png"));
		} catch (IOException e) {
			System.err.println("Error al carregar, intentant carregar imatge per defecte");
			try {
				img1 = ImageIO.read(new File("Resources/default-image.jpg"));
				img2 = ImageIO.read(new File("Resources/default-image.jpg"));
				img3 = ImageIO.read(new File("Resources/default-image.jpg"));
				img4 = ImageIO.read(new File("Resources/default-image.jpg"));
				img5 = ImageIO.read(new File("Resources/default-image.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		rouletteButton = new JButton();
		rouletteButton.setIcon(new ImageIcon(img1));
		rouletteButton.putClientProperty("action", "Play Roulette");
		rouletteButton.setContentAreaFilled(false);
		rouletteButton.setBorderPainted(false);

		horseButton = new JButton(new ImageIcon(img2));
		horseButton.putClientProperty("action", "Play Horses");
		horseButton.setContentAreaFilled(false);
		horseButton.setBorderPainted(false);

		blackjackButton = new JButton(new ImageIcon(img3));
		blackjackButton.putClientProperty("action", "Play BlackJack");
		blackjackButton.setContentAreaFilled(false);
		blackjackButton.setBorderPainted(false);

		statisticsButton = new JButton(new ImageIcon(img4));
		statisticsButton.putClientProperty("action", "Statistics");
		statisticsButton.setContentAreaFilled(false);
		statisticsButton.setBorderPainted(false);

		configButton = new JButton(new ImageIcon(img5));
		configButton.putClientProperty("action", "Configuration");
		configButton.setContentAreaFilled(false);
		configButton.setBorderPainted(false);

		panelTop.setLayout(new BorderLayout());
		panelTop.setBackground(Color.BLACK);
		panelTop.add(configButton, BorderLayout.EAST);
		panelTop.add(led, BorderLayout.WEST);
		led.setFont(Constants.ledFont);

		panelCenter.setLayout(new GridLayout(1, 4));
		panelCenter.setOpaque(false);
		panelCenter.add(rouletteButton);
		panelCenter.add(horseButton);
		panelCenter.add(blackjackButton);
		panelCenter.add(statisticsButton);

		background = new Tapet(width, height, "resources/fondoMain.jpg");
		background.setLayout(new BorderLayout());
		background.add(panelTop, BorderLayout.NORTH);
		background.add(panelCenter, BorderLayout.CENTER);
		add(background, BorderLayout.CENTER);
	}

	public void registerController() {
		rouletteButton.addActionListener(getManager().getController());
		blackjackButton.addActionListener(getManager().getController());
		horseButton.addActionListener(getManager().getController());
		statisticsButton.addActionListener(getManager().getController());
		configButton.addActionListener(getManager().getController());
		c.setManager(getManager());
		c.registerController();
	}

	/**
	 * Lateral panel.
	 * (Mostra i oculta el panell lateral de configuració.)
	 *
	 * @param open
	 */
	public void lateralPanel(boolean open) {
		c.setVisible(open);
	}

	/**
	 * Gets lateral panel.
	 *
	 * @return lateral panel
	 */
	public ConfigPanel getLateralPanel() {
		return c;
	}

	/**
	 * Sets LED color.
	 * (Canvia el color del LED d'estat de les partides.)
	 *
	 * @param color
	 */
	public void setLEDColor(Color color) {
		led.setForeground(color);
	}
}
