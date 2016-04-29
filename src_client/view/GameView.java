package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ComparadorABC;
import model.Constants;
import model.struct.bet.HorsesBet;
import model.struct.bet.RouletteBet;
import model.struct.users.PublicUser;

public class GameView extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = Constants.HEIGHT;
	private static final int WIDTH = Constants.WIDTH;
	private JPanel jpFinestra;
	private JPanel jpTemps;
	protected Tapet jpTapet;
	private JLabel jlTemps;
	private GridLayout ColumnLayout;
	private JScrollPane jspList;
	private JPanel jpDades;
	private JLabel jlAvatar;
	private JPanel jpCell;
	private JLabel jlUser;
	private JPanel jpInfo;
	private JLabel jlAposta;
	private JPanel jpList;
	private JPanel jpAux;
	private TimerThread timer;
	private boolean isRuleta;
	
	public GameView(){
		jpFinestra = new JPanel(new BorderLayout());
		
		creaTemps();
		jpFinestra.add(jpTemps, BorderLayout.NORTH);
		
		jpTapet = new Tapet(WIDTH, HEIGHT, "Resources/tapet.jpg");
		jpTapet.setLayout(new BorderLayout());
		jpFinestra.add(jpTapet, BorderLayout.CENTER);
		
		creaList();
		jpFinestra.add(jpDades, BorderLayout.EAST);

		this.getContentPane().add(jpFinestra);
		propietats();
		
		isRuleta = false;
	}
	
	public void ompleLlista(LinkedList<PublicUser> players){
		LinkedList<PublicUser> roulettePlayers = new LinkedList<PublicUser>();
		
		if(players.get(0).getBets().get(0) instanceof RouletteBet){
			Collections.sort(players, new ComparadorABC());
			roulettePlayers = creaLlista(players);
			ColumnLayout.setRows(roulettePlayers.size());
			isRuleta = true;
		}else{
			ColumnLayout.setRows(players.size());
		}
		
		for(int i = 0; i < players.size(); i++){
			jpCell = new JPanel(new BorderLayout());
			jpInfo = new JPanel(new GridLayout(2,1));
			
			if(isRuleta){
				jlAvatar = new JLabel(new ImageIcon(roulettePlayers.get(i).getAvatar()));
				jlUser = new JLabel("   " + roulettePlayers.get(i).getName() + " - " + roulettePlayers.get(i).getBets().get(0).getAmount() + " €   ");
				jlAposta = new JLabel("   " + ((RouletteBet) (roulettePlayers.get(i).getBets().get(0))).getAposta() + "   ");
			}else{
				jlAvatar = new JLabel(new ImageIcon(players.get(i).getAvatar()));
				jlUser = new JLabel("   " + players.get(i).getName() + " - " + players.get(i).getBets().get(0).getAmount() + " €   ");
				jlAposta = new JLabel("   " + ((HorsesBet) (players.get(i).getBets().get(0))).getHorse() + "   ");
			}
			
			jpInfo.add(jlUser);
			jpInfo.add(jlAposta);

			jlUser.setFont(new Font("Serif", Font.BOLD, 20));
			jlAposta.setFont(new Font("Serif", Font.PLAIN, 18));
			
			jpCell.add(jlAvatar, BorderLayout.WEST);
			jpCell.add(jpInfo, BorderLayout.CENTER);
			
			jpList.add(jpCell, BorderLayout.CENTER);
			ColumnLayout.setVgap(10);
		}
	}
	
	public void actualitzaTemps(){
		timer = new TimerThread(jlTemps);
		new Thread(timer).start();
	}
	
	private LinkedList<PublicUser> creaLlista(LinkedList<PublicUser> players){
		LinkedList<PublicUser> list = new LinkedList<PublicUser>();
		
		for(int i = 0; i < players.size(); i++){
			PublicUser user = players.get(i);
			
			float amount = 0;
			String bet = "";
			for(int j = 0; j < players.get(i).getBets().size(); j++){
				amount = amount + players.get(i).getBets().get(j).getAmount();
				if(((RouletteBet) (players.get(i).getBets().get(j))).isColor()){
					bet = bet + ", " + ((RouletteBet) (players.get(i).getBets().get(j))).getColor();
				}else{
					bet = bet + ", " + String.valueOf(((RouletteBet) (players.get(i).getBets().get(j))).getNum());
				}
			}
			
			user.getBets().get(0).setAmount(amount);
			((RouletteBet) user.getBets().get(0)).setAposta(bet);
			
			list.add(user);
		}
		
		return list;
	}
	
	private void creaTemps() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		jlTemps = new JLabel(dateFormat.format(date));
		jlTemps.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		
		jpTemps = new JPanel();
		jpTemps.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jpTemps.add(jlTemps);
	}
	
	private void creaList(){
		ColumnLayout = new GridLayout();
		jpList = new JPanel(ColumnLayout);
		jpAux = new JPanel(new BorderLayout());
		jpAux.add(jpList, BorderLayout.NORTH);		
		jspList = new JScrollPane(jpAux);
		ColumnLayout.setColumns(1);
		
		jpDades = new JPanel(new BorderLayout());
		jpDades.add(jspList, BorderLayout.CENTER);
		jpDades.setBorder(BorderFactory.createTitledBorder("Jugadors"));
	}
	
	private void propietats(){
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setTitle("JOC");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}
