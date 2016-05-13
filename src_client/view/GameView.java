package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Manager;
import controller.listeners.BetButtonController;
import model.struct.user.User;

public class GameView extends BaseJPanel {
	
	
	private static final long serialVersionUID = 1L;

	private BaseJPanel panell;
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
	protected JPanel jpBet;
	private JLabel jlCount;
	
	public void ompleLlista(LinkedList<User> players){
	/**
		LinkedList<User> roulettePlayers = new LinkedList<User>();
		
		columnLayout.setRows(players.size());
		
		for(int i = 0; i < players.size(); i++){
			jpCell = new JPanel(new BorderLayout());
			jpInfo = new JPanel(new GridLayout(2,1));
			
			if(isRuleta){
				jlAvatar = new JLabel(new ImageIcon(roulettePlayers.get(i).getAvatar()));
				jlUser = new JLabel("   " + players.get(i).getEmail() + " - " + 25 + " €   ");
				jlAposta = new JLabel("   Aposta    ");
				//jlUser = new JLabel("   " + roulettePlayers.get(i).getName() + " - " + roulettePlayers.get(i).getBets().get(0).getAmount() + " €   ");
				//jlAposta = new JLabel("   " + ((RouletteBet) (roulettePlayers.get(i).getBets().get(0))).getAposta() + "   ");
			}else{
				jlAvatar = new JLabel(new ImageIcon(players.get(i).getAvatar()));
				//jlUser = new JLabel("   " + players.get(i).getName() + " - " + players.get(i).getBets().get(0).getAmount() + " €   ");
				jlUser = new JLabel("   " + players.get(i).getEmail() + " - " + 25 + " €   ");
				//jlAposta = new JLabel("   " + ((HorsesBet) (players.get(i).getBets().get(0))).getHorse() + "   ");
				jlAposta = new JLabel("   Aposta    ");
			}
			
			jpInfo.add(jlUser);
			jpInfo.add(jlAposta);

			jlUser.setFont(new Font("Serif", Font.BOLD, 20));
			jlAposta.setFont(new Font("Serif", Font.PLAIN, 18));
			
			jpCell.add(jlAvatar, BorderLayout.WEST);
			jpCell.add(jpInfo, BorderLayout.CENTER);
			
			jpList.add(jpCell, BorderLayout.CENTER);
			columnLayout.setVgap(10);
		}
	}*/}
	
	public void actualitzaTemps(){
		timer = new TimerThread(jlTemps);
		new Thread(timer).start();
	}
	
	
	@SuppressWarnings("unused")
	private LinkedList<User> creaLlista(LinkedList<User> players){
		LinkedList<User> list = new LinkedList<User>();
		
		for(int i = 0; i < players.size(); i++){
			User user = players.get(i);
			
			float amount = 0;
			String bet = "";
		/*
			for(int j = 0; j < players.get(i).getBets().size(); j++){
				amount = amount + players.get(i).getBets().get(j).getAmount();
				/*if(((RouletteBet) (players.get(i).getBets().get(j))).isColor()){
					bet = bet + ", " + ((RouletteBet) (players.get(i).getBets().get(j))).getColor();
				}else{
					bet = bet + ", " + String.valueOf(((RouletteBet) (players.get(i).getBets().get(j))).getNum());
				}
			}
			
			user.getBets().get(0).setAmount(amount);
			//((RouletteBet) user.getBets().get(0)).setAposta(bet);
			
			list.add(user);
		
			*/
		}
		
		return list;
	}
	
	protected void creaTemps() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		jlTemps = new JLabel(dateFormat.format(date));
		jlTemps.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlTemps.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		
		jpTemps = new JPanel();
		jpTemps.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jpTemps.add(jlTemps);
	}
	
	public BaseJPanel getPanel(){
		return panell;
	}
	protected void creaList(){
		columnLayout = new GridLayout();
		jpList = new JPanel(columnLayout);
		jpAux = new JPanel(new BorderLayout());
		jpAux.add(jpList, BorderLayout.NORTH);		
		jspList = new JScrollPane(jpAux);
		columnLayout.setColumns(1);
		
		jbBet = new JButton("Aposta!");
		jpBet = new JPanel(new BorderLayout());
		jpBet.add(jbBet, BorderLayout.EAST);
		jbBet.setBackground(new Color(255,255,255));
		jbBet.setForeground(new Color(255,128,0));
		
		jpDades = new JPanel(new BorderLayout());
		jpDades.add(jspList, BorderLayout.CENTER);
		jpDades.add(jpBet, BorderLayout.SOUTH);
		jpDades.setBorder(BorderFactory.createTitledBorder("Jugadors"));
	}
	
	public void registerController(BetButtonController bbc){
		jbBet.addActionListener(bbc);
	}
	
	public void acabaPartida(){
		super.setVisible(false);
	}
	
	public BaseJPanel getTablero(){
		return panell;
	}
	
	public JLabel setCounter(){
		jlCount = new JLabel("...");
		jlCount.setHorizontalAlignment(JLabel.CENTER);
		jlCount.setVerticalAlignment(JLabel.CENTER);
		jlCount.setFont(new Font("Serif", Font.BOLD, 70));
		jlCount.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		return jlCount;
	}
	
	public void showCounter(boolean show){
		jlCount.setVisible(show);
	}
	
	public void actualitzaCounter(int num){
		jlCount.setText(String.valueOf(num));
	}

	@Override
	public void setManager(Manager manager) {
		// TODO Auto-generated method stub
		
	}
}
