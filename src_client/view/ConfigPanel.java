/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.struct.user.User;
import controller.Constants;

/**
 * The Class ConfigPanel.
 * (A part de contenir la informació necessària d'usuari en la part superior, regula les accions que pot dur a terme:
 * canviar la contrasenya, afegir monedes al seu compte, veure la seva evolució de saldo i, finalment, fer logout.)
 */
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
	private JPanel dataPanel;
	private JLabel jlName, jlCash, jlLastLogin;

	private boolean guest;

	/**
	 * Instantiates a new config panel.
	 */
	public ConfigPanel() {
		initElements();
	}

	/**
	 * (Inicialitza els elements del panell lateral.)
	 */
	protected void initElements() {
		setLayout(new GridBagLayout());
		setBackground(Constants.coolDarkGray);

		BufferedImage img1 = null;

		try {
			img1 = ImageIO.read(new File("Resources/rightArrow.png"));
		} catch (IOException e) {
			try {
				img1 = ImageIO.read(new File("Resources/default-image.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// e.printStackTrace();
		}

		dataPanel = new JPanel(new GridLayout(3, 1));
		jlName = new JLabel();
		jlCash = new JLabel();
		jlLastLogin = new JLabel();
		dataPanel.add(jlName);
		dataPanel.add(jlCash);
		dataPanel.add(jlLastLogin);
		jlName.setForeground(new Color(245, 245, 245));
		jlCash.setForeground(new Color(220, 220, 220));
		jlLastLogin.setForeground(new Color(220, 220, 220));
		jlName.setFont(Constants.nameFont);
		jlCash.setFont(Constants.cashFont);
		jlLastLogin.setFont(Constants.lastLoginFont);
		jlName.setHorizontalAlignment(JLabel.CENTER);
		jlCash.setHorizontalAlignment(JLabel.CENTER);
		jlLastLogin.setHorizontalAlignment(JLabel.CENTER);
		dataPanel.setBackground(Constants.coolDarkGray);

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
		changePasswordButton.setPreferredSize(new Dimension((int) (width * 0.2), (int) (height * 0.06)));

		addMoneyButton.setFont(Constants.boldFont);
		addMoneyButton.setForeground(Color.WHITE);
		addMoneyButton.setBackground(Constants.coolGray);
		addMoneyButton.setContentAreaFilled(true);
		addMoneyButton.setBorderPainted(false);
		addMoneyButton.putClientProperty("action", "Add Money");
		addMoneyButton.setPreferredSize(new Dimension((int) (width * 0.2), (int) (height * 0.06)));

		seeEvoButton.setFont(Constants.boldFont);
		seeEvoButton.setForeground(Color.WHITE);
		seeEvoButton.setBackground(Constants.coolGray);
		seeEvoButton.setContentAreaFilled(true);
		seeEvoButton.setBorderPainted(false);
		seeEvoButton.putClientProperty("action", "User Evo");
		seeEvoButton.setPreferredSize(new Dimension((int) (width * 0.2), (int) (height * 0.06)));

		logOutButton.setFont(Constants.boldFont);
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setBackground(Constants.coolGreen);
		logOutButton.setContentAreaFilled(true);
		logOutButton.setBorderPainted(false);
		logOutButton.putClientProperty("action", "Log Out");
		logOutButton.setPreferredSize(new Dimension((int) (width * 0.2), (int) (height * 0.06)));

		// Crea constraint
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 1;
		this.add(backButtonPanel, c);
		c.weighty = 200;
		c.gridy = 1;
		c.gridx = 1;
		this.add(dataPanel, c);
		c.gridy = 2;
		c.gridx = 1;
		this.add(changePasswordButton, c);
		c.gridy = 3;
		c.gridx = 1;
		this.add(addMoneyButton, c);
		c.gridy = 4;
		c.gridx = 1;
		this.add(seeEvoButton, c);
		c.gridy = 5;
		c.gridx = 1;
		this.add(logOutButton, c);
	}

	/**
	 * (Agrega listeners.)
	 */
	public void registerController() {
		changePasswordButton.addActionListener(getManager().getController());
		addMoneyButton.addActionListener(getManager().getController());
		seeEvoButton.addActionListener(getManager().getController());
		logOutButton.addActionListener(getManager().getController());
		backButton.addActionListener(getManager().getController());
	}

	/**
	 * Sets guest.
	 *
	 * @param guest
	 */
	public void setGuest(boolean guest) {
		if (guest)
			setButtonsEnabled(false);
	}

	/**
	 * Sets buttons enabled.
	 * (Habilita o inhabilita alguns botons del panell.)
	 *
	 * @param enabled
	 */
	public void setButtonsEnabled(boolean enabled) {
		changePasswordButton.setEnabled(enabled);
		addMoneyButton.setEnabled(enabled);
		seeEvoButton.setEnabled(enabled);
	}

	/**
	 * Sets labels.
	 * (Col·loca valors a les etiquetes d'informació d'usuari: el nom, el saldo i la data de l'últim login, per assumptes de seguretat.)
	 *
	 * @param name
	 * @param cash
	 * @param lastLogin
	 */
	public void setLabels(String name, String cash, String lastLogin) {
		jlName.setText(name);
		jlCash.setText(cash + " €");
		if (!guest)
			jlLastLogin.setText("Last login - " + lastLogin);
	}

	/**
	 * Sets labels.
	 * (Col·loca valors a les etiquetes d'informació d'usuari: el nom, el saldo i la data de l'últim login, per assumptes de seguretat.)
	 *
	 * @param u
	 * @param guest
	 */
	public void setLabels(User u, boolean guest) {
		this.guest = guest;
		if (guest)
			this.setLabels("Guest", String.valueOf(u.getCash()), "");
		else
			this.setLabels(u.getName() + " " + u.getSurname(), String.valueOf(u.getCash()),
					u.getLastLogin().toString());
	}
}
