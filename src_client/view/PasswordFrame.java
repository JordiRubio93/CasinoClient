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
 * The Class PasswordFrame.
 * (Finestra auxiliar per a canviar de contrasenya.)
 */
public class PasswordFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel passLabel;
	private JPasswordField passField;
	private JLabel pass2Label;
	private JPasswordField pass2Field;
	private JButton goButton;
	private Manager manager;

	/**
	 * Instantiates a new password frame.
	 *
	 * @param manager
	 */
	public PasswordFrame(Manager manager) {
		this.manager = manager;
		initElements();
		registerController();
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	/**
	 * Inits elements.
	 */
	private void initElements() {
		passLabel = new JLabel("PASSWORD:           ");
		passField = new JPasswordField();
		pass2Label = new JLabel("REPEAT PASSWORD: ");
		pass2Field = new JPasswordField();
		goButton = new JButton("Change it!");

		setLayout(new GridLayout(3, 1));
		setBackground(Color.BLACK);
		setSize(new Dimension(700, 150));
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
				if (String.copyValueOf(passField.getPassword()).length() > 5
						&& String.copyValueOf(pass2Field.getPassword()).length() > 5
						&& String.copyValueOf(passField.getPassword())
								.equals(String.copyValueOf(pass2Field.getPassword())))
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

	/**
	 * Gets password.
	 *
	 * @return password
	 */
	public String getPassword() {
		return new String(passField.getPassword());
	}

	/**
	 * Gets password 2.
	 *
	 * @return password 2
	 */
	public String getPassword2() {
		return new String(pass2Field.getPassword());
	}

	/**
	 * Register controller.
	 * (Afegeix els controladors de botons i de finestra.)
	 */
	public void registerController() {
		goButton.addActionListener(manager.getController());
		this.addWindowListener(manager.getWindow());
	}
}