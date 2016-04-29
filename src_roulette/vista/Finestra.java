package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controlador.ListenerBotons;
import model.AmericanRoulette;
import model.Casella;

public class Finestra extends JFrame {
	// Els components grafics de la vista definits com atributs de la classe.
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
	//private ArrayList<JButton> buttons;
	private AmericanRoulette americanRoulette;

	public Finestra() {

		americanRoulette = new AmericanRoulette();
		jpFinestra = new JPanel(new BorderLayout());
		jpEsquerra = new JPanel(new BorderLayout());
		jpCentral = new JPanel(new BorderLayout());
		jpInferior = new JPanel(new BorderLayout());
		//buttons = new ArrayList<JButton>();
		
		
		
		// Creem el panell que contidra els botons referents als numeros
		jpNumeros = new JPanel();
		// Indiquem un esquema de disseny en forma de graella
		// d'3 files per 12 columnes
		jpNumeros.setLayout(new GridLayout(3, 12));
		// Creem els 36 botons
		Border thickBorder = new LineBorder(Color.WHITE, 1);
		
		
		ArrayList<Casella> taula = americanRoulette.getCaselles();
		for(int i = 0; i<taula.size(); i++){
			Casella c = taula.get(i);
			
			JButton button = new JButton(String.valueOf(c.getNumero()));
			button.setBackground(c.getColor());
			button.setForeground(Color.WHITE);
			button.setBorderPainted(true);
			button.setBorder(thickBorder);
			button.setOpaque(true);
			//Afegim el ActionListener
			button.addActionListener(new ListenerBotons(null));
			
			jpNumeros.add(button);
			jpNumeros.setBackground(americanRoulette.getBackgroundColor());
		}

		jpCentral.add(jpNumeros, BorderLayout.CENTER);
		jpFinestra.add(jpCentral, BorderLayout.CENTER);

		// Creem el panell que contidra el boto referent al numero Zero
		jpZero = new JPanel(new BorderLayout());

		// Creem el boto
		jbZero = new JButton("0");
		jbZero.setBackground(new Color(76, 145, 65));
		jbZero.setForeground(Color.WHITE);
		jbZero.setBorderPainted(false);
		jbZero.setContentAreaFilled(false);
		jbZero.setOpaque(true);
		
		//Afegim el ActionListener
		jbZero.addActionListener(new ListenerBotons(null));
		
		// Afegim el boto al panell
		jpZero.add(jbZero, BorderLayout.CENTER);

		jpEsquerra.add(jpZero, BorderLayout.CENTER);
		jpFinestra.add(jpEsquerra, BorderLayout.WEST);

		// Creem el panell que contidra els botons referents a les dotzenes
		jpDotzenes = new JPanel();
		// Indiquem un esquema de disseny en forma de graella
		// d'1 fila per 3 columnes
		jpDotzenes.setLayout(new GridLayout(1, 3));
		jpDotzenes.setBackground(new Color(76, 145, 65));
		
		
		
		// Creem els botons

		jbPrimera = new JButton("1ST 12");
		jbPrimera.setFont(new Font("Arial", Font.PLAIN, 30));
		jbPrimera.setBackground(new Color(76, 145, 65));
		jbPrimera.setForeground(Color.WHITE);
		jbPrimera.setBorderPainted(true);
		jbPrimera.setBorder(thickBorder);
		jbPrimera.setContentAreaFilled(false);
		jbPrimera.setOpaque(true);
		//Afegim el ActionListener
		jbPrimera.addActionListener(new ListenerBotons(null));
		

		jbSegona = new JButton("2ND 12");
		jbSegona.setFont(new Font("Arial", Font.PLAIN, 30));
		jbSegona.setBackground(new Color(76, 145, 65));
		jbSegona.setForeground(Color.WHITE);
		jbSegona.setBorderPainted(true);
		jbSegona.setBorder(thickBorder);
		jbSegona.setContentAreaFilled(false);
		jbSegona.setOpaque(true);
		//Afegim el ActionListener
		jbSegona.addActionListener(new ListenerBotons(null));

		jbTercera = new JButton("3RD 12");
		jbTercera.setFont(new Font("Arial", Font.PLAIN, 30));
		jbTercera.setBackground(new Color(76, 145, 65));
		jbTercera.setForeground(Color.WHITE);
		jbTercera.setBorderPainted(true);
		jbTercera.setBorder(thickBorder);
		jbTercera.setContentAreaFilled(false);
		jbTercera.setOpaque(true);
		//Afegim el ActionListener
		jbTercera.addActionListener(new ListenerBotons(null));
		
		
		// Afegim els botons al panell
		jpDotzenes.add(jbPrimera);
		jpDotzenes.add(jbSegona);
		jpDotzenes.add(jbTercera);

		// Creem el panell que contidra els botons referents als "dobles"
		jpDobles = new JPanel();
		// Indiquem un esquema de disseny en forma de graella
		// d'1 fila per 6 columnes
		jpDobles.setLayout(new GridLayout(1, 6));
		jpDobles.setBackground(new Color(76, 145, 65));

		// Creem els botons
		jbManca = new JButton("1-18");
		jbManca.setFont(new Font("Arial", Font.PLAIN, 30));
		jbManca.setBackground(new Color(76, 145, 65));
		jbManca.setForeground(Color.WHITE);
		jbManca.setBorderPainted(true);
		jbManca.setBorder(thickBorder);
		jbManca.setContentAreaFilled(false);
		jbManca.setOpaque(true);
		//Afegim el ActionListener
		jbManca.addActionListener(new ListenerBotons(null));
		
		jbParell = new JButton("EVEN");
		jbParell.setFont(new Font("Arial", Font.PLAIN, 30));
		jbParell.setBackground(new Color(76, 145, 65));
		jbParell.setForeground(Color.WHITE);
		jbParell.setBorderPainted(true);
		jbParell.setBorder(thickBorder);
		jbParell.setContentAreaFilled(false);
		jbParell.setOpaque(true);
		//Afegim el ActionListener
		jbParell.addActionListener(new ListenerBotons(null));

		jbVermell = new JButton("RED");
		jbVermell.setFont(new Font("Arial", Font.PLAIN, 30));
		jbVermell.setBackground(new Color(204, 006, 005));
		jbVermell.setForeground(Color.WHITE);
		jbVermell.setBorderPainted(true);
		jbVermell.setBorder(thickBorder);
		jbVermell.setContentAreaFilled(false);
		jbVermell.setOpaque(true);
		//Afegim el ActionListener
		jbVermell.addActionListener(new ListenerBotons(null));

		jbNegre = new JButton("BLACK");
		jbNegre.setFont(new Font("Arial", Font.PLAIN, 30));
		jbNegre.setBackground(new Color(010, 010, 010));
		jbNegre.setForeground(Color.WHITE);
		jbNegre.setBorderPainted(true);
		jbNegre.setBorder(thickBorder);
		jbNegre.setContentAreaFilled(false);
		jbNegre.setOpaque(true);
		//Afegim el ActionListener
		jbNegre.addActionListener(new ListenerBotons(null));

		jbSenar = new JButton("ODD");
		jbSenar.setFont(new Font("Arial", Font.PLAIN, 30));
		jbSenar.setBackground(new Color(76, 145, 65));
		jbSenar.setForeground(Color.WHITE);
		jbSenar.setBorderPainted(true);
		jbSenar.setBorder(thickBorder);
		jbSenar.setContentAreaFilled(false);
		jbSenar.setOpaque(true);
		//Afegim el ActionListener
		jbSenar.addActionListener(new ListenerBotons(null));
		

		jbPassa = new JButton("19-36");
		jbPassa.setFont(new Font("Arial", Font.PLAIN, 30));
		jbPassa.setBackground(new Color(76, 145, 65));
		jbPassa.setForeground(Color.WHITE);
		jbPassa.setBorderPainted(true);
		jbPassa.setBorder(thickBorder);
		jbPassa.setContentAreaFilled(false);
		jbPassa.setOpaque(true);
		//Afegim el ActionListener
		jbPassa.addActionListener(new ListenerBotons(null));

		// Afegim els botons al panell
		jpDobles.add(jbManca);
		jpDobles.add(jbParell);
		jpDobles.add(jbVermell);
		jpDobles.add(jbNegre);
		jpDobles.add(jbSenar);
		jpDobles.add(jbPassa);

		jpInferior.add(jpDotzenes, BorderLayout.NORTH);
		jpInferior.add(jpDobles, BorderLayout.SOUTH);

		jpCentral.add(jpInferior, BorderLayout.SOUTH);

		this.getContentPane().add(jpFinestra);

		// Donem una mida, un t�tol i centrem la finestra a la pantalla
		setSize(900, 500);
		setTitle("GUIRuleta");
		setLocationRelativeTo(null);
		// Indiquem que quan es faci clic a la "X" de la finestra
		// el programa finalitzi
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
