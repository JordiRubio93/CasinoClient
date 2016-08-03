package view.roulette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Constants;
import model.AmericanRoulette;
import model.struct.roulette.Casella;
import view.Dialeg;
import view.GameView;

/**
 * 
 * <p>
 * <b> Classe: RouletteView </b> <br/>
 * Implementa la gràfica de la ruleta.
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */

public class RouletteView extends GameView {
	private static final long serialVersionUID = 1L;
	private final Color PUSHED = new Color(255,215,0);
	
	//Atributs de la classe
	private JPanel jpNumeros;
	private JPanel jpZero;
	private MyButton jbZero;

	private JPanel jpDotzenes;
	private MyButton jbPrimera;
	private MyButton jbSegona;
	private MyButton jbTercera;

	private JPanel jpDobles;
	private MyButton jbManca;
	private MyButton jbParell;
	private MyButton jbVermell;
	private MyButton jbNegre;
	private MyButton jbSenar;
	private MyButton jbPassa;

	//private JPanel jpFinestra;
	private JPanel jpEsquerra;
	private JPanel jpCentral;
	private JPanel jpInferior;
	
	private AmericanRoulette americanRoulette;
	private ArrayList<Casella> taula;
	private ArrayList<MyButton> butons;
	
	/**
     * Constructor per la grafica de la ruleta.
     */
	
	public RouletteView(){
		super.initElements();
		initElements();
		createDaemonTime();	
	}
	
	/**
     * Metode que no retorna res i que s'encarrega de inicialitzar els elements.
     */
	protected void initElements(){
		americanRoulette = new AmericanRoulette();
		jpFinestra = new JPanel(new BorderLayout());
		jpEsquerra = new JPanel(new BorderLayout());
		jpCentral = new JPanel(new BorderLayout());
		jpInferior = new JPanel(new BorderLayout());
		jpNumeros = new JPanel(new GridLayout(3, 12));
		
		//numeros
		taula = americanRoulette.getCaselles();
		butons = new ArrayList<MyButton>();
		for(int i = 0; i < taula.size(); i++){
			Casella c = taula.get(i);
			MyButton button = new MyButton(String.valueOf(c.getNumero()), c.getColor());
			button.setFont(new Font("Serif", Font.PLAIN, 30));
			button.setBackground(c.getColor());
			button.setForeground(Color.WHITE);
			JPanel jpBoto = new JPanel(new BorderLayout());
			butons.add(button);
			jpBoto.add(button, BorderLayout.CENTER);
			jpBoto.putClientProperty("action", "");
			jpNumeros.add(jpBoto);
		}
		jpCentral.add(jpNumeros, BorderLayout.CENTER);
		
		jpZero = new JPanel(new BorderLayout());
		jbZero = new MyButton("0", new Color(76, 145, 65));
		jbZero.setFont(new Font("Serif", Font.PLAIN, 30));
		jbZero.setBackground(new Color(76, 145, 65));
		jbZero.setForeground(Color.WHITE);
		jbZero.putClientProperty("action", "roulette");
		
		jpZero.add(jbZero, BorderLayout.CENTER);

		jpEsquerra.add(jpZero, BorderLayout.CENTER);
		jpCentral.add(jpEsquerra, BorderLayout.WEST);

		jpDotzenes = new JPanel();
		jpDotzenes.setLayout(new GridLayout(1, 3));
		jpDotzenes.setBackground(new Color(76, 145, 65));
		jpDotzenes.putClientProperty("action", "roulette");
		
		jbPrimera = new MyButton("1ST 12", new Color(76, 145, 65));
		jbPrimera.setFont(new Font("Serif", Font.PLAIN, 30));
		jbPrimera.setBackground(new Color(76, 145, 65));
		jbPrimera.setForeground(Color.WHITE);
		jbPrimera.putClientProperty("action", "roulette");

		jbSegona = new MyButton("2ND 12", new Color(76, 145, 65));
		jbSegona.setFont(new Font("Serif", Font.PLAIN, 30));
		jbSegona.setBackground(new Color(76, 145, 65));
		jbSegona.setForeground(Color.WHITE);
		jbSegona.putClientProperty("action", "roulette");
		
		jbTercera = new MyButton("3RD 12", new Color(76, 145, 65));
		jbTercera.setFont(new Font("Serif", Font.PLAIN, 30));
		jbTercera.setBackground(new Color(76, 145, 65));
		jbTercera.setForeground(Color.WHITE);
		jbTercera.putClientProperty("action", "roulette");
		
		
		jpDotzenes.add(jbPrimera);
		jpDotzenes.add(jbSegona);
		jpDotzenes.add(jbTercera);

		jpDobles = new JPanel();
		
		jpDobles.setLayout(new GridLayout(1, 6));
		jpDobles.setBackground(new Color(76, 145, 65));
		jpDobles.putClientProperty("action", "roulette");
		jbManca = new MyButton("1-18", new Color(76, 145, 65));
		jbManca.setFont(new Font("Serif", Font.PLAIN, 30));
		jbManca.setBackground(new Color(76, 145, 65));
		jbManca.setForeground(Color.WHITE);
		jbManca.putClientProperty("action", "roulette");
		jbParell = new MyButton("EVEN", new Color(76, 145, 65));
		jbParell.setFont(new Font("Serif", Font.PLAIN, 30));
		jbParell.setBackground(new Color(76, 145, 65));
		jbParell.setForeground(Color.WHITE);
		jbParell.putClientProperty("action", "roulette");
		
		jbVermell = new MyButton("RED", new Color(139, 0, 0));
		jbVermell.setFont(new Font("Serif", Font.PLAIN, 30));
		jbVermell.setBackground(new Color(139, 0, 0));
		jbVermell.setForeground(Color.WHITE);
		jbVermell.putClientProperty("action", "roulette");
		

		jbNegre = new MyButton("BLACK", new Color(010, 010, 010));
		jbNegre.setFont(new Font("Serif", Font.PLAIN, 30));
		jbNegre.setBackground(new Color(010, 010, 010));
		jbNegre.setForeground(Color.WHITE);
		jbNegre.putClientProperty("action", "roulette");
		
		jbSenar = new MyButton("ODD", new Color(76, 145, 65));
		jbSenar.setFont(new Font("Serif", Font.PLAIN, 30));
		jbSenar.setBackground(new Color(76, 145, 65));
		jbSenar.setForeground(Color.WHITE);
		jbSenar.putClientProperty("action", "roulette");
		
		jbPassa = new MyButton("19-36", new Color(76, 145, 65));
		jbPassa.setFont(new Font("Serif", Font.PLAIN, 30));
		jbPassa.setBackground(new Color(76, 145, 65));
		jbPassa.setForeground(Color.WHITE);
		jbPassa.putClientProperty("action", "roulette");
		
		jpDobles.add(jbManca);
		jpDobles.add(jbParell);
		jpDobles.add(jbVermell);
		jpDobles.add(jbNegre);
 		jpDobles.add(jbSenar);
		jpDobles.add(jbPassa);
		
		jpInferior.add(jpDotzenes, BorderLayout.NORTH);
		jpInferior.add(jpDobles, BorderLayout.SOUTH);
		jpCentral.add(jpInferior, BorderLayout.SOUTH);
		jpFinestra.add(jpCentral, BorderLayout.CENTER);

		this.add(jpFinestra, BorderLayout.CENTER);
		
	}//Tancament del metode
	
