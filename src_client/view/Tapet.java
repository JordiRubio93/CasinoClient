package view;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ImgUtils;

public class Tapet extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image imatge;
	private int w;
	private int h;
	
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
			g.drawImage(imatge, 0, 0, w, h, this);
		}
	}
	
	public void setImatge(int w, int h, String ruta){
		JLabel jlAux = new JLabel();
		BufferedImage bi = ImgUtils.scaleImage(this.getWidth(), this.getHeight(), "Resources/carrils.png");
		ImageIcon ic = new ImageIcon((Image)bi);
		jlAux.setIcon(ic);
		this.setLayout(null);
		this.add(jlAux);
		jlAux.setBounds(0, 0, this.getWidth(), this.getHeight());
	}
}
