package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.listeners.ButtonListener;

public class LoginWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel backgroundPanel = new JPanel(new BorderLayout());
	private JPanel northPanel = new JPanel(new BorderLayout());
	private JLabel southLabel;
	private JPanel registerPanel = new JPanel(new GridLayout(2,1));
	private JLabel registerLabel = new JLabel("    Don't have an account?");
	private JButton registerButton = new JButton("Register");
	private JPanel loginPanel = new JPanel(new BorderLayout());
	private JLabel mailLabel = new JLabel("User:");
	private JTextField mailField = new JTextField();
	private JLabel passwordLabel = new JLabel("Password:");
	private JPasswordField passwordField = new JPasswordField();
	private JCheckBox checkBox = new JCheckBox();
	private JButton loginButton = new JButton("Log in");
	private JLabel rememberPassword = new JLabel("Remember?");
	private JPanel leftLoginPanel = new JPanel();
	private JPanel rightLoginPanel = new JPanel(new BorderLayout());

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
		
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBackground(Color.BLACK);
		
		passwordField.setPreferredSize(new Dimension(150, 20));
		
		loginButton.setFont(new Font("Calibri", Font.BOLD, 24));
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
		leftLoginPanel.add(spaceLabel);
		leftLoginPanel.add(passwordLabel);
		leftLoginPanel.add(passwordField);
		leftLoginPanel.add(spaceLabel2);
		leftLoginPanel.add(aux);

		rightLoginPanel.setBackground(Color.BLACK);
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

		setTitle("LS Casino");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void registerController(ButtonListener listener){
		registerButton.addActionListener(listener);
		loginButton.addActionListener(listener);
	}
	
}