	/**
     * Metode que no retorna res i que s'encarrega d'inserir el Gif de la ruleta.
     */
	public void insereixGif(){
		Icon icon = new ImageIcon(Constants.GIF);
		JLabel label = new JLabel(icon);
		label.setOpaque(true);
		
		jpCentral.removeAll();
		jpFinestra.add(label);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
	    
	}//Tancament del metode
	
	/**
     * Metode que no retorna res i que s'encarrega d'acabar la partida de la ruleta.
     */
	public void acabaPartida(int winner, boolean c){
		Dialeg dialeg = new Dialeg();
		dialeg.setWarningText("The winner number is... " + winner + " !");
	}//Tancament del metode

	/**
     * Metode que no retorna res i que tracta el boto de "enable" de la ruleta.
     */
	public void enableBet(){
		jbBet.setEnabled(true);
	}//Tancament del metode
	
	public void disableBet() {
		jbBet.setEnabled(false);
	}//Tancament del metode
	
	/**
     * Metode que no retorna res, rep un MyButton i s'encarrega de pintar un botó.
     * @param jBoto (Boto que es vol pintar)
     * 
     */
	public void pintaBoto(MyButton jBoto) {
		pintaBoto(jBoto);
		jBoto.canviaEstat();
		jBoto.setBackground(PUSHED);
	}//Tancament del metode
	
	/**
     * Metode que no retorna res i que s'encarrega de tractar els registres dels controladors.
     */
	public void registerController(){
		super.registerController();
		for(MyButton c: butons){
			c.putClientProperty("action", "roulette");
			c.addActionListener(getManager().getController());	
		}
		jbBet.putClientProperty("action", "BET_R");
		jbExit.putClientProperty("action", "EXIT_R");
		jbManca.addActionListener(getManager().getController());
		jbPassa.addActionListener(getManager().getController());
		jbSenar.addActionListener(getManager().getController());
		jbSegona.addActionListener(getManager().getController());
		jbNegre.addActionListener(getManager().getController());
		jbVermell.addActionListener(getManager().getController());
		jbTercera.addActionListener(getManager().getController());
		jbZero.addActionListener(getManager().getController());
		jbPrimera.addActionListener(getManager().getController());
		jbParell.addActionListener(getManager().getController());
	}//Tancament del metode
}//Tancament de la classe
