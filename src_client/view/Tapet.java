package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * 
 * <p>
 * <b> Classe: Tapet </b> <br/>
 * </p>
 * 
 * Classe base que permet situar com a fons d'un panell normal una imatge donada
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
public class Tapet extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Image imatge;
	protected int w;
	protected int h;
	
	public Tapet(LayoutManager layout){
		setLayout(layout);
	}
	
	public Tapet(int w, int h, String ruta){
		this.w = w;
		this.h = h;
		
		try{
			File fitxer = new File(ruta);
			imatge = ImageIO.read(fitxer);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(imatge != null){
			g.drawImage(imatge, 0, 0, w, h, null);
		}
	}
	
	public void setImatge(String ruta){
		try {
			File fitxer = new File(ruta);
			imatge = ImageIO.read(fitxer);
			this.repaint();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void setW(int w) {
		this.w = w;
	}
	public void setH(int h) {
		this.h = h;
	}
}
