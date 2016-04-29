package view.horses;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.listeners.CentralMouseController;
import controller.listeners.HorseButtonController;
import controller.listeners.LButtonController;
import controller.listeners.LMouseController;
import controller.listeners.RButtonController;
import controller.listeners.RMouseController;
import model.struct.horses.HorseData;
import view.Dialeg;

public class ChooseHorse extends JFrame {
	private static final long serialVersionUID = 1L;
	private String[] opcions;
	private JPanel jpActual;
	private JPanel jpFinestra;
	private JButton jbDreta;
	private JButton jbEsquerra;
	private int index;
	private JButton jbCavall;
	private JPanel jpCavall;
	private Dialeg dialeg;
	private JPanel jpEleccio;
	private JTextField jtfAmount;
	
	public ChooseHorse(LinkedList<HorseData> hdList) {
		opcions = new String[12];
		
		for(int i = 0; i < 12; i++){
			opcions[i] = hdList.get(i).getName();
		}
		
		jbCavall = new JButton(opcions[index]);
		jbCavall.setHorizontalAlignment(JButton.CENTER);
		jbCavall.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbCavall.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbCavall.setFont(new Font("Serif", Font.BOLD, 22));
		jbCavall.setBorder(BorderFactory.createEmptyBorder());
		jpCavall = new JPanel(new BorderLayout());
		jpCavall.add(jbCavall, BorderLayout.CENTER);
		
		jpActual = new JPanel(new BorderLayout());
		jpActual.add(jpCavall);
		
		jbDreta = new JButton("  >  ");
		jbEsquerra = new JButton("  <  ");
		
		jbDreta.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbDreta.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbDreta.setFont(new Font("Serif", Font.BOLD, 26));
		jbDreta.setBorder(BorderFactory.createEmptyBorder());
		
		jbEsquerra.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbEsquerra.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbEsquerra.setFont(new Font("Serif", Font.BOLD, 26));
		jbEsquerra.setBorder(BorderFactory.createEmptyBorder());
		
		jpFinestra = new JPanel(new BorderLayout());
		
		jpFinestra.add(jpActual, BorderLayout.CENTER);
		jpFinestra.add(jbDreta, BorderLayout.EAST);
		jpFinestra.add(jbEsquerra, BorderLayout.WEST);
		
		jpEleccio = new JPanel(new BorderLayout());
		jpEleccio.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jpEleccio.setBorder(BorderFactory.createEtchedBorder());
		creaEleccio();
		jpFinestra.add(jpEleccio, BorderLayout.SOUTH);
		
		this.getContentPane().add(jpFinestra);
		
		propietats();
		
		dialeg = new Dialeg();
	}
	
	private void creaEleccio() {
		JLabel jlIntro = new JLabel(" Diners a apostar : ");
		jlIntro.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		jtfAmount = new JTextField();
		jpEleccio.add(jlIntro, BorderLayout.WEST);
		jpEleccio.add(jtfAmount, BorderLayout.CENTER);
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
		jbCavall.setHorizontalAlignment(JLabel.CENTER);
		jbCavall.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jbCavall.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jbCavall.setFont(new Font("Serif", Font.BOLD, 22));
		jbCavall.setBorder(BorderFactory.createEmptyBorder());
		jpCavall.add(jbCavall, BorderLayout.CENTER);
		jpActual.add(jpCavall);
	}
	
	public void obreDialeg(){
		dialeg.setConfirmText("Segur que vols apostar " + jtfAmount.getText() + " € per aquest cavall?");
	}
	
	public void pintaBoto(JButton jBoto){
		jBoto.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		if(jBoto.equals(jbCavall)) jBoto.setBackground(new Color(0.56f, 0.73f, 0.56f, 1.0f));
		else jBoto.setBackground(new Color(0.43f, 0.57f, 0.85f, 1.0f));
	}
	public void despintaBoto(JButton jBoto){
		jBoto.setForeground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jBoto.setBackground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
	}
	
	public JButton getR(){
		return jbDreta;
	}
	public JButton getL(){
		return jbEsquerra;
	}
	public JButton getCentral(){
		return jbCavall;
	}
	
	public void registerController(RButtonController rb, LButtonController lb, HorseButtonController hb, CentralMouseController cm,
			RMouseController rm, LMouseController lm){
		jbDreta.addActionListener(rb);
		jbEsquerra.addActionListener(lb);
		jbCavall.addActionListener(hb);
		jbCavall.addMouseListener(cm);
		jbDreta.addMouseListener(rm);
		jbEsquerra.addMouseListener(lm);
	}
	
	private void propietats(){
		this.setSize(300, 120);
		this.setResizable(false);
		this.setTitle("HORSES");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public Dialeg getDialeg() {
		return dialeg;
	}
	
	public String getHorseName(){
		return jbCavall.getText();
	}
}
