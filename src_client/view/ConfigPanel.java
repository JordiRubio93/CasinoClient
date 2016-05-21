package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Constants;

public class ConfigPanel extends BaseJPanel {
	private static String changePassword = "Change password";
	private static String addMoney = "Add money to account";
	private static String seeEvo = "See cash evolution";
	private static String logOut = "Log Out";
	private static final long serialVersionUID = 1L;
	
	private JButton changePasswordButton = new JButton(changePassword);
	private JButton addMoneyButton = new JButton(addMoney);
	private JButton seeEvoButton = new JButton(seeEvo);
	private JButton logOutButton = new JButton(logOut);
	private JButton backButton;
	private JPanel backButtonPanel = new JPanel();
	
	public ConfigPanel(){
		initElements();
	}
	
	protected void initElements(){	
		setLayout(new GridBagLayout());
		setBackground(Constants.semiOpaqueBlack);
		
		BufferedImage img1 = null;
		
		try {
			img1 = ImageIO.read(new File("Resources/rightArrow.png"));
		} catch (IOException e) {
			try {
				img1 = ImageIO.read(new File("Resources/default-image.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		backButton = new JButton(new ImageIcon(img1));
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.putClientProperty("action", "Minimize Panel");
		
		backButtonPanel.setLayout(new BorderLayout());
		backButtonPanel.setBackground(Color.BLACK);
		backButtonPanel.add(backButton, BorderLayout.WEST);
		
		changePasswordButton.setFont(Constants.boldFont);
		changePasswordButton.setForeground(Color.WHITE);
		changePasswordButton.setBackground(Constants.coolGray);
		changePasswordButton.setContentAreaFilled(true);
		changePasswordButton.setBorderPainted(false);
		changePasswordButton.putClientProperty("action", "Change Password");
		changePasswordButton.setPreferredSize(new Dimension((int)(width * 0.2), (int)(height * 0.06)));
		
		addMoneyButton.setFont(Constants.boldFont);
		addMoneyButton.setForeground(Color.WHITE);
		addMoneyButton.setBackground(Constants.coolGray);
		addMoneyButton.setContentAreaFilled(true);
		addMoneyButton.setBorderPainted(false);
		addMoneyButton.putClientProperty("action", "Add Money");
		addMoneyButton.setPreferredSize(new Dimension((int)(width * 0.2), (int)(height * 0.06)));
		
		seeEvoButton.setFont(Constants.boldFont);
		seeEvoButton.setForeground(Color.WHITE);
		seeEvoButton.setBackground(Constants.coolGray);
		seeEvoButton.setContentAreaFilled(true);
		seeEvoButton.setBorderPainted(false);
		seeEvoButton.putClientProperty("action", "User Evo");
		seeEvoButton.setPreferredSize(new Dimension((int)(width * 0.2), (int)(height * 0.06)));
		
		logOutButton.setFont(Constants.boldFont);
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setBackground(Constants.coolGreen);
		logOutButton.setContentAreaFilled(true);
		logOutButton.setBorderPainted(false);
		logOutButton.putClientProperty("action", "Log Out");
		logOutButton.setPreferredSize(new Dimension((int)(width * 0.2), (int)(height * 0.06)));
		
		// Crea constraint
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 1;
		this.add(backButtonPanel, c);
		c.weighty = 200;
		c.gridy = 1;
		c.gridx = 1;
		this.add(changePasswordButton, c);
		c.gridy = 2;
		c.gridx = 1;
		this.add(addMoneyButton, c);
		c.gridy = 3;
		c.gridx = 1;
		this.add(seeEvoButton, c);
		c.gridy = 4;
		c.gridx = 1;
		this.add(logOutButton, c);
	}
	
	public void registerController(){
		changePasswordButton.addActionListener(getManager().getController());
		addMoneyButton.addActionListener(getManager().getController());
		seeEvoButton.addActionListener(getManager().getController());
		logOutButton.addActionListener(getManager().getController());
		backButton.addActionListener(getManager().getController());
	}
}
