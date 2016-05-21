package view.cavalls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Constants;
import controller.Manager;
import model.Calcul;
import model.struct.horses.HorseData;
import view.Dialeg;
import view.GameView;

public class HorsesView extends GameView {
	private static final long serialVersionUID = 1L;
	private Stadium jpStadium;
	private GridLayout gridLayout;
	private JPanel jpCarrils;
	private LinkedList<HorseAnimation> list;
	private Point[] coord;

	public HorsesView(){
		super();
		initElements();
	}
	
	private void initElements() {
		jpFinestra = new JPanel(new BorderLayout());
		
		jpFinestra.add(jpTemps, BorderLayout.NORTH);
		jpStadium = new Stadium(width, height, Constants.PATH_TAPET);
		jpStadium.setLayout(new BorderLayout());
		jpFinestra.add(jpStadium, BorderLayout.CENTER);
		super.createList();
		jpFinestra.add(jpDades, BorderLayout.EAST);
		this.setLayout(new BorderLayout());
		this.add(jpFinestra, BorderLayout.CENTER);
		setCounter();
	}
	
	public void setCursa() {
		jpStadium.setDimensions();
		jpStadium.setImatge(Constants.PATH_CARRILS);
		
		gridLayout = new GridLayout(Constants.nHorses, 1);
		gridLayout.setVgap(-80);
		jpCarrils = new JPanel(gridLayout);
		
		for(int i = 0; i < Constants.nHorses; i++){
			JLabel jlPos = new JLabel("   " + String.valueOf(12 - i) + "  ");
			jlPos.setHorizontalAlignment(JLabel.LEFT);
			jlPos.setFont(new Font("Serif", Font.BOLD, 34));
			jlPos.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
			jpCarrils.add(jlPos);
		}
		
		jpCarrils.setBackground(new Color(0,0,0,0));
		
		jpStadium.add(jpCarrils, BorderLayout.WEST);
		jpFinestra.add(jpStadium, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void showCursa(boolean show){
		jpCarrils.setVisible(show);
	}
	
	public void initHorses(LinkedList<HorseData> dades){
		list = new LinkedList<HorseAnimation>();
		coord = new Point[12];
		for(int i = 0; i < Constants.nHorses; i++){
			list.add(new HorseAnimation(dades.get(i).getColor(), i));
			coord[i] = new Point(Calcul.calculaX(dades.get(i).getSegons(), true), Calcul.calculaY(i));
		}
		jpStadium.setList(list);
		jpStadium.setCoordList(coord);
		jpStadium.setReady(true);
		revalidate();
		repaint();
	}
	
	public void runHorses(int i, int x, int y){
		jpStadium.getList().get(i).run();
		jpStadium.setCoord(x, y, i);
	}
	
	public void setCounter(){
		jlCount = new JLabel("BET!!!");
		jlCount.setHorizontalAlignment(JLabel.CENTER);
		jlCount.setVerticalAlignment(JLabel.CENTER);
		jlCount.setFont(new Font("Serif", Font.BOLD, 70));
		jlCount.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlCount.setBackground(Color.RED);
		
		jpStadium.add(jlCount, BorderLayout.CENTER);
	}
	
	public void showCounter(boolean show){
		jlCount.setVisible(show);
	}
	
	public JLabel getCounter(){
		return jlCount;
	}
	
	public void actualitzaCounter(int num){
		jlCount.setText(String.valueOf(num));
	}
	
	public void paintRed(boolean flag){
		jlCount.setOpaque(flag);
	}
	
	public void acabaPartida(String winner){
		Dialeg dialeg = new Dialeg();
		dialeg.setWarningText("The winner horse is... " + winner.toUpperCase() + " !");
	}

	@Override
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	@Override
	public Manager getManager() {
		return manager;
	}
	@Override
	public void registerController(){
		jbBet.putClientProperty("action", "BET_H");
		jbExit.putClientProperty("action", "EXIT_H");
		super.registerController();
	}

}
