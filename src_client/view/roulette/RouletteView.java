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
import controller.roulette.AmericanRoulette;
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

	private JPanel jpFinestra;
	private JPanel jpEsquerra;
	private JPanel jpCentral;
	private JPanel jpInferior;
	
	private AmericanRoulette americanRoulette;
	private ArrayList<Casella> taula;
	private ArrayList<MyButton> butons;
	
	/**
     * Constructor per la gràfica de la ruleta.
     */
	public RouletteView(){
		jpFinestra = new JPanel(new BorderLayout());
		super.createTime();
		jpFinestra.add(jpTemps, BorderLayout.NORTH);
		super.createList();
		jpFinestra.add(jpDades, BorderLayout.EAST);
		this.setLayout(new BorderLayout());
		this.add(jpFinestra, BorderLayout.CENTER);
		
		initElements();
		
		jbBet.putClientProperty("action", "BET_R");
	}//Tancament del constructor
	
	/**
     * Metode que no retorna res i que s'encarrega de inicialitzar els elements.
     */
	private void initElements(){
		americanRoulette = new AmericanRoulette();
		jpFinestra = new JPanel(new BorderLayout());
		jpEsquerra = new JPanel(new BorderLayout());
		jpCentral = new JPanel(new BorderLayout());
		jpInferior = new JPanel(new BorderLayout());
		jpNumeros = new JPanel(new GridLayout(3, 12));

		taula = americanRoulette.getCaselles();
		butons = new ArrayList<MyButton>();
		for(int i = 0; i < taula.size(); i++){
			Casella c = taula.get(i);
			MyButton button = new MyButton(String.valueOf(c.getNumero()), c.getColor());
			button.setFont(new Font("Cambria", Font.PLAIN, 30));
			button.setBackground(c.getColor());
			button.setForeground(Color.WHITE);
			JPanel jpBoto = new JPanel(new BorderLayout());
			butons.add(button);
			jpBoto.add(button, BorderLayout.CENTER);
			jpNumeros.add(jpBoto);
		}
		
		jpCentral.add(jpNumeros, BorderLayout.CENTER);

		jpZero = new JPanel(new BorderLayout());

		jbZero = new MyButton("0", new Color(76, 145, 65));
		jbZero.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbZero.setBackground(new Color(76, 145, 65));
		jbZero.setForeground(Color.WHITE);
		
		jpZero.add(jbZero, BorderLayout.CENTER);

		jpEsquerra.add(jpZero, BorderLayout.CENTER);
		jpCentral.add(jpEsquerra, BorderLayout.WEST);

		jpDotzenes = new JPanel();
		jpDotzenes.setLayout(new GridLayout(1, 3));
		jpDotzenes.setBackground(new Color(76, 145, 65));
		
		jbPrimera = new MyButton("1ST 12", new Color(76, 145, 65));
		jbPrimera.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbPrimera.setBackground(new Color(76, 145, 65));
		jbPrimera.setForeground(Color.WHITE);

		jbSegona = new MyButton("2ND 12", new Color(76, 145, 65));
		jbSegona.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbSegona.setBackground(new Color(76, 145, 65));
		jbSegona.setForeground(Color.WHITE);
		
		jbTercera = new MyButton("3RD 12", new Color(76, 145, 65));
		jbTercera.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbTercera.setBackground(new Color(76, 145, 65));
		jbTercera.setForeground(Color.WHITE);
		
		jpDotzenes.add(jbPrimera);
		jpDotzenes.add(jbSegona);
		jpDotzenes.add(jbTercera);

		jpDobles = new JPanel();
		
		jpDobles.setLayout(new GridLayout(1, 6));
		jpDobles.setBackground(new Color(76, 145, 65));

		jbManca = new MyButton("1-18", new Color(76, 145, 65));
		jbManca.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbManca.setBackground(new Color(76, 145, 65));
		jbManca.setForeground(Color.WHITE);

		jbParell = new MyButton("EVEN", new Color(76, 145, 65));
		jbParell.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbParell.setBackground(new Color(76, 145, 65));
		jbParell.setForeground(Color.WHITE);

		jbVermell = new MyButton("RED", new Color(139, 0, 0));
		jbVermell.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbVermell.setBackground(new Color(139, 0, 0));
		jbVermell.setForeground(Color.WHITE);

		jbNegre = new MyButton("BLACK", new Color(010, 010, 010));
		jbNegre.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbNegre.setBackground(new Color(010, 010, 010));
		jbNegre.setForeground(Color.WHITE);

		jbSenar = new MyButton("ODD", new Color(76, 145, 65));
		jbSenar.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbSenar.setBackground(new Color(76, 145, 65));
		jbSenar.setForeground(Color.WHITE);
		
		jbPassa = new MyButton("19-36", new Color(76, 145, 65));
		jbPassa.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbPassa.setBackground(new Color(76, 145, 65));
		jbPassa.setForeground(Color.WHITE);

		jpDobles.add(jbManca);
		jpDobles.add(jbParell);
		jpDobles.add(jbVermell);
		jpDobles.add(jbNegre);
		jpDobles.add(jbSenar);
		jpDobles.add(jbPassa);
		
		jpInferior.add(jpDotzenes, BorderLayout.NORTH);
		jpInferior.add(jpDobles, BorderLayout.SOUTH);

		jpCentral.add(jpInferior, BorderLayout.SOUTH);

		jpFinestra.add(jpTemps, BorderLayout.NORTH);
		jpFinestra.add(jpCentral, BorderLayout.CENTER);
		jpFinestra.add(jpDades, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
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
		jpFinestra.add(label, BorderLayout.CENTER);
	}//Tancament del metode
	
	
	/**
     * Metode que no retorna res i que s'encarrega d'acabar la partida de la ruleta.
     */
	public void acabaPartida(int winner, boolean c){
		Dialeg dialeg = new Dialeg();
		dialeg.setWarningText("The winner number is... " + winner + " !");
	}//Tancament del metode
	
	
	public void registerController(){
		for(MyButton c: butons){
			c.addActionListener(getManager().getController());
		}
		
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
	}
}
