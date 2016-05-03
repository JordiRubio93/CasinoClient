package view.cavalls;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import model.Constants;
import view.Tapet;

public class Stadium extends Tapet {
	private static final long serialVersionUID = 1L;
	private LinkedList<HorseAnimation> list;
	private Point[] coord;
	private boolean ready;

	public Stadium(int w, int h, String ruta) {
		super(w, h, ruta);
	}

	public void setList(LinkedList<HorseAnimation> aniList) {
		this.list = aniList;
	}
	
	public LinkedList<HorseAnimation> getList() {
		return list;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(ready){
			for(int i = 0; i < Constants.nHorses; i++){
				g.drawImage(list.get(i).getGif(), (int) coord[i].getX(), (int) coord[i].getY(), null);
				this.repaint();
			}
		}
	}
	
	public void setReady(boolean ready){
		this.ready = ready;
	}
	
	public void setCoordList(Point[] cList){
		this.coord = cList;
	}
	
	public void setCoord(int x, int y, int i){
		coord[i].setLocation(coord[i].getX() + x, y);
	}
}
