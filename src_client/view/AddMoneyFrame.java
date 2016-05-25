package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Constants;
import controller.Manager;
/**
 * 
 * <p>
 * <b> Classe: AddMoneyFrame </b> <br/>
 * </p>
 * 
 * Ventana emergent per afegir diners
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class AddMoneyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel passLabel;
	private JPasswordField passField;
	private JLabel cashLabel;
	private JTextField cashField;
	private JButton goButton;
	private Manager manager;
	
	public AddMoneyFrame(Manager manager){
		this.manager = manager;
		initElements();
		registerController();
	}
	
	private void initElements(){
		passLabel = new JLabel("password:     ");
		passField = new JPasswordField();
		cashLabel = new JLabel("money to add: ");
		cashField = new JTextField();
		goButton = new JButton("Add money!");
		
		setLayout(new GridLayout(3, 1));
		setBackground(Color.BLACK);
		setSize(new Dimension(700, 200));
		setLocationRelativeTo(null);
		setTitle("ADD MONEY");
		
		passLabel.setOpaque(false);
		passLabel.setFont(Constants.plainFont);
		passLabel.setForeground(Color.WHITE);
		
		passField.setOpaque(true);
		passField.setPreferredSize(new Dimension(500, 20));
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		p1.add(passLabel);
		p1.add(passField);
		
		cashLabel.setOpaque(false);
		cashLabel.setFont(Constants.plainFont);
		cashLabel.setForeground(Color.WHITE);
		
		cashField.setOpaque(true);
		cashField.setPreferredSize(new Dimension(500, 20));
		cashField.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	            super.keyReleased(e);
	            if(String.copyValueOf(passField.getPassword()).length() > 5 && cashField.getText().length() > 0 )
	            	goButton.setEnabled(true);
	            else
	            	goButton.setEnabled(false);
	        }
	    });
		
		JPanel p2 = new JPanel();
		p2.setBackground(Color.BLACK);
		p2.add(cashLabel);
		p2.add(cashField);
		
		goButton.setEnabled(false);
		goButton.setToolTipText("All fields must be filled correctly in order to add money");
		goButton.setFont(Constants.boldFont);
		goButton.setForeground(Color.WHITE);
		goButton.setBackground(Constants.coolOrange);
		goButton.setContentAreaFilled(true);
		goButton.setBorderPainted(false);
		goButton.putClientProperty("action", "Go Add Money");
		
		add(p1);
		add(p2);
		add(goButton);
	}
	
	public String getPassword(){
		return new String(passField.getPassword());
	}
	
	public float getCash(){
		Float money = Float.parseFloat(cashField.getText());
		return money;	
	}
	
	public void registerController() {
		goButton.addActionListener(manager.getController());
	}
}