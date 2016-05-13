package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import model.Constants;

public class LoginWindow extends BaseJPanel {
	
	private final String register = "Register";
	private final String email = "Email:";
	private final String password = "Password:";
	private final String login = "Log in";
	private final String remember = "Remember?";
	private final String space = "     ";
	private static final long serialVersionUID = 1L;
	
	private Tapet backgroundPanel;
	private JPanel northPanel = new JPanel(new BorderLayout());
	private JPanel registerPanel = new JPanel(new GridLayout(2,1));
	private JButton registerButton = new JButton(register);
	private JPanel loginPanel = new JPanel();
	private JLabel mailLabel = new JLabel(email);
	private JTextField mailField = new JTextField();
	private JLabel passwordLabel = new JLabel(password);
	private JPasswordField passwordField = new JPasswordField();
	private JCheckBox checkBox = new JCheckBox();
	private JButton loginButton = new JButton(login);
	private JLabel rememberPassword = new JLabel(remember);
	private JLabel passwordError = new JLabel(space);
	private JLabel emailError = new JLabel(space);
	private JLabel spaceLabel = new JLabel(space);
	private JLabel spaceLabel2 = new JLabel(space);
	private RegisterPanel rPanel = new RegisterPanel();

	public LoginWindow(){
			initElements();
	}
	
	public void showPasswordError(Boolean b){
		if(b){
				BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				passwordError.setIcon(new ImageIcon (img));
				passwordError.setToolTipText("Error found in password, 6 characters are needed, must include upper cases, numbers and a simbol");
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
	
	private void initElements(){
		setLayout(new BorderLayout());
		
		// Crea el panell de fons
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		backgroundPanel = new Tapet(width, height, "resources/casino.jpg");

		
		// Panell de boto de registre
		registerPanel.setBackground(Color.BLACK);
		registerPanel.add(registerButton);
		
		// Crea elements per a l'inici de sessio amb mail
		mailLabel.setFont(Constants.boldFont);
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
		emailError.setPreferredSize(Constants.errorIconDimension);
		emailError.setBackground(Color.BLACK);
		
		// Crea elements per a l'inici de sessio amb mail (part de la contrasenya)
		passwordLabel.setFont(Constants.boldFont);
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
		
		// Crea elements per a l'inici de sessio amb mail (boto de logejar)
		loginButton.setFont(Constants.boldFont);
		loginButton.putClientProperty("action", "Log in");
		loginButton.setBackground(Constants.coolBlue);
		loginButton.setContentAreaFilled(true);
		loginButton.setBorderPainted(false);
		loginButton.setPreferredSize(new Dimension (200, 50));		
		
		// Crea elements per a l'inici de sessio amb mail (checkbox)
		JPanel aux = new JPanel();
		checkBox.setBackground(Color.BLACK);
		aux.add(checkBox);
		rememberPassword.setFont(Constants.boldFont);
		rememberPassword.setForeground(Color.WHITE);
		rememberPassword.setBackground(Color.BLACK);
		aux.add(rememberPassword);
		aux.setBackground(Color.BLACK);
		
		// Part esquerra del panell de logejar
		loginPanel.setBackground(Color.BLACK);
		loginPanel.add(mailLabel);
		loginPanel.add(mailField);
		loginPanel.add(emailError);
		loginPanel.add(spaceLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);
		
		passwordError.setPreferredSize(Constants.errorIconDimension);
		passwordError.setBackground(Color.BLACK);
		
		loginPanel.add(passwordError);
		loginPanel.add(spaceLabel2);
		loginPanel.add(aux);
		
		// Part dreta del panell de logejar
		loginButton.setEnabled(false);
		loginButton.setToolTipText("All fields must be filled correctly in order to log in");
		loginPanel.add(loginButton);
		
		// Afegeix el panell de logejar al background
		northPanel.setBackground(Constants.semiOpaqueBlack);
		northPanel.add(loginPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);
		
		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.add(rPanel, BorderLayout.EAST);
		add(backgroundPanel, BorderLayout.CENTER);
		
	}
	
	public void showEmailError(Boolean b){
		if(b){
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				emailError.setIcon(new ImageIcon (img));
				emailError.setToolTipText("Error found in e-mail format");
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
			emailError.setIcon(null);
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
		rPanel.registerController(listener);
		registerButton.addActionListener(listener);
		loginButton.addActionListener(listener);
	}

	@Override
	public void setManager(Manager manager) {
		rPanel.setManager(manager);
		registerController(manager.getButtonListener());
	}	
}