package view.statistics;

/**
 * Panell per escollir el TOP5 a mostrar
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */

import java.awt.BorderLayout;
import java.awt.Color;
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

import controller.Constants;
import view.BaseJPanel;
import view.Tapet;

public class StatisticsWindow extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private final String home = "Home";
	
	private Tapet background;
	private JPanel panelTop = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JButton top5RouletteButton;
	private JButton top5BlackjackButton;
	private JButton top5hhorseButton;
	private JButton cashEvoButton;
	private JButton homeButton = new JButton(home);
	
	public StatisticsWindow(){
		initElements();
	}
	
	protected void initElements(){
		setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		BufferedImage img3 = null;
		BufferedImage img4 = null;
		
		//Carrega les imatges
		
		try {
			img1 = ImageIO.read(new File("Resources/top5Roulette.png"));
			img2 = ImageIO.read(new File("Resources/top5Blackjack.png"));
			img3 = ImageIO.read(new File("Resources/top5HorseRace.png"));
			img4 = ImageIO.read(new File("Resources/cashRanking.png"));
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
		
		top5RouletteButton = new JButton();
		top5RouletteButton.setIcon(new ImageIcon (img1));
		top5RouletteButton.putClientProperty("action", "Top 5 Roulette");
		top5RouletteButton.setContentAreaFilled(false);
		top5RouletteButton.setBorderPainted(false);
		
		top5BlackjackButton = new JButton();
		top5BlackjackButton.setIcon(new ImageIcon (img2));
		top5BlackjackButton.putClientProperty("action", "Top 5 Blackjack");
		top5BlackjackButton.setContentAreaFilled(false);
		top5BlackjackButton.setBorderPainted(false);
		
		top5hhorseButton = new JButton();
		top5hhorseButton.setIcon(new ImageIcon (img3));
		top5hhorseButton.putClientProperty("action", "Top 5 Horses");
		top5hhorseButton.setContentAreaFilled(false);
		top5hhorseButton.setBorderPainted(false);
		
		cashEvoButton = new JButton();
		cashEvoButton.setIcon(new ImageIcon (img4));
		cashEvoButton.putClientProperty("action", "Cash Evo");
		cashEvoButton.setContentAreaFilled(false);
		cashEvoButton.setBorderPainted(false);
		
		panelCenter.setLayout(new GridLayout(1, 4));
		panelCenter.setOpaque(false);
		
		panelCenter.add(top5RouletteButton);
		panelCenter.add(top5BlackjackButton);
		panelCenter.add(top5hhorseButton);
		panelCenter.add(cashEvoButton);
		
		homeButton.setFont(Constants.boldFont);
		homeButton.setBackground(Constants.coolGreen);
		homeButton.setContentAreaFilled(true);
		homeButton.setBorderPainted(false);
		homeButton.putClientProperty("action", "Home");
		homeButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		
		panelTop.setLayout(new FlowLayout());
		panelTop.setBackground(Color.BLACK);
		panelTop.add(homeButton, 0);
		
		background = new Tapet(width, height, Constants.BG);
		background.setLayout(new BorderLayout());
		background.add(panelTop, BorderLayout.NORTH);
		background.add(panelCenter, BorderLayout.CENTER);
		add(background, BorderLayout.CENTER);
	}

	/**
	 * Controlador pels botons
	 */
	public void registerController(){
		top5RouletteButton.addActionListener(getManager().getController());
		top5BlackjackButton.addActionListener(getManager().getController());
		top5hhorseButton.addActionListener(getManager().getController());
		cashEvoButton.addActionListener(getManager().getController());
		homeButton.addActionListener(getManager().getController());
	}
}
