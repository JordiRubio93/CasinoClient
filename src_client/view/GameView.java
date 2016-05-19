package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Constants;
import controller.TimerThread;
import controller.listeners.BetButtonController;
import controller.listeners.ExitButtonController;

public class GameView extends BaseJPanel {
	private static final long serialVersionUID = 1L;

	protected BaseJPanel panel;
	protected JPanel jpFinestra;
	protected JPanel jpTemps;
	protected JLabel jlTemps;
	protected GridLayout columnLayout;
	protected JScrollPane jspList;
	protected JPanel jpDades;
	protected JLabel jlAvatar;
	protected JPanel jpCell;
	protected JLabel jlUser;
	protected JPanel jpInfo;
	protected JLabel jlAposta;
	protected JPanel jpList;
	protected JPanel jpAux;
	protected TimerThread timer;
	protected boolean isRuleta;
	protected JButton jbBet;
	protected JButton jbExit;
	protected JPanel jpOptions;
	protected JLabel jlCount;
	
	public GameView(){
		initElements();
	}
	
	private void initElements(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		jlTemps = new JLabel(dateFormat.format(date));
		jlTemps.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlTemps.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		
		jpTemps = new JPanel();
		jpTemps.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jpTemps.add(jlTemps);
				
		columnLayout = new GridLayout();
		jpList = new JPanel(columnLayout);
		jpAux = new JPanel(new BorderLayout());
		jpAux.add(jpList, BorderLayout.NORTH);		
		jspList = new JScrollPane(jpAux);
		columnLayout.setColumns(1);
		
		jbExit = new JButton("Exit");
		jbExit.setBackground(new Color(255,255,255));
		jbExit.setForeground(Constants.coolBlue);
		
		jbBet = new JButton("Bet!");
		jbBet.setBackground(new Color(255,255,255));
		jbBet.setForeground(Constants.coolOrange);
		
		jpOptions = new JPanel(new GridLayout(1,2));
		jpOptions.add(jbExit);
		jpOptions.add(jbBet);
		
		jpDades = new JPanel(new BorderLayout());
		jpDades.add(jspList, BorderLayout.CENTER);
		jpDades.add(jpOptions, BorderLayout.SOUTH);
		jpDades.setBorder(BorderFactory.createTitledBorder("Players"));
	}
	
	public void registerController(){//BetButtonController bbc, ExitButtonController ebc){
		//jbBet.addActionListener(bcb);
		//jbExit.addActionListener(ebc);
	}
	
	public void actualitzaTemps(){
		timer = new TimerThread(jlTemps);
		new Thread(timer).start();
	}
	
	protected void createTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		jlTemps = new JLabel(dateFormat.format(date));
		jlTemps.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlTemps.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		
		jpTemps = new JPanel();
		jpTemps.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jpTemps.add(jlTemps);
	}
	
	protected void createList(){
		columnLayout = new GridLayout();
		jpList = new JPanel(columnLayout);
		jpAux = new JPanel(new BorderLayout());
		jpAux.add(jpList, BorderLayout.NORTH);		
		jspList = new JScrollPane(jpAux);
		columnLayout.setColumns(1);
		
		jbExit = new JButton("Exit");
		jbExit.setBackground(new Color(255,255,255));
		jbExit.setForeground(Constants.coolBlue);
		
		jbBet = new JButton("Bet!");
		jbBet.setBackground(new Color(255,255,255));
		jbBet.setForeground(Constants.coolOrange);
		
		jpOptions = new JPanel(new GridLayout(1,2));
		jpOptions.add(jbExit);
		jpOptions.add(jbBet);
		
		jpDades = new JPanel(new BorderLayout());
		jpDades.add(jspList, BorderLayout.CENTER);
		jpDades.add(jpOptions, BorderLayout.SOUTH);
		jpDades.setBorder(BorderFactory.createTitledBorder("Players"));
	}
}
