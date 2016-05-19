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
import controller.listeners.RouletteButtonsController;
import controller.roulette.AmericanRoulette;
import controller.roulette.RouletteManager;
import model.struct.roulette.Casella;
import view.Dialeg;
import view.GameView;

public class RouletteView extends GameView {
	private static final long serialVersionUID = 1L;
	
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
	private RouletteButtonsController listener;
	private RouletteManager rm;
	
	public RouletteView(){
		jpFinestra = new JPanel(new BorderLayout());
		super.createTime();
		jpFinestra.add(jpTemps, BorderLayout.NORTH);
		super.createList();
		jpFinestra.add(jpDades, BorderLayout.EAST);
		this.setLayout(new BorderLayout());
		this.add(jpFinestra, BorderLayout.CENTER);
		
		initElements();
	}
	
	private void initElements(){
		listener = new RouletteButtonsController(rm);
		
		americanRoulette = new AmericanRoulette();
		jpFinestra = new JPanel(new BorderLayout());
		jpEsquerra = new JPanel(new BorderLayout());
		jpCentral = new JPanel(new BorderLayout());
		jpInferior = new JPanel(new BorderLayout());
		jpNumeros = new JPanel(new GridLayout(3, 12));
		
		ArrayList<Casella> taula = americanRoulette.getCaselles();
		for(int i = 0; i < taula.size(); i++){
			Casella c = taula.get(i);
			
			MyButton button = new MyButton(String.valueOf(c.getNumero()), c.getColor());
			button.setFont(new Font("Cambria", Font.PLAIN, 30));
			button.setBackground(c.getColor());
			button.setForeground(Color.WHITE);
			
			button.addActionListener(listener);
			
			JPanel jpBoto = new JPanel(new BorderLayout());
			jpBoto.add(button, BorderLayout.CENTER);
			
			jpNumeros.add(jpBoto);
		}
		
		jpCentral.add(jpNumeros, BorderLayout.CENTER);

		jpZero = new JPanel(new BorderLayout());

		jbZero = new MyButton("0", new Color(76, 145, 65));
		jbZero.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbZero.setBackground(new Color(76, 145, 65));
		jbZero.setForeground(Color.WHITE);
		
		jbZero.addActionListener(listener);
		
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
		
		jbPrimera.addActionListener(listener);

		jbSegona = new MyButton("2ND 12", new Color(76, 145, 65));
		jbSegona.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbSegona.setBackground(new Color(76, 145, 65));
		jbSegona.setForeground(Color.WHITE);
		
		jbSegona.addActionListener(listener);

		jbTercera = new MyButton("3RD 12", new Color(76, 145, 65));
		jbTercera.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbTercera.setBackground(new Color(76, 145, 65));
		jbTercera.setForeground(Color.WHITE);
		
		jbTercera.addActionListener(listener);
		
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

		jbManca.addActionListener(listener);
		
		jbParell = new MyButton("EVEN", new Color(76, 145, 65));
		jbParell.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbParell.setBackground(new Color(76, 145, 65));
		jbParell.setForeground(Color.WHITE);

		jbParell.addActionListener(listener);

		jbVermell = new MyButton("RED", new Color(139, 0, 0));
		jbVermell.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbVermell.setBackground(new Color(139, 0, 0));
		jbVermell.setForeground(Color.WHITE);

		jbVermell.addActionListener(listener);

		jbNegre = new MyButton("BLACK", new Color(010, 010, 010));
		jbNegre.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbNegre.setBackground(new Color(010, 010, 010));
		jbNegre.setForeground(Color.WHITE);

		jbNegre.addActionListener(listener);

		jbSenar = new MyButton("ODD", new Color(76, 145, 65));
		jbSenar.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbSenar.setBackground(new Color(76, 145, 65));
		jbSenar.setForeground(Color.WHITE);

		jbSenar.addActionListener(listener);		

		jbPassa = new MyButton("19-36", new Color(76, 145, 65));
		jbPassa.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbPassa.setBackground(new Color(76, 145, 65));
		jbPassa.setForeground(Color.WHITE);

		jbPassa.addActionListener(listener);

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
	}
	
	public void insereixGif(){
		Icon icon = new ImageIcon(Constants.GIF);
		JLabel label = new JLabel(icon);
		label.setOpaque(true);
		
		jpCentral.removeAll();
		jpFinestra.add(label, BorderLayout.CENTER);
	}
	
	public void acabaPartida(int winner, boolean c){
		Dialeg dialeg = new Dialeg();
		dialeg.setWarningText("The winner number is... " + winner + " !");
	}
	
	
}
