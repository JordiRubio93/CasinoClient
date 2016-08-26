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
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.struct.user.User;
import controller.Constants;

/**
 * The Class LoginWindow.
 * (Pantalla de benvinguda, on es troba el login i el registre.)
 */
public class LoginWindow extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private final String email = "Email:";
	private final String password = "Password:";
	private final String login = "Log in";
	private final String remember = "Remember?";
	private final String space = "     ";

	private Tapet backgroundPanel;
	private JPanel northPanel = new JPanel(new BorderLayout());
	private JPanel registerPanel = new JPanel(new GridLayout(2, 1));
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

	/**
	 * Instantiates a new login window.
	 */
	public LoginWindow() {
		initElements();
	}

	/**
	 * Inicialitza els elements del panell.
	 */
	protected void initElements() {
		setLayout(new BorderLayout());
		backgroundPanel = new Tapet(width, height, Constants.CASINO);

		registerPanel.setBackground(Color.BLACK);
		// registerPanel.add(registerButton);

		mailLabel.setFont(Constants.boldFont);
		mailLabel.setForeground(Color.WHITE);
		mailLabel.setBackground(Color.BLACK);
		mailField.setPreferredSize(new Dimension(150, 20));
		mailField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (String.copyValueOf(passwordField.getPassword()).length() > 5 && mailField.getText().length() > 0)
					loginButton.setEnabled(true);
				else
					loginButton.setEnabled(false);
			}
		});
		emailError.setPreferredSize(Constants.errorIconDimension);
		emailError.setBackground(Color.BLACK);

		passwordLabel.setFont(Constants.boldFont);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBackground(Color.BLACK);
		passwordField.setPreferredSize(new Dimension(150, 20));
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (String.copyValueOf(passwordField.getPassword()).length() > 5 && mailField.getText().length() > 0)
					loginButton.setEnabled(true);
				else
					loginButton.setEnabled(false);
			}
		});

		loginButton.setFont(Constants.boldFont);
		loginButton.putClientProperty("action", "Log in");
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Constants.coolBlue);
		loginButton.setContentAreaFilled(true);
		loginButton.setBorderPainted(false);
		loginButton.setPreferredSize(new Dimension(200, 50));

		JPanel aux = new JPanel();
		checkBox.setBackground(Color.BLACK);
		aux.add(checkBox);
		rememberPassword.setFont(Constants.boldFont);
		rememberPassword.setForeground(Color.WHITE);
		rememberPassword.setBackground(Color.BLACK);
		aux.add(rememberPassword);
		aux.setBackground(Color.BLACK);

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

		loginButton.setEnabled(false);
		loginButton.setToolTipText("All fields must be filled correctly in order to log in");
		loginPanel.add(loginButton);

		northPanel.setBackground(Constants.semiOpaqueBlack);
		northPanel.add(loginPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);

		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.add(rPanel, BorderLayout.EAST);
		add(backgroundPanel, BorderLayout.CENTER);
	}
	
	public void registerController() {
		rPanel.setManager(getManager());
		rPanel.registerController();
		loginButton.addActionListener(getManager().getController());
	}

	/**
	 * Show password error.
	 * (Mostra l'error de que la contrasenya �s incorrecta.)
	 *
	 * @param b
	 */
	public void showPasswordError(Boolean b) {
		if (b) {
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				passwordError.setIcon(new ImageIcon(img));
				passwordError.setToolTipText(
						"Error found in password, 6 characters are needed, must include upper cases, numbers and a simbol");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					passwordError.setIcon(new ImageIcon(img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			passwordError.setBackground(Color.BLACK);
			passwordError.setIcon(null);
		}
	}

	/**
	 * Show email error.
	 * (Mostra l'error de que el correu electr�nic �s incorrecte.)
	 *
	 * @param b
	 */
	public void showEmailError(Boolean b) {
		if (b) {
			BufferedImage img;
			try {
				img = ImageIO.read(new File("Resources/warning.png"));
				emailError.setIcon(new ImageIcon(img));
				emailError.setToolTipText("Error found in e-mail format");
			} catch (IOException e) {
				try {
					img = ImageIO.read(new File("Resources/default-image.jpg"));
					emailError.setIcon(new ImageIcon(img));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			emailError.setBackground(Color.BLACK);
			emailError.setIcon(null);
		}
	}

	/**
	 * Gets register panel.
	 *
	 * @return register panel
	 */
	public RegisterPanel getRegisterPanel() {
		return rPanel;
	}

	/**
	 * Gets user.
	 *
	 * @return user
	 */
	public User getUser() {
		return new User(mailField.getText(), String.copyValueOf(passwordField.getPassword()));
	}

	/**
	 * Gets remember.
	 *
	 * @return remember
	 */
	public boolean getRemember() {
		return checkBox.isSelected();
	}
}
