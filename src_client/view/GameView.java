package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Constants;

public abstract class GameView extends BaseJPanel {
	private static final long serialVersionUID = 1L;

	protected BaseJPanel gamePanel;
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
	
	protected void initElements(){
		setLayout(new BorderLayout());
		
		//Label temps
		jlTemps = new JLabel();
		jlTemps.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlTemps.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		//panell temps		
		jpTemps = new JPanel();
		jpTemps.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jpTemps.add(jlTemps);
		add(jpTemps, BorderLayout.NORTH);
		
		//boton salir
		jbExit = new JButton("Exit");
		jbExit.setBackground(Color.WHITE);
		jbExit.setForeground(Constants.coolBlue);
		
		//boton Apostar
		jbBet = new JButton("Bet!");
		jbBet.setBackground(Color.WHITE);
		jbBet.setForeground(Constants.coolOrange);
		
		//barra Inferior (botones)
		jpOptions = new JPanel(new GridLayout(1,2));
		jpOptions.add(jbExit);
		jpOptions.add(jbBet);
		
		//panell de apostes
		jpDades = new JPanel(new BorderLayout());
		columnLayout = new GridLayout();
		columnLayout.setColumns(1);
		
		//tirar apostas cap adalt	
		jpList = new JPanel(columnLayout);
		jpAux = new JPanel(new BorderLayout());
		jpAux.add(jpList, BorderLayout.NORTH);		
		jspList = new JScrollPane(jpAux);
		jpDades.add(jspList, BorderLayout.CENTER);
		jpDades.add(jpOptions, BorderLayout.SOUTH);
		jpDades.setBorder(BorderFactory.createTitledBorder(Constants.BET_LABEL));
		add(jpDades, BorderLayout.EAST);
	}
	
	public void setGamePanel(BaseJPanel panel){
		//add(gamePanel, )
	}
	
	public void registerController(){	
		jbBet.addActionListener(getManager().getController());
		jbExit.addActionListener(getManager().getController());
	}
	
	/**
	 * Funcio que s'encarrega de actualitzar el rellotje superior
	 */
	private void actualitzaTemps(String time){
		jlTemps.setText(time);
	
	}
	protected void createDaemonTime(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			@Override
			public void run() {
				try{
					actualitzaTemps(dateFormat.format(Calendar.getInstance().getTime()));
				}catch (Exception e){
					e.printStackTrace();
				}
			
			}
		}, Constants.SPLASH_TIME, TimeUnit.SECONDS.toMillis(1));
	}
}
