package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private JButton logOutButton = new JButton(logout);

	public MainWindow(){
		initElements();
	}
	
	private void initElements(){
		setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		BufferedImage img3 = null;
		BufferedImage img4 = null;
		
		try {
			img1 = ImageIO.read(new File("Resources/logoRoulette.png"));
			img2 = ImageIO.read(new File("Resources/logoHorse.png"));
			img3 = ImageIO.read(new File("Resources/logoBlackjack.png"));
			img4 = ImageIO.read(new File("Resources/logoStatistics.png"));
		} catch (IOException e) {
			System.err.println("Error al carregar, intentant carregar imatge per defecte");
			try {
				img1 = ImageIO.read(new File("Resources/default-image.jpg"));
				img2 = ImageIO.read(new File("Resources/default-image.jpg"));
				img3 = ImageIO.read(new File("Resources/default-image.jpg"));
				img4 = ImageIO.read(new File("Resources/default-image.jpg"));
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
		blackjackButton.putClientProperty("action", "Play BlackJac");
		blackjackButton.setContentAreaFilled(false);
		blackjackButton.setBorderPainted(false);
		
		statisticsButton = new JButton(new ImageIcon (img4));
		statisticsButton.putClientProperty("action", "Statistics");
		statisticsButton.setContentAreaFilled(false);
		statisticsButton.setBorderPainted(false);
		
		logOutButton.setFont(Constants.boldFont);
		logOutButton.setBackground(Constants.coolGreen);
		logOutButton.setContentAreaFilled(true);
		logOutButton.setBorderPainted(false);
		logOutButton.putClientProperty("action", "Log Out");
		logOutButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		
		panelTop.setLayout(new FlowLayout());
		panelTop.setBackground(Constants.semiOpaqueBlack);
		panelTop.add(logOutButton, 0);
		
		panelCenter.setLayout(new GridLayout(2, 2));
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
		logOutButton.addActionListener(listener);
	}

	@Override
	public void setManager(Manager manager) {
		registerController(manager.getButtonListener());
	}
	
}