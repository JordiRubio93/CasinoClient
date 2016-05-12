package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import controller.listeners.ButtonListener;

public class MainWindow extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private Tapet background;
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private Manager manager;
	private JButton rouletteButton;
	private JButton blackjackButton;
	private JButton horseButton;
	private JButton statisticsButton;
    private BufferedImage img1 = null;
    private BufferedImage img2 = null;
    private BufferedImage img3 = null;
    private BufferedImage img4 = null;
    
	public MainWindow(){
		
		
		rouletteButton = new JButton();
		LoadMenuImage();
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
		statisticsButton.setContentAreaFilled(false);
		statisticsButton.setBorderPainted(false);
		
		setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		background = new Tapet(width, height, "resources/fondoMain.jpg");
		background.setLayout(new GridLayout(2, 2));
		
		panel1.setLayout(new BorderLayout());
		panel1.setOpaque(false);
		panel1.add(rouletteButton, BorderLayout.CENTER);
		background.add(panel1);
		
		panel2.setLayout(new BorderLayout());
		panel2.setOpaque(false);
		panel2.add(horseButton, BorderLayout.CENTER);
		background.add(panel2);

		
		panel3.setLayout(new BorderLayout());
		panel3.setOpaque(false);
		panel3.add(blackjackButton, BorderLayout.CENTER);
		background.add(panel3);

		
		panel4.setLayout(new BorderLayout());
		panel4.setOpaque(false);
		panel4.add(statisticsButton, BorderLayout.CENTER);
		background.add(panel4);	
		
		add(background);
	}
	
	public void registerController(ButtonListener listener){
		rouletteButton.addActionListener(listener);
		blackjackButton.addActionListener(listener);
		horseButton.addActionListener(listener);
		statisticsButton.addActionListener(listener);
	}

	@Override
	public void setManager(Manager manager) {
		registerController(manager.getButtonLister());
	}
	public void LoadMenuImage(){
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
		
	}
}