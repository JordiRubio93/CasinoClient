package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tapet extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image imatge;
	private int w;
	private int h;
	
	public Tapet(LayoutManager layout){
		setLayout(layout);
	}
	
	public Tapet(int w, int h, String ruta){
		this.w = w;
		this.h = h;
		
		try{
			File fitxer = new File(ruta);
			imatge = ImageIO.read(fitxer);
		}catch(IOException e){}
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
		}catch (IOException e){}
	}
	
	public void setDimensions(){
		this.w = this.getWidth();
		this.h = this.getHeight();
	}
}
