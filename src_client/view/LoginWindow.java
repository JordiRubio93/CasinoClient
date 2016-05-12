package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.lgooddatepicker.datepicker.DatePicker;
import com.github.lgooddatepicker.datepicker.DatePickerSettings;

import controller.Manager;
import controller.listeners.ButtonListener;
import model.struct.user.User;

public class LoginWindow extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private JPanel backgroundPanel = new JPanel(new BorderLayout());
	private JPanel northPanel = new JPanel(new BorderLayout());
	private JLabel southLabel;
	private JPanel registerPanel = new JPanel(new GridLayout(2,1));
	private JLabel registerLabel = new JLabel("    Don't have an account?");
	private JButton registerButton = new JButton("Register");
	private JPanel loginPanel = new JPanel(new BorderLayout());
	private JLabel mailLabel = new JLabel("Email:");
	private JTextField mailField = new JTextField();
	private JLabel passwordLabel = new JLabel("Password:");
	private JPasswordField passwordField = new JPasswordField();
	private JCheckBox checkBox = new JCheckBox();
	private JButton loginButton = new JButton("Log in");
	private JLabel rememberPassword = new JLabel("Remember?");
	private JPanel leftLoginPanel = new JPanel();
	private JPanel rightLoginPanel = new JPanel(new BorderLayout());
	private JLabel passwordError = new JLabel("   ");
	private JLabel emailError = new JLabel("   ");

	public LoginWindow(){
		setLayout(new BorderLayout());
		southLabel = null;
		JLabel spaceLabel = new JLabel("     ");
		JLabel spaceLabel2 = new JLabel("     ");
		JLabel background = null;
		BufferedImage img = null;
		BufferedImage img2 = null;
		try {
			img = ImageIO.read(new File("resources/casino.jpg"));
			img2 = ImageIO.read(new File("resources/label.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
		
		registerButton.setFont(new Font("Calibri", Font.BOLD, 24));
		registerButton.setBackground(new Color(215, 143, 35));
		registerButton.setContentAreaFilled(true);

		registerButton.putClientProperty("action", "Register");
		registerButton.setBorderPainted(false);
		registerButton.setPreferredSize(new Dimension (200, 50));
		
		registerLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		registerLabel.setForeground(Color.WHITE);
		registerLabel.setBackground(Color.BLACK);
		
		registerPanel.setBackground(Color.BLACK);
		registerPanel.add(registerLabel);
		registerPanel.add(registerButton);
		
		mailLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		mailLabel.setForeground(Color.WHITE);
		mailLabel.setBackground(Color.BLACK);
		mailField.setPreferredSize(new Dimension(150, 20));
		mailField.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	            super.keyReleased(e);
	            if(String.copyValueOf(passwordField.getPassword()).length() > 5 && mailField.getText().length() > 0 )
		              loginButton.setEnabled(true);
	            else
	            	 loginButton.setEnabled(false);
	        }
	    });
		emailError.setPreferredSize(new Dimension (48, 48));
		emailError.setBackground(Color.BLACK);
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBackground(Color.BLACK);
		passwordField.setPreferredSize(new Dimension(150, 20));
		passwordField.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	            super.keyReleased(e);
	            if(String.copyValueOf(passwordField.getPassword()).length() > 5 && mailField.getText().length() > 0 )
	                loginButton.setEnabled(true);
	            else
	            	loginButton.setEnabled(false);
	        }
	    });
		loginButton.setFont(new Font("Calibri", Font.BOLD, 24));
		loginButton.putClientProperty("action", "Log in");
		loginButton.setBackground(new Color(56, 192, 196));
		loginButton.setContentAreaFilled(true);
		loginButton.setBorderPainted(false);
		loginButton.setPreferredSize(new Dimension (200, 50));
		
		
		
		
		JPanel aux = new JPanel();
		checkBox.setBackground(Color.BLACK);
		aux.add(checkBox);
		rememberPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		rememberPassword.setForeground(Color.WHITE);
		rememberPassword.setBackground(Color.BLACK);
		aux.add(rememberPassword);
		aux.setBackground(Color.BLACK);
		
		leftLoginPanel.setBackground(Color.BLACK);
		leftLoginPanel.add(mailLabel);
		leftLoginPanel.add(mailField);
		leftLoginPanel.add(emailError);
		leftLoginPanel.add(spaceLabel);
		leftLoginPanel.add(passwordLabel);
		leftLoginPanel.add(passwordField);
		
		passwordError.setPreferredSize(new Dimension (48, 48));
		passwordError.setBackground(Color.BLACK);
		
		leftLoginPanel.add(passwordError);
		leftLoginPanel.add(spaceLabel2);
		leftLoginPanel.add(aux);

		rightLoginPanel.setBackground(Color.BLACK);
		loginButton.setEnabled(false);
		rightLoginPanel.add(loginButton, BorderLayout.EAST);

		loginPanel.setBackground(Color.BLACK);
		loginPanel.add(leftLoginPanel, BorderLayout.CENTER);
		loginPanel.add(rightLoginPanel, BorderLayout.SOUTH);

		northPanel.setBackground(new Color(0, 0, 0));
		northPanel.add(loginPanel, BorderLayout.EAST);
		northPanel.add(registerPanel, BorderLayout.WEST);
		add(northPanel, BorderLayout.NORTH);
		
		southLabel = new JLabel(new ImageIcon(img2));
		southLabel.setBackground(Color.BLACK);
		add(southLabel, BorderLayout.SOUTH);
		
		background = new JLabel(new ImageIcon(img));		
		backgroundPanel.add(background, BorderLayout.CENTER);
		add(backgroundPanel, BorderLayout.CENTER);	
	}
	
	public void showPasswordError(Boolean b){
		if(b){
				BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				passwordError.setIcon(new ImageIcon (img));
				passwordError.setToolTipText("Error en la Password");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					passwordError.setIcon(new ImageIcon (img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
			}
		else{
			passwordError.setBackground(Color.BLACK);
			passwordError.setIcon(null);
		}
		
	}
	
	public void showEmailError(Boolean b){
		if(b){
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				emailError.setIcon(new ImageIcon (img));
				emailError.setToolTipText("Error del formato Email");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					emailError.setIcon(new ImageIcon (img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			emailError.setBackground(Color.BLACK);
			passwordError.setIcon(null);
		}
			
	}
	
	public User getUser(){
		User u = new User();
		u.setEmail(mailField.getText());
		u.setPassword(String.copyValueOf(passwordField.getPassword()));
		u.toString();
		return u;
	}
	
	public void registerController(ButtonListener listener){
		
		registerButton.addActionListener(listener);
		loginButton.addActionListener(listener);
	}

	@Override
	public void setManager(Manager manager) {
		registerController(manager.getButtonLister());
	}	
}