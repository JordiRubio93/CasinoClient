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

import controller.Manager;
import view.Dialeg;

public class PickHorseView extends JFrame {
	private static final long serialVersionUID = 1L;
	//main controler identify label
	public static final String next = "  >  ";
	public static final String previous = "  <  ";
	private final int horses_num = 12;
	private Manager manager;
	private String[] opcions;
	private int index;
	
	private JButton jbDreta;
	private JButton jbEsquerra;
	private JButton jbCavall;

	private Dialeg dialeg;
	private JPanel jpEleccio;
	private JTextField jtfAmount;
		

	public PickHorseView(Manager manager){
		this.manager =(manager);
		initElements();
		registerController();
		dialeg = new Dialeg();
		
	}
	
	public void loadInfo(Manager manager){
		opcions = new String[horses_num];
		for(int i = 0; i < opcions.length; i++){
			System.out.println(manager.toString());
			opcions[i] =  manager.getGameManager().getHorsesList().get(i).getName();
		}
		jbCavall.setText(opcions[index]);
	}
	
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
		
		setSize(600, 300);
		setVisible(true);
	}
	
	public String getAmount(){
		return jtfAmount.getText();
	}

	public void passaDreta(){
		if(index == 11) index = 0;
		else index++;
		actualitzaText();
	}
	public void passaEsquerra(){
		if(index == 0) index = 11;
		else index--;
		actualitzaText();
	}
	
	private void actualitzaText(){
		jbCavall.setText(opcions[index]);
	}
	
	public void obreDialeg(){
		dialeg.setConfirmText("Are you sure you want to bet " + jtfAmount.getText() + " € for this horse?");
	}
	
	public Dialeg getDialeg() {
		return dialeg;
	}
	
	public String getHorseName(){
		return jbCavall.getText();
	}
	
	public void registerController(){
		//aprofitem per carregar les dades
		loadInfo(manager);
		jbEsquerra.addActionListener(manager.getController());
		jbEsquerra.addActionListener(manager.getController());
		jbEsquerra.addActionListener(manager.getController());
	}

	public void clean() {
		jtfAmount.setText("");
	}
}
