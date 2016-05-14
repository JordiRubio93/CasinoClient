package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.datepicker.DatePicker;
import com.github.lgooddatepicker.datepicker.DatePickerSettings;

import controller.Manager;
import controller.listeners.MainButtonsController;
import model.Constants;

public class RegisterPanel extends BaseJPanel{
	private final String main = "Don't have an account? Join us!";
	private final String name = "Name:";
	private final String surname = "Surname:";
	private final String male = "Male:";
	private final String female = "Female:";
	private final String age = "Age:";
	private final String email = "e-mail:";
	private final String password = "Password:";
	private final String password2 = "    Repeat password:    ";
	private final String join = "Join us!";
	private final String guest = "Try as guest";
	private final String space = "     ";
	private static final long serialVersionUID = 1L;
	
	private JLabel mainLabel = new JLabel(main);
	private JPanel mainPanel = new JPanel(new GridBagLayout());
	private JLabel nameLabel = new JLabel(name);
	private JTextField nameField = new JTextField();
	private JLabel surnameLabel = new JLabel(surname);
	private JTextField surnameField = new JTextField();
	private JRadioButton maleButton = new JRadioButton(male);
    private JRadioButton femaleButton = new JRadioButton(female);
    private ButtonGroup bG = new ButtonGroup();
	private JLabel ageLabel = new JLabel(age);
	private JLabel errorAge = new JLabel(space);
	private JLabel mailLabel = new JLabel(email);
	private JTextField mailField = new JTextField();
	private JLabel errorMail = new JLabel(space);
	private JLabel passwordLabel = new JLabel(password);
	private JPasswordField passwordField = new JPasswordField();
	private JLabel errorPassword = new JLabel(space);
	private JLabel passwordLabel2 = new JLabel(password2);
	private JPasswordField passwordField2 = new JPasswordField();
	private JLabel errorPassword2 = new JLabel(space);
	private JLabel errorName = new JLabel(space);
	private JLabel errorSurname = new JLabel(space);
	private JButton registerButton = new JButton(join);
	private JButton guestButton = new JButton(guest);
	private JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
	
	public RegisterPanel(){
		initElements();
	}
	
