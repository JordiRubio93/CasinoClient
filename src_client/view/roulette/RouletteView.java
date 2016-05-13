package view.roulette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.listeners.ListenerBotons;
import controller.roulette.AmericanRoulette;
import model.struct.roulette.Casella;
import view.GameView;

public class RouletteView extends GameView {
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JPanel jpNumeros;
	private JPanel jpZero;
	private JButton jbZero;

	private JPanel jpDotzenes;
	private JButton jbPrimera;
	private JButton jbSegona;
	private JButton jbTercera;

	private JPanel jpDobles;
	private JButton jbManca;
	private JButton jbParell;
	private JButton jbVermell;
	private JButton jbNegre;
	private JButton jbSenar;
	private JButton jbPassa;

	private JPanel jpFinestra;
	private JPanel jpEsquerra;
	private JPanel jpCentral;
	private JPanel jpInferior;
	
	private AmericanRoulette americanRoulette;
	
	public void creaRuleta(){
	
		americanRoulette = new AmericanRoulette();
		jpFinestra = new JPanel(new BorderLayout());
		jpEsquerra = new JPanel(new BorderLayout());
		jpCentral = new JPanel(new BorderLayout());
		jpInferior = new JPanel(new BorderLayout());
		
		jpNumeros = new JPanel(new GridLayout(3, 12));
		Border thickBorder = new LineBorder(Color.WHITE, 1);
		
		ArrayList<Casella> taula = americanRoulette.getCaselles();
		for(int i = 0; i < taula.size(); i++){
			Casella c = taula.get(i);
			
			JButton button = new JButton(String.valueOf(c.getNumero()));
			button.setBackground(c.getColor());
			button.setForeground(Color.WHITE);
			button.setFont(new Font("Cambria", Font.PLAIN, 30));
			button.setBorderPainted(true);
			button.setBorder(thickBorder);
			button.setOpaque(true);
			
			button.addActionListener(new ListenerBotons());
			
			jpNumeros.add(button);
			jpNumeros.setBackground(americanRoulette.getBackgroundColor());
		}
		
		jpCentral.add(jpNumeros, BorderLayout.CENTER);
		jpFinestra.add(jpCentral, BorderLayout.CENTER);

		jpZero = new JPanel(new BorderLayout());

		jbZero = new JButton("0");
		jbZero.setBackground(new Color(76, 145, 65));
		jbZero.setForeground(Color.WHITE);
		jbZero.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbZero.setBorderPainted(false);
		jbZero.setContentAreaFilled(false);
		jbZero.setOpaque(true);
		
		jbZero.addActionListener(new ListenerBotons());
		
		jpZero.add(jbZero, BorderLayout.CENTER);

		jpEsquerra.add(jpZero, BorderLayout.CENTER);
		jpFinestra.add(jpEsquerra, BorderLayout.WEST);

		jpDotzenes = new JPanel();
		jpDotzenes.setLayout(new GridLayout(1, 3));
		jpDotzenes.setBackground(new Color(76, 145, 65));
		
		jbPrimera = new JButton("1ST 12");
		jbPrimera.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbPrimera.setBackground(new Color(76, 145, 65));
		jbPrimera.setForeground(Color.WHITE);
		jbPrimera.setBorderPainted(true);
		jbPrimera.setBorder(thickBorder);
		jbPrimera.setContentAreaFilled(false);
		jbPrimera.setOpaque(true);
		
		jbPrimera.addActionListener(new ListenerBotons());

		jbSegona = new JButton("2ND 12");
		jbSegona.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbSegona.setBackground(new Color(76, 145, 65));
		jbSegona.setForeground(Color.WHITE);
		jbSegona.setBorderPainted(true);
		jbSegona.setBorder(thickBorder);
		jbSegona.setContentAreaFilled(false);
		jbSegona.setOpaque(true);
		
		jbSegona.addActionListener(new ListenerBotons());

		jbTercera = new JButton("3RD 12");
		jbTercera.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbTercera.setBackground(new Color(76, 145, 65));
		jbTercera.setForeground(Color.WHITE);
		jbTercera.setBorderPainted(true);
		jbTercera.setBorder(thickBorder);
		jbTercera.setContentAreaFilled(false);
		jbTercera.setOpaque(true);
		
		jbTercera.addActionListener(new ListenerBotons());
		
		jpDotzenes.add(jbPrimera);
		jpDotzenes.add(jbSegona);
		jpDotzenes.add(jbTercera);

		jpDobles = new JPanel();
		
		jpDobles.setLayout(new GridLayout(1, 6));
		jpDobles.setBackground(new Color(76, 145, 65));

		jbManca = new JButton("1-18");
		jbManca.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbManca.setBackground(new Color(76, 145, 65));
		jbManca.setForeground(Color.WHITE);
		jbManca.setBorderPainted(true);
		jbManca.setBorder(thickBorder);
		jbManca.setContentAreaFilled(false);
		jbManca.setOpaque(true);

		jbManca.addActionListener(new ListenerBotons());
		
		jbParell = new JButton("EVEN");
		jbParell.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbParell.setBackground(new Color(76, 145, 65));
		jbParell.setForeground(Color.WHITE);
		jbParell.setBorderPainted(true);
		jbParell.setBorder(thickBorder);
		jbParell.setContentAreaFilled(false);
		jbParell.setOpaque(true);

		jbParell.addActionListener(new ListenerBotons());

		jbVermell = new JButton("RED");
		jbVermell.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbVermell.setBackground(new Color(204, 006, 005));
		jbVermell.setForeground(Color.WHITE);
		jbVermell.setBorderPainted(true);
		jbVermell.setBorder(thickBorder);
		jbVermell.setContentAreaFilled(false);
		jbVermell.setOpaque(true);

		jbVermell.addActionListener(new ListenerBotons());

		jbNegre = new JButton("BLACK");
		jbNegre.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbNegre.setBackground(new Color(010, 010, 010));
		jbNegre.setForeground(Color.WHITE);
		jbNegre.setBorderPainted(true);
		jbNegre.setBorder(thickBorder);
		jbNegre.setContentAreaFilled(false);
		jbNegre.setOpaque(true);

		jbNegre.addActionListener(new ListenerBotons());

		jbSenar = new JButton("ODD");
		jbSenar.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbSenar.setBackground(new Color(76, 145, 65));
		jbSenar.setForeground(Color.WHITE);
		jbSenar.setBorderPainted(true);
		jbSenar.setBorder(thickBorder);
		jbSenar.setContentAreaFilled(false);
		jbSenar.setOpaque(true);

		jbSenar.addActionListener(new ListenerBotons());		

		jbPassa = new JButton("19-36");
		jbPassa.setFont(new Font("Cambria", Font.PLAIN, 30));
		jbPassa.setBackground(new Color(76, 145, 65));
		jbPassa.setForeground(Color.WHITE);
		jbPassa.setBorderPainted(true);
		jbPassa.setBorder(thickBorder);
		jbPassa.setContentAreaFilled(false);
		jbPassa.setOpaque(true);

		jbPassa.addActionListener(new ListenerBotons());

		jpDobles.add(jbManca);
		jpDobles.add(jbParell);
		jpDobles.add(jbVermell);
		jpDobles.add(jbNegre);
		jpDobles.add(jbSenar);
		jpDobles.add(jbPassa);
		
		jpInferior.add(jpDotzenes, BorderLayout.NORTH);
		jpInferior.add(jpDobles, BorderLayout.SOUTH);

		jpCentral.add(jpInferior, BorderLayout.SOUTH);
		
		//jpFinestra.add(jpTemps, BorderLayout.NORTH);
		jpFinestra.add(jpCentral, BorderLayout.CENTER);
		//jpFinestra.add(jpDades, BorderLayout.EAST);
		
		getPanel().add(jpFinestra);
		
		try {
			inserirGif();
		} catch (MalformedURLException e) {}
	}
	
	public static void inserirGif() throws MalformedURLException{
	    Icon icon = new ImageIcon("Resources/ruleta.gif");
	    JLabel label = new JLabel(icon);

	    f = new JFrame("RULETA");
	   
	    f.getContentPane().add(label);
	    f.pack();
	    f.setLocationRelativeTo(null);
	    f.setResizable(false);
	    f.setVisible(true);
	}
	
	public RouletteView(){
		/**super.creaTemps();
		jpFinestra.add(jpTemps, BorderLayout.NORTH);
		super.creaList();
		jpFinestra.add(jpDades, BorderLayout.EAST);
		getPanel().add(jpFinestra);*/
	}
	
	public void acabaPartida(){
		f.setVisible(false);
		super.setVisible(false);
	}
}
