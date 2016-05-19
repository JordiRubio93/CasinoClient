package view.statistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Constants;
import model.struct.user.HistoricPartides;
import view.BaseJPanel;
import view.Dialeg;

public class Graphics extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private JButton backButton = new JButton("Back");
	private JPanel panelTop = new JPanel(new FlowLayout());
	private BarChart chart;
	
	public Graphics(){
		initElements();
	}
	
	private void initElements() {	
		setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		backButton.setFont(Constants.boldFont);
		backButton.setBackground(Constants.coolGreen);
		backButton.setContentAreaFilled(true);
		backButton.setBorderPainted(false);
		backButton.putClientProperty("action", "Back");
		backButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		
		panelTop.setBackground(Constants.semiOpaqueBlack);
		panelTop.add(backButton, 0);

		add(panelTop, BorderLayout.NORTH);
	}
	
	public boolean createChart(LinkedList<HistoricPartides> hist){
		ArrayList<Bar> bars = new ArrayList<Bar>();
		
		if(hist.isEmpty()){
			new Dialeg().setWarningText("We're sorry.\nThere's no available information about this game.");
			return false;
		}
		
		String cad = null;
		Color color = null;
		
		switch(hist.getFirst().getJoc()){
		case 1: color = Constants.coolOrange;
			cad = "ROULETTE";
			break;
		case 2: color = Constants.coolDarkGreen;
			cad = "HORSE RACE";
			break;
		case 3: color = Constants.coolIndigo;
			cad = "BLACKJACK";
			break;
		}
		
		for(int i = 0; i < hist.size() && i < 5; i++){
			Bar bar = new Bar((int) hist.get(i).getGuanys(), color, hist.get(i).getClient().getSurname());
			bars.add(bar);
		}
		
		int max = (int) hist.getFirst().getGuanys();
		int digits = String.valueOf(max).length();
		max = (int) (((String.valueOf(max).charAt(0) - '0') + 1) * Math.pow(10,(double)(digits-1)));
		
		Axis axis = new Axis(max, 0, 50, 10, 5, "Gains");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		chart = new BarChart(bars, axis, cad, width, height);
		add(chart, BorderLayout.CENTER);
		
		return true;
	}

	@Override
	public void registerController() {
		backButton.addActionListener(getManager().getController());
	}

	public BarChart getChart() {
		return chart;
	}

	public void setChart(BarChart chart) {
		this.chart = chart;
	}
}
