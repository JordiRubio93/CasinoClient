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

import controller.Constants;
import controller.Manager;
/**
 * 
 * <p>
 * <b> Classe: PasswordFrame </b> <br/>
 * </p>
 * 
 * Finestra emergent de canvi de password
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
public class PasswordFrame extends JFrame {
	private static final long serialVersionUID = 1L;
		private JLabel passLabel;
		private JPasswordField passField;
		private JLabel pass2Label;
		private JPasswordField pass2Field;
		private JButton goButton;
		private Manager manager;
		
		public PasswordFrame(Manager manager){
			this.manager = manager;
			initElements();
			registerController();
		}
		
		private void initElements(){
			passLabel = new JLabel("password:           ");
			passField = new JPasswordField();
			pass2Label = new JLabel("repeat password: ");
			pass2Field = new JPasswordField();
			goButton = new JButton("Change it!");
			
			setLayout(new GridLayout(3, 1));
			setBackground(Color.BLACK);
			setSize(new Dimension(700, 200));
			setLocationRelativeTo(null);
			setTitle("CHANGE PASSWORD");
			
			passLabel.setOpaque(false);
			passLabel.setFont(Constants.plainFont);
			passLabel.setForeground(Color.WHITE);
			
			passField.setOpaque(true);
			passField.setPreferredSize(new Dimension(500, 20));
			
			JPanel p1 = new JPanel();
			p1.setBackground(Color.BLACK);
			p1.add(passLabel);
			p1.add(passField);
			
			pass2Label.setOpaque(false);
			pass2Label.setFont(Constants.plainFont);
			pass2Label.setForeground(Color.WHITE);
			pass2Field.setOpaque(true);
			pass2Field.setPreferredSize(new Dimension(500, 20));
			pass2Field.addKeyListener(new KeyAdapter() {
		        public void keyReleased(KeyEvent e) {
		            super.keyReleased(e);
		            if(String.copyValueOf(passField.getPassword()).length() > 5 && String.copyValueOf(pass2Field.getPassword()).length() > 5 && String.copyValueOf(passField.getPassword()).equals(String.copyValueOf(pass2Field.getPassword())))
		                goButton.setEnabled(true);
		            else
		            	goButton.setEnabled(false);
		        }
		    });
			
			JPanel p2 = new JPanel();
			p2.setBackground(Color.BLACK);
			p2.add(pass2Label);
			p2.add(pass2Field);
			
			goButton.setEnabled(false);
			goButton.setToolTipText("All fields must be filled correctly in order to change your password");
			goButton.setFont(Constants.boldFont);
			goButton.setForeground(Color.WHITE);
			goButton.setBackground(Constants.coolOrange);
			goButton.setContentAreaFilled(true);
			goButton.setBorderPainted(false);
			goButton.putClientProperty("action", "Go Change Password");
			
			add(p1);
			add(p2);
			add(goButton);
		}
		
		public String getPassword(){
			return new String(passField.getPassword());
		}
		
		public String getPassword2(){
			return new String(pass2Field.getPassword());	
		}
		
		public void registerController() {
			goButton.addActionListener(manager.getController());
		}
}