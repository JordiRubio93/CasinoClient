package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Manager;
import controller.listeners.MainButtonsController;
import model.Constants;

public class MainWindow extends BaseJPanel {
	private final String logout = "Log Out";
	private static final long serialVersionUID = 1L;
	
	private Tapet background;
	private JPanel panelTop = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JButton rouletteButton;
	private JButton blackjackButton;
	private JButton horseButton;
	private JButton statisticsButton;
	private JButton configButton = new JButton(logout);
	private ConfigPanel c = new ConfigPanel();
	private JLabel labelUp;

	public MainWindow(){
		initElements();
	}
	
	public MainWindow(ConfigPanel c){
		initElements();
		this.c = c;
		add(this.c, BorderLayout.EAST);
	}
	
	private void initElements(){
		setLayout(new BorderLayout());
		setBackground(Color.GRAY);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
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
		rouletteButton.setIcon(new ImageIcon (img1));
		rouletteButton.putClientProperty("action", "Play Roulette");
		rouletteButton.setContentAreaFilled(false);
		rouletteButton.setBorderPainted(false);
		
		horseButton = new JButton(new ImageIcon (img2));
		horseButton.putClientProperty("action", "Play Horses");
		horseButton.setContentAreaFilled(false);
		horseButton.setBorderPainted(false);
		
		blackjackButton = new JButton(new ImageIcon (img3));
		blackjackButton.putClientProperty("action", "Play BlackJack");
		blackjackButton.setContentAreaFilled(false);
		blackjackButton.setBorderPainted(false);
		
		statisticsButton = new JButton(new ImageIcon (img4));
		statisticsButton.putClientProperty("action", "Statistics");
		statisticsButton.setContentAreaFilled(false);
		statisticsButton.setBorderPainted(false);
		
		configButton = new JButton(new ImageIcon (img5));
		configButton.putClientProperty("action", "Configuration");
		configButton.setContentAreaFilled(false);
		configButton.setBorderPainted(false);
		
		ImageIcon img = new ImageIcon(Constants.LABEL);
		labelUp = new JLabel(img);
		panelTop.setLayout(new BorderLayout());
		panelTop.setBackground(Color.BLACK);
		panelTop.add(labelUp, BorderLayout.CENTER);
		panelTop.add(configButton, BorderLayout.EAST);
		
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
	
	public void registerController(MainButtonsController listener){
		rouletteButton.addActionListener(listener);
		blackjackButton.addActionListener(listener);
		horseButton.addActionListener(listener);
		statisticsButton.addActionListener(listener);
		configButton.addActionListener(listener);

	}

	@Override
	public void setManager(Manager manager) {
		registerController(manager.getButtonListener());
		c.registerController(manager.getButtonListener());
	}
	
}