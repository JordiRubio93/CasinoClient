package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Constants;
import controller.Manager;

public abstract class GameView extends BaseJPanel {
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
	protected Manager manager;
	private DateFormat dateFormat;
	
	public GameView(){
		initElements();
		actualitzaTemps();
		createList();
	}
	
	private void initElements(){
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		//Label temps
		jlTemps = new JLabel(dateFormat.format(date));
		jlTemps.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlTemps.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		
		//panell temps		
		jpTemps = new JPanel();
		jpTemps.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jpTemps.add(jlTemps);
		add(jpTemps);
		
		//boton salir
		jbExit = new JButton("Exit");
		jbExit.setBackground(new Color(255,255,255));
		jbExit.setForeground(Constants.coolBlue);
		
		//boton Apostar
		jbBet = new JButton("Bet!");
		jbBet.setBackground(new Color(255,255,255));
		jbBet.setForeground(Constants.coolOrange);
		
		//barra Inferior (botones)
		jpOptions = new JPanel(new GridLayout(1,2));
		jpOptions.add(jbExit);
		jpOptions.add(jbBet);
		
		//tirar apostas cap adalt
		columnLayout = new GridLayout();
		jpList = new JPanel(columnLayout);
		jpAux = new JPanel(new BorderLayout());
		jpAux.add(jpList, BorderLayout.NORTH);		
		jspList = new JScrollPane(jpAux);
		columnLayout.setColumns(1);
	}
	
	public void registerController(){	
		jbBet.addActionListener(manager.getController());
		jbExit.addActionListener(manager.getController());
	}
	
	/**
	 * Funcio que s'encarrega de actualitzar el rellotje superior
	 */
	private void actualitzaTemps(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				jlTemps.setText(dateFormat.format(Calendar.getInstance().getTime()));
			}
		}, 0, TimeUnit.SECONDS.toMillis(1));
	}

	/**
	 * Crea el panel lateral de apostes
	 */
	protected void createList(){
		jpDades = new JPanel(new BorderLayout());
		jpDades.add(jspList, BorderLayout.CENTER);
		jpDades.add(jpOptions, BorderLayout.SOUTH);
		jpDades.setBorder(BorderFactory.createTitledBorder(Constants.BET_LABEL));
	}
	/**
	 * Managers...
	 */
	public abstract Manager getManager();
	public abstract void setManager(Manager manager);
	
}
