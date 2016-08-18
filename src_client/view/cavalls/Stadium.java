package view.cavalls;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import view.Tapet;
import controller.Constants;

/**
 * Hereta de Tapet
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Val√©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubi√≥ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaci√≥ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */

public class Stadium extends Tapet {
	private static final long serialVersionUID = 1L;
	private LinkedList<HorseAnimation> list;
	private Point[] coord;
	private boolean ready;

	/**
	 * Constructor
	 * @param w: amplada
	 * @param h: alÁada
	 * @param ruta: adreÁa de la imatge de fons
	 */
	public Stadium(int w, int h, String ruta) {
		super(w, h, ruta);
	}

	public void setList(LinkedList<HorseAnimation> aniList) {
		this.list = aniList;
	}
	
	public LinkedList<HorseAnimation> getList() {
		return list;
	}

	/**
	 * Pinta els cavalls a lloc
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(ready){
			for(int i = 0; i < Constants.nHorses; i++){
				g.drawImage(list.get(i).getGif(), (int) coord[i].getX(), (int) coord[i].getY(), null);
				this.repaint();
			}
		}
	}
	
	/**
	 * Per indicar quan els cavalls es poden comenÁar a moure
	 * @param ready
	 */
	public void setReady(boolean ready){
		this.ready = ready;
	}

	/**
	 * 
	 * @param cList: la llista de coordenades completa
	 */
	public void setCoordList(Point[] cList){
		this.coord = cList;
	}
	
	/**
	 * 
	 * @param x: una coordenada x determinada
	 * @param y: una coordenada y determinada
	 * @param i: l'Ìndex del cavall
	 */
	public void setCoord(int x, int y, int i){
		coord[i].setLocation(coord[i].getX() + x, y);
	}
}
