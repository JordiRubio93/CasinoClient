package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ButtonListener;

public class RegisterWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel mainLabel = new JLabel("Please fill in all the following fields");
	private JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel nameLabel = new JLabel("Name:");
	private JTextField nameField = new JTextField();
	private JPanel surnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel surnameLabel = new JLabel("Surname:");
	private JTextField surnameField = new JTextField();
	private JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel ageLabel = new JLabel("Age:");
	private JTextField ageField = new JTextField();
	private JPanel mailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel mailLabel = new JLabel("e-mail:");
	private JTextField mailField = new JTextField();
	private JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel passwordLabel = new JLabel("Password:");
	private JPasswordField passwordField = new JPasswordField();
	private JPanel passwordPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel passwordLabel2 = new JLabel("Repeat password:");
	private JPasswordField passwordField2 = new JPasswordField();
	private JButton registerButton = new JButton("Join us!");
	
	public RegisterWindow(){
		setLayout(new GridLayout(8, 1));
		setBackground(Color.BLACK);
		setSize(new Dimension(500, 500));
		
		mainLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		mainLabel.setAlignmentX(SwingConstants.CENTER);
		mainLabel.setBackground(Color.BLACK);
		mainLabel.setForeground(new Color(56, 192, 196));
		mainPanel.add(mainLabel);
		mainPanel.setBackground(Color.BLACK);
		
		nameLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBackground(Color.BLACK);
		nameField.setPreferredSize(new Dimension(400,20));
		namePanel.setBackground(Color.BLACK);
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		surnameLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		surnameLabel.setForeground(Color.WHITE);
		surnameLabel.setBackground(Color.BLACK);
		surnameField.setPreferredSize(new Dimension(380,20));
		surnamePanel.setBackground(Color.BLACK);
		surnamePanel.add(surnameLabel);
		surnamePanel.add(surnameField);
		
		ageLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		ageLabel.setForeground(Color.WHITE);
		ageLabel.setBackground(Color.BLACK);
		ageField.setPreferredSize(new Dimension(420,20));
		agePanel.setBackground(Color.BLACK);
		agePanel.add(ageLabel);
		agePanel.add(ageField);
		
		mailLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		mailLabel.setForeground(Color.WHITE);
		mailLabel.setBackground(Color.BLACK);
		mailField.setPreferredSize(new Dimension(400,20));
		mailPanel.setBackground(Color.BLACK);
		mailPanel.add(mailLabel);
		mailPanel.add(mailField);
		
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBackground(Color.BLACK);
		passwordField.setPreferredSize(new Dimension(380,20));
		passwordPanel.setBackground(Color.BLACK);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		passwordLabel2.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordLabel2.setForeground(Color.WHITE);
		passwordLabel2.setBackground(Color.BLACK);
		passwordField2.setPreferredSize(new Dimension(325,20));
		passwordPanel2.setBackground(Color.BLACK);
		passwordPanel2.add(passwordLabel2);
		passwordPanel2.add(passwordField2);	
		
		registerButton.setFont(new Font("Calibri", Font.BOLD, 24));
		registerButton.setBackground(new Color(56, 192, 196));
		registerButton.setContentAreaFilled(true);
		registerButton.setBorderPainted(false);
		registerButton.setPreferredSize(new Dimension (150, 50));
		
		add(mainPanel);
		add(namePanel);
		add(surnamePanel);
		add(agePanel);
		add(mailPanel);
		add(passwordPanel);
		add(passwordPanel2);
		add(registerButton);
	}
	
	public void registerController(ButtonListener listener){
		registerButton.addActionListener(listener);
	}

}
