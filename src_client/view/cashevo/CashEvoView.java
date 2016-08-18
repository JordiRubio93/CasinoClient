package view.cashevo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.BaseJPanel;
import controller.Constants;

public class CashEvoView extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private JButton backButton = new JButton("Back");
	private JPanel panelTop = new JPanel();
	private JPanel panel = new JPanel(new BorderLayout());
	private LineChart chart;
	private JPanel panelBottom = new JPanel(new GridLayout());
	private JButton jbGlobal = new JButton("Global");
	private JButton jbRoulette = new JButton("Roulette");
	private JButton jbHorses = new JButton("Horse race");
	private JButton jbBJ = new JButton("Blackjack");

	public CashEvoView(){
		initElements();
	}
	
	@Override
	public void registerController() {
		backButton.addActionListener(getManager().getController());
		jbGlobal.addActionListener(getManager().getController());
		jbRoulette.addActionListener(getManager().getController());
		jbHorses.addActionListener(getManager().getController());
		jbBJ.addActionListener(getManager().getController());
	}

	@Override
	protected void initElements() {
		setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		backButton.setFont(Constants.boldFont);
		backButton.setBackground(Constants.coolGreen);
		backButton.setContentAreaFilled(true);
		backButton.setBorderPainted(false);
		backButton.putClientProperty("action", "Back To Cash Evo");
		backButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		
		panelTop.setBackground(Color.BLACK);
		panelTop.add(backButton, 0);

		jbGlobal.setBackground(Constants.coolRed);
		jbGlobal.setForeground(Color.WHITE);
		jbGlobal.putClientProperty("action", "GLOBAL BUTTON");
		jbRoulette.setBackground(Constants.coolOrange);
		jbRoulette.setForeground(Color.WHITE);
		jbRoulette.putClientProperty("action", "ROULETTE BUTTON");
		jbHorses.setBackground(Constants.coolDarkGreen);
		jbHorses.setForeground(Color.WHITE);
		jbHorses.putClientProperty("action", "HORSES BUTTON");
		jbBJ.setBackground(Constants.coolIndigo);
		jbBJ.setForeground(Color.WHITE);
		jbBJ.putClientProperty("action", "BJ BUTTON");
		
		panelBottom.add(jbGlobal);
		panelBottom.add(jbRoulette);
		panelBottom.add(jbHorses);
		panelBottom.add(jbBJ);
		
		chart = new LineChart(width, height, Constants.PATH_WALL);
		chart.setLayout(new BorderLayout());
		panel.add(chart, BorderLayout.CENTER);
		
		add(panelTop, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(panelBottom, BorderLayout.SOUTH);
	}
	
	public void addPoint(float x, float y, int joc){
		chart.addPoint(x,y,joc);
	}
	
	public void removeAllPoints(int joc){
		chart.removeAllPoints(joc);
	}
	
	public int getPanelHeight(){
		return panel.getHeight();
	}
	
	public void setLines(boolean show, int joc){
		chart.addLines(show, joc);
	}
	
	public void setUserName(String name){
		chart.setUserName(name);
	}
}
