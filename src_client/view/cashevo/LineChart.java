package view.cashevo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import view.Tapet;
import controller.Constants;

public class LineChart extends Tapet {	
	private static final long serialVersionUID = 1L;
	private LinkedList<Point> punts = new LinkedList<Point>();
	private LinkedList<Point> ruleta = new LinkedList<Point>();
	private LinkedList<Point> cavalls = new LinkedList<Point>();
	private LinkedList<Point> blackjack = new LinkedList<Point>();
	private LinkedList<String> sGlobal = new LinkedList<String>();
	private LinkedList<String> sRuleta = new LinkedList<String>();
	private LinkedList<String> sCavalls = new LinkedList<String>();
	private LinkedList<String> sBJ = new LinkedList<String>();
	private boolean[] lines;
	private String name;

	public LineChart(int width, int height, String path) {
		super(width, height, path);
		lines = new boolean[5];
		lines[4] = true;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
	
		g.drawLine(0,55,1920,55);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Cambria", Font.BOLD, 40));
		g.drawString(name, 10, 40);
		
		g.setFont(new Font("Calibri", Font.BOLD, 25));
		g.setColor(Constants.coolRed);
		g.drawString("Global", 10, 80);
		g.setColor(Constants.coolOrange);
		g.drawString("Roulette", 10, 105);
		g.setColor(Constants.coolDarkGreen);
		g.drawString("Horse race", 10, 130);
		g.setColor(Constants.coolIndigo);
		g.drawString("Blackjack", 10, 155);
		
		g2.setStroke(new BasicStroke(3));
		g.setFont(new Font("Calibri", Font.ITALIC, 16));
		if(lines[4] || lines[0]){
			g.setColor(Constants.coolRed);
			for(int i = 0; i < punts.size() - 1; i++){
				g2.drawLine((int) punts.get(i).getX()+5, (int) punts.get(i).getY()+5, (int) punts.get(i+1).getX()+5, (int) punts.get(i+1).getY()+5);
			}
			g.setColor(Constants.coolGray);
			for(int i = 0; i < punts.size(); i++){
				g.fillOval((int)punts.get(i).getX(), (int)punts.get(i).getY(), 10, 10);
			}
			
			if(!lines[4]){
				g.setColor(Color.WHITE);
				int x, y;
				for(int i = 0; i < punts.size(); i++){
					if(i == sGlobal.size()-1){
						x = (int)punts.get(i).getX() - sGlobal.get(i).length() * 8 - 3;
						y = (int)punts.get(i).getY() + 30;
					}
					else{
						x = (int)punts.get(i).getX() + 3;
						y = (int)punts.get(i).getY() - 7;
					}
					g.drawString(sGlobal.get(i), x, y);
				}
			}
		}
		if(lines[4] || lines[1]){
			g.setColor(Constants.coolOrange);
			for(int i = 0; i < ruleta.size() - 1; i++){
				g2.drawLine((int) ruleta.get(i).getX()+5, (int) ruleta.get(i).getY()+5, (int) ruleta.get(i+1).getX()+5, (int) ruleta.get(i+1).getY()+5);
			}
			g.setColor(Constants.coolGray);
			for(int i = 0; i < ruleta.size(); i++){
				g.fillOval((int)ruleta.get(i).getX(), (int)ruleta.get(i).getY(), 10, 10);
			}
			
			if(!lines[4]){
				g.setColor(Color.WHITE);
				int x, y;
				for(int i = 0; i < ruleta.size(); i++){
					if(i == sRuleta.size()-1){
						x = (int)ruleta.get(i).getX() - sRuleta.get(i).length() * 8 - 3;
						y = (int)ruleta.get(i).getY() + 30;
					}
					else{
						x = (int)ruleta.get(i).getX() + 3;
						y = (int)ruleta.get(i).getY() - 7;
					}
					g.drawString(sRuleta.get(i), x, y);
				}
			}
		}
		if(lines[4] || lines[2]){
			g.setColor(Constants.coolDarkGreen);
			for(int i = 0; i < cavalls.size() - 1; i++){
				g2.drawLine((int) cavalls.get(i).getX()+5, (int) cavalls.get(i).getY()+5, (int) cavalls.get(i+1).getX()+5, (int) cavalls.get(i+1).getY()+5);
			}
			g.setColor(Constants.coolGray);
			for(int i = 0; i < cavalls.size(); i++){
				g.fillOval((int)cavalls.get(i).getX(), (int)cavalls.get(i).getY(), 10, 10);
			}
			
			if(!lines[4]){
				g.setColor(Color.WHITE);
				int x, y;
				for(int i = 0; i < cavalls.size(); i++){
					if(i == sCavalls.size()-1){
						x = (int)cavalls.get(i).getX() - sCavalls.get(i).length() * 8 - 3;
						y = (int)cavalls.get(i).getY() + 30;
					}
					else{
						x = (int)cavalls.get(i).getX() + 3;
						y = (int)cavalls.get(i).getY() - 7;
					}
					g.drawString(sCavalls.get(i), x, y);
				}
			}
		}
		if(lines[4] || lines[3]){
			g.setColor(Constants.coolIndigo);
			for(int i = 0; i < blackjack.size() - 1; i++){
				g2.drawLine((int) blackjack.get(i).getX()+5, (int) blackjack.get(i).getY()+5, (int) blackjack.get(i+1).getX()+5, (int) blackjack.get(i+1).getY()+5);
			}
			g.setColor(Constants.coolGray);
			for(int i = 0; i < blackjack.size(); i++){
				g.fillOval((int)blackjack.get(i).getX(), (int)blackjack.get(i).getY(), 10, 10);
			}
			
			if(!lines[4]){
				g.setColor(Color.WHITE);
				int x, y;
				for(int i = 0; i < blackjack.size(); i++){
					if(i == sBJ.size()-1){
						x = (int)blackjack.get(i).getX() - sBJ.get(i).length() * 8 - 2;
						y = (int)blackjack.get(i).getY() + 30;
					}
					else{
						x = (int)blackjack.get(i).getX() + 3;
						y = (int)blackjack.get(i).getY() - 7;
					}
					g.drawString(sBJ.get(i), x, y);
				}
			}
		}
	}
	
	public void addPoint(float x, float y, boolean goFirst, int joc){
		Point punt = new Point();
		punt.setLocation(x, y);
		switch(joc){
		case 0:
			if(goFirst) punts.addFirst(punt);
			else punts.add(punt);
			break;
		case 1:
			if(goFirst) ruleta.addFirst(punt);
			else ruleta.add(punt);
			break;
		case 2:
			if(goFirst) cavalls.addFirst(punt);
			else cavalls.add(punt);
			break;
		case 3:
			if(goFirst) blackjack.addFirst(punt);
			else blackjack.add(punt);
			break;
		}
	}
	
	public void addString(String s, int joc){
		switch(joc){
		case 0: sGlobal.add(s); break;
		case 1: sRuleta.add(s); break;
		case 2: sCavalls.add(s); break;
		case 3: sBJ.add(s); break;
		}
	}
	
	public void addLines(boolean b, int joc){
		this.lines[joc] = b;
		repaint();
	}
	
	public void removeAllPoints(int joc){
		switch(joc){
		case 0: punts.clear(); break;
		case 1: ruleta.clear(); break;
		case 2: cavalls.clear(); break;
		case 3: blackjack.clear(); break;
		}
	}
	
	public void reset(){
		sGlobal.clear();
		sRuleta.clear();
		sCavalls.clear();
		sBJ.clear();
	}
	
	public void setUserName(String name){
		this.name = name;
	}
}