	private void initElements(){
		setLayout(new BorderLayout());
		Color back = new Color(0, 0, 0, 80);
		
		// Agafa les dimensions de la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		Dimension preferredSize = new Dimension((int)(width * 0.184), (int)(height * 0.02));
		setBackground(back);
		
		// Crea constraint
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.setOpaque(false);
		
		// Dona valors al main label
		mainLabel.setFont(Constants.boldFont);
		mainLabel.setPreferredSize(new Dimension((int)(width * 0.184),(int)(height * 0.1)));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBackground(Color.BLACK);
		mainLabel.setForeground(Constants.coolBlue);
		add(mainLabel, BorderLayout.NORTH);
		
		// Dona valors al name label
		nameLabel.setFont(Constants.plainFont);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBackground(back);
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(nameLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(nameField, c);
		c.gridx = 2;
		c.gridy = 1;
		mainPanel.add(errorName, c);
		
		// Dona valors al surname label
		surnameLabel.setFont(Constants.plainFont);
		surnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		surnameLabel.setForeground(Color.WHITE);
		surnameLabel.setBackground(back);
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(surnameLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(surnameField, c);
		c.gridx = 2;
		c.gridy = 2;
		mainPanel.add(errorSurname, c);
		
		// Dona valors al age label
		ageLabel.setFont(Constants.plainFont);
		ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ageLabel.setForeground(Color.WHITE);
		ageLabel.setBackground(back);
		
		// Dona valors al date label
		DatePickerSettings dateSettings = new DatePickerSettings();
	    dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
	    DatePicker datePicker = new DatePicker(dateSettings);	
	    datePicker.setPreferredSize(preferredSize);
		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(ageLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		mainPanel.add(datePicker, c);
		c.gridx = 2;
		c.gridy = 3;
		mainPanel.add(errorAge, c);
		
		// Dona valors al mail label
		mailLabel.setFont(Constants.plainFont);
		mailLabel.setForeground(Color.WHITE);
		mailLabel.setBackground(back);
		mailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 4;
		mainPanel.add(mailLabel, c);
		c.gridx = 1;
		c.gridy = 4;
		mainPanel.add(mailField, c);
		c.gridx = 2;
		c.gridy = 4;
		mainPanel.add(errorMail, c);
		
		// Dona valors al password label
		passwordLabel.setFont(Constants.plainFont);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBackground(back);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 5;
		mainPanel.add(passwordLabel, c);
		c.gridx = 1;
		c.gridy = 5;
		mainPanel.add(passwordField, c);
		c.gridx = 2;
		c.gridy = 5;
		mainPanel.add(errorPassword, c);
		
		// Dona valors al repeat password label
		passwordLabel2.setFont(Constants.plainFont);
		passwordLabel2.setForeground(Color.WHITE);
		passwordLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel2.setBackground(back);
		c.gridx = 0;
		c.gridy = 6;
		mainPanel.add(passwordLabel2, c);
		c.gridx = 1;
		c.gridy = 6;
		mainPanel.add(passwordField2, c);
		c.gridx = 2;
		c.gridy = 6;
		mainPanel.add(errorPassword2, c);
		
		//Dona valors als botons male - female
		maleButton.setOpaque(false);
		maleButton.setHorizontalAlignment(SwingConstants.CENTER);
		maleButton.setFont(Constants.plainFont);
		maleButton.setForeground(Color.WHITE);
		bG.add(maleButton);
		buttonsPanel.setBackground(Color.BLACK);
		buttonsPanel.add(maleButton);
		
		femaleButton.setOpaque(false);
		femaleButton.setHorizontalAlignment(SwingConstants.CENTER);
		femaleButton.setFont(Constants.plainFont);
		femaleButton.setForeground(Color.WHITE);
	
		bG.add(femaleButton);
		buttonsPanel.add(femaleButton);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 7;
		
		mainPanel.add(buttonsPanel, c);
	
		// Dona valors al register button
		registerButton.setEnabled(false);
		registerButton.setToolTipText("All fields must be filled correctly in order to register");
		registerButton.setFont(Constants.boldFont);
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Constants.coolOrange);
		registerButton.setContentAreaFilled(true);
		registerButton.setBorderPainted(false);
		registerButton.putClientProperty("action", "Register");
		registerButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		c.gridx = 0;
		c.gridy = 8;
		c.weighty = 0;
		mainPanel.add(registerButton, c);
		
		// Dona valors al guest button
		guestButton.setFont(Constants.boldFont);
		guestButton.setForeground(Color.WHITE);
		guestButton.setBackground(Constants.coolGreen);
		guestButton.setContentAreaFilled(true);
		guestButton.setBorderPainted(false);
		guestButton.putClientProperty("action", "Try as guest");
		guestButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		c.gridx = 0;
		c.gridy = 9;
		mainPanel.add(guestButton, c);
		
		add(mainPanel, BorderLayout.CENTER);
	}
	
	public void showNameError(Boolean b){
		if(b){
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				errorName.setIcon(new ImageIcon (img));
				errorName.setToolTipText("Error found in e-mail format");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					errorName.setIcon(new ImageIcon (img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			errorName.setBackground(Color.BLACK);
			errorName.setIcon(null);
		}
	}
	
	public void showSurnameError(Boolean b){
		if(b){
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				errorSurname.setIcon(new ImageIcon (img));
				errorSurname.setToolTipText("Error found in e-mail format");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					errorSurname.setIcon(new ImageIcon (img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			errorSurname.setBackground(Color.BLACK);
			errorSurname.setIcon(null);
		}
			
	}
	
	public void showEmailError(Boolean b){
		if(b){
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				errorMail.setIcon(new ImageIcon (img));
				errorMail.setToolTipText("Error found in e-mail format");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					errorMail.setIcon(new ImageIcon (img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			errorMail.setBackground(Color.BLACK);
			errorMail.setIcon(null);
		}	
	}
	
	public void showPasswordError(Boolean b){
		if(b){
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				errorPassword.setIcon(new ImageIcon (img));
				errorPassword.setToolTipText("Error found in password, 6 characters are needed, must include upper cases, numbers and a simbol");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					errorPassword.setIcon(new ImageIcon (img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		}else{
			errorPassword.setBackground(Color.BLACK);
			errorPassword.setIcon(null);
		}
	}
	
	public void showPassword2Error(Boolean b){
		if(b){
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				errorPassword2.setIcon(new ImageIcon (img));
				errorPassword2.setToolTipText("Two passwords must match");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					errorPassword2.setIcon(new ImageIcon (img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		}else{
			errorPassword2.setBackground(Color.BLACK);
			errorPassword2.setIcon(null);
		}
	}

	public void registerController(MainButtonsController listener){
		registerButton.addActionListener(listener);
		guestButton.addActionListener(listener);
	}
	
	@Override
	public void setManager(Manager manager) {
		registerController(manager.getButtonListener());
	}
}
