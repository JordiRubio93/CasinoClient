package controlador;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import vista.Finestra;

public class Principal {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					inserirGif();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Creem la vista
				Finestra vista = new Finestra();
				// Creem el controlador i establim la relaci� CONTROLADOR->VISTA
				ListenerBotons controlador = new ListenerBotons(vista);
				// Establim la relacio VISTA->CONTROLADOR
				//vista.registreControladorBotons(controlador);
				// Fem visible la vista
				vista.setVisible(true);
			}
		});
	}
	
	public static void inserirGif() throws MalformedURLException{
		URL url = new URL("http://bsselektronika.hu/rajzok/rulett/rulett3.gif");
		
	    Icon icon = new ImageIcon(url);
	    JLabel label = new JLabel(icon);

	    JFrame f = new JFrame("Ruleta");
	   
	    f.getContentPane().add(label);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	}

	
}
