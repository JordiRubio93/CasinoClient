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
	private boolean lines;
	private String name;

	public LineChart(int width, int height, String path) {
		super(width, height, path);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
	
		g.drawLine(0,55,1920,55);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Serif", Font.BOLD, 40));
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
		
		if(lines){
			g2.setStroke(new BasicStroke(3));
			
			g.setColor(Constants.coolOrange);
			for(int i = 0; i < ruleta.size() - 1; i++){
				g2.drawLine((int) ruleta.get(i).getX()+5, (int) ruleta.get(i).getY()+5, (int) ruleta.get(i+1).getX()+5, (int) ruleta.get(i+1).getY()+5);
			}
			
			g.setColor(Constants.coolDarkGreen);
			for(int i = 0; i < cavalls.size() - 1; i++){
				g2.drawLine((int) cavalls.get(i).getX()+5, (int) cavalls.get(i).getY()+5, (int) cavalls.get(i+1).getX()+5, (int) cavalls.get(i+1).getY()+5);
			}
			
			g.setColor(Constants.coolIndigo);
			for(int i = 0; i < blackjack.size() - 1; i++){
				g2.drawLine((int) blackjack.get(i).getX()+5, (int) blackjack.get(i).getY()+5, (int) blackjack.get(i+1).getX()+5, (int) blackjack.get(i+1).getY()+5);
			}
			
			g.setColor(Constants.coolRed);
			for(int i = 0; i < punts.size() - 1; i++){
				g2.drawLine((int) punts.get(i).getX()+5, (int) punts.get(i).getY()+5, (int) punts.get(i+1).getX()+5, (int) punts.get(i+1).getY()+5);
			}
		}
		
		g.setColor(Constants.coolGray);
		for(int i = 0; i < punts.size(); i++){
			g.fillOval((int)punts.get(i).getX(), (int)punts.get(i).getY(), 10, 10);
		}
		for(int i = 0; i < ruleta.size(); i++){
			g.fillOval((int)ruleta.get(i).getX(), (int)ruleta.get(i).getY(), 10, 10);
		}
		for(int i = 0; i < cavalls.size(); i++){
			g.fillOval((int)cavalls.get(i).getX(), (int)cavalls.get(i).getY(), 10, 10);
		}
		for(int i = 0; i < blackjack.size(); i++){
			g.fillOval((int)blackjack.get(i).getX(), (int)blackjack.get(i).getY(), 10, 10);
		}
	}
	
	public void addPoint(float x, float y, int joc){
		Point punt = new Point();
		punt.setLocation(x, y);
		switch(joc){
		case 0: punts.add(punt); break;
		case 1: ruleta.add(punt); break;
		case 2: cavalls.add(punt); break;
		case 3: blackjack.add(punt); break;
		}
	}
	
	public void addLines(boolean b){
		this.lines = b;
	}
	
	public void removeAllPoints(int joc){
		switch(joc){
		case 0: punts.clear(); break;
		case 1: ruleta.clear(); break;
		case 2: cavalls.clear(); break;
		case 3: blackjack.clear(); break;
		}
	}
	
	public void setUserName(String name){
		this.name = name;
	}
}
