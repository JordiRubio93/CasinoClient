package view.cavalls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Constants;
import controller.Manager;
import view.Dialeg;


/**
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */

public class PickHorseView extends JFrame {
	private static final long serialVersionUID = 1L;
	//main controler identify label
	public static final String next = "  >  ";
	public static final String previous = "  <  ";
	private Manager manager;
	private String[] opcions;
	private int index = 0;
	private JButton jbDreta;
	private JButton jbEsquerra;
	private JButton jbCavall;

	private Dialeg dialeg;
	private JPanel jpEleccio;
	private JTextField jtfAmount;
		
	/**
	 * Constructor 1
	 */
	public PickHorseView(){
		initElements();
		dialeg = new Dialeg();
		propietats();		
	}
	
	/**
	 * Constructor 2
	 * @param manager
	 */
	public PickHorseView(Manager manager){
		this.manager =(manager);
		initElements();
		registerController();
		dialeg = new Dialeg();
		propietats();		
	}
	
	public void loadInfo(Manager manager){
		opcions = new String[Constants.nHorses];
		for(int i = 0; i < opcions.length; i++){
			opcions[i] =  manager.getGameManager().getHorsesList().get(i).getName();
		}
	}
	
	/**
	 * Inicialitzem els elements
	 */
	protected void initElements() {
		
		setLayout(new BorderLayout());

		//boto principal
		jbCavall = new JButton();
		jbCavall.setHorizontalAlignment(JButton.CENTER);
		jbCavall.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbCavall.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbCavall.setFont(new Font("Serif", Font.BOLD, 22));
		jbCavall.setBorder(BorderFactory.createEmptyBorder());
		jbCavall.putClientProperty("action", "This Horse");
		
		//boto dret
		jbDreta = new JButton(next);
		jbDreta.putClientProperty("action", next);
		jbDreta.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbDreta.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbDreta.setFont(new Font("Serif", Font.BOLD, 26));
		jbDreta.setBorder(BorderFactory.createEmptyBorder());

		//boto esquerra
		jbEsquerra = new JButton(previous);
		jbEsquerra.putClientProperty("action", previous);
		jbEsquerra.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbEsquerra.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbEsquerra.setFont(new Font("Serif", Font.BOLD, 26));
		jbEsquerra.setBorder(BorderFactory.createEmptyBorder());
				
		 //crem un panell per gestionar la quantitat de les apostes
		jpEleccio = new JPanel(new BorderLayout());
		jpEleccio.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jpEleccio.setBorder(BorderFactory.createEtchedBorder());
		JLabel jlIntro = new JLabel(" Money to bet : ");
		jlIntro.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		jtfAmount = new JTextField();
		jpEleccio.add(jlIntro, BorderLayout.WEST);
		jpEleccio.add(jtfAmount, BorderLayout.CENTER);
		
		//agreguem
		add(jbCavall, BorderLayout.CENTER);
		add(jbDreta, BorderLayout.EAST);
		add(jbEsquerra, BorderLayout.WEST);
		add(jpEleccio, BorderLayout.SOUTH);
	}

	/**
	 * Establim les propietats de la finestra
	 */
	public void propietats(){
		setSize(300,100);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public String getAmount(){
		return jtfAmount.getText();
	}

	/**
	 * Passem al cavall de la dreta
	 */
	public void passaDreta(){
		if(index == 11) index = 0;
		else index++;
		actualitzaText();
	}
	/**
	 * Passem al cavall de l'esquerra
	 */
	public void passaEsquerra(){
		if(index == 0) index = 11;
		else index--;
		actualitzaText();
	}

	/**
	 * Estableix el nom del cavall a mostrar en aquell moment
	 */
	private void actualitzaText(){
		jbCavall.setText(opcions[index]);
	}

	/**
	 * Per confirmar
	 */
	public void obreDialeg(){
		dialeg.setConfirmText("Are you sure you want to bet " + jtfAmount.getText() + " � for this horse?");
	}
	
	public Dialeg getDialeg() {
		return dialeg;
	}
	
	public String getHorseName(){
		return jbCavall.getText();
	}
	
	public void setManager(Manager manager){
		this.manager = manager;
		registerController();
		
	}
	public void registerController(){
		//aprofitem per carregar les dades
		loadInfo(manager);
		jbEsquerra.addActionListener(manager.getController());
		jbDreta.addActionListener(manager.getController());
		jbCavall.addActionListener(manager.getController());
	}

	/**
	 * Resetegem la vista
	 */
	public void clean() {
		index = 0;
		jbCavall.setText(opcions[index]);
		jtfAmount.setText(new String());
	}
}
