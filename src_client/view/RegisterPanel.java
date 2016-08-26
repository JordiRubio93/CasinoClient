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
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.datepicker.DatePicker;
import com.github.lgooddatepicker.datepicker.DatePickerSettings;

import controller.Constants;
import controller.listeners.RegisterController;

/**
 * The Class RegisterPanel.
 * (Panell lateral pertanyent al registre d'usuaris.)
 */
public class RegisterPanel extends BaseJPanel {
	private final String main = "Don't have an account? Join us!";
	private final String name = "Name";
	private final String surname = "Surname";
	private final String male = "Male";
	private final String female = "Female";
	private final String age = "Birthday";
	private final String email = "e-mail";
	private final String password = "Password";
	private final String password2 = "    Repeat password     ";
	private final String join = "Join us!";
	private final String guest = "Try as guest";
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
	private JLabel mailLabel = new JLabel(email);
	private JTextField mailField = new JTextField();
	private JLabel passwordLabel = new JLabel(password);
	private JPasswordField passwordField = new JPasswordField();
	private JLabel passwordLabel2 = new JLabel(password2);
	private JPasswordField passwordField2 = new JPasswordField();
	private JButton registerButton = new JButton(join);
	private JButton guestButton = new JButton(guest);
	private JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
	private DatePicker datePicker;

	private RegisterController controller;

	/**
	 * Instantiates a new register panel.
	 */
	public RegisterPanel() {
		controller = new RegisterController(this);
		initElements();
	}

	protected void initElements() {
		setLayout(new BorderLayout());
		Color back = new Color(0, 0, 0, 80);

		Dimension preferredSize = new Dimension((int) (width * 0.184), (int) (height * 0.02));
		setBackground(back);

		// Crea constraint
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.setOpaque(false);

		// Dona valors al main label
		mainLabel.setFont(Constants.boldFont);
		mainLabel.setPreferredSize(new Dimension((int) (width * 0.184), (int) (height * 0.1)));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBackground(Color.BLACK);
		mainLabel.setForeground(Constants.coolBlue);
		add(mainLabel, BorderLayout.NORTH);

		// Dona valors al name label
		nameLabel.setFont(Constants.registerFont);
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

		// Dona valors al surname label
		surnameLabel.setFont(Constants.registerFont);
		surnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		surnameLabel.setForeground(Color.WHITE);
		surnameLabel.setBackground(back);
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(surnameLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(surnameField, c);

		// Dona valors al age label
		ageLabel.setFont(Constants.registerFont);
		ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ageLabel.setForeground(Color.WHITE);
		ageLabel.setBackground(back);

		// Dona valors al date label
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		dateSettings.setColorBackgroundNavigateYearMonthButtons(Color.WHITE);
		datePicker = new DatePicker(dateSettings);
		datePicker.setBackground(Color.WHITE);
		datePicker.setPreferredSize(preferredSize);
		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(ageLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		mainPanel.add(datePicker, c);

		// Dona valors al mail label
		mailLabel.setFont(Constants.registerFont);
		mailLabel.setForeground(Color.WHITE);
		mailLabel.setBackground(back);
		mailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 4;
		mainPanel.add(mailLabel, c);
		c.gridx = 1;
		c.gridy = 4;
		mainPanel.add(mailField, c);

		// Dona valors al password label
		passwordLabel.setFont(Constants.registerFont);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBackground(back);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 5;
		mainPanel.add(passwordLabel, c);
		c.gridx = 1;
		c.gridy = 5;
		mainPanel.add(passwordField, c);

		// Dona valors al repeat password label
		passwordLabel2.setFont(Constants.registerFont);
		passwordLabel2.setForeground(Color.WHITE);
		passwordLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel2.setBackground(back);
		c.gridx = 0;
		c.gridy = 6;
		mainPanel.add(passwordLabel2, c);
		c.gridx = 1;
		c.gridy = 6;
		mainPanel.add(passwordField2, c);

		// Dona valors als botons male - female
		maleButton.setOpaque(false);
		maleButton.setHorizontalAlignment(SwingConstants.CENTER);
		maleButton.setFont(Constants.registerFont);
		maleButton.setForeground(Color.BLACK);
		bG.add(maleButton);
		buttonsPanel.add(maleButton);
		femaleButton.setOpaque(false);
		femaleButton.setHorizontalAlignment(SwingConstants.CENTER);
		femaleButton.setFont(Constants.registerFont);
		femaleButton.setForeground(Color.BLACK);
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
		registerButton.setPreferredSize(new Dimension((int) (width * 0.18), (int) (height * 0.06)));
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
		guestButton.setPreferredSize(new Dimension((int) (width * 0.18), (int) (height * 0.06)));
		c.gridx = 0;
		c.gridy = 9;
		mainPanel.add(guestButton, c);

		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Gets name.
	 *
	 * @return name
	 */
	public String getName() {
		return nameField.getText();
	}

	/**
	 * Gets surname.
	 *
	 * @return surname
	 */
	public String getSurname() {
		return surnameField.getText();
	}

	/**
	 * Gets sex.
	 *
	 * @return sex
	 */
	public Boolean getSex() {
		return maleButton.isSelected();
	}

	/**
	 * Checks if button 'female' is on.
	 *
	 * @return boolean
	 */
	public Boolean isFemaleButtonOn() {
		return femaleButton.isSelected();
	}

	/**
	 * Gets mail.
	 *
	 * @return mail
	 */
	public String getMail() {
		return mailField.getText();
	}

	/**
	 * Gets password.
	 *
	 * @return password
	 */
	public String getPassword() {
		return String.copyValueOf(passwordField.getPassword());
	}

	/**
	 * Gets password 2.
	 *
	 * @return password 2
	 */
	public String getPassword2() {
		return String.copyValueOf(passwordField2.getPassword());
	}

	/**
	 * Gets birthday.
	 *
	 * @return birthday
	 */
	public Date getBirthday() {
		Instant instant = datePicker.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	@Override
	public void registerController() {
		guestButton.addActionListener(getManager().getController());
		registerButton.addActionListener(getManager().getController());
		passwordField.addKeyListener(controller);
		passwordField2.addKeyListener(controller);
		nameField.addKeyListener(controller);
		surnameField.addKeyListener(controller);
		mailField.addKeyListener(controller);
	}

	/**
	 * Sets field background.
	 * (Coloreja el fons dels camps.)
	 *
	 * @param jtf
	 * @param wrong
	 */
	public void setFieldBackground(JTextField jtf, boolean wrong) {
		if (wrong) {
			jtf.setBackground(Constants.coolRed);
			jtf.setForeground(Color.WHITE);
		} else {
			jtf.setBackground(Color.WHITE);
			jtf.setForeground(Color.BLACK);
		}
	}

	/**
	 * Enable register button.
	 *
	 * @param b
	 */
	public void enableRegisterButton(boolean b) {
		registerButton.setEnabled(b);
	}

	/**
	 * Gets name field.
	 *
	 * @return name field
	 */
	public JTextField getNameField() {
		return nameField;
	}

	/**
	 * Gets surname field.
	 *
	 * @return surname field
	 */
	public JTextField getSurnameField() {
		return surnameField;
	}

	/**
	 * Gets mail field.
	 *
	 * @return mail field
	 */
	public JTextField getMailField() {
		return mailField;
	}

	/**
	 * Gets password field.
	 *
	 * @return password field
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
	 * Gets password field 2.
	 *
	 * @return password field 2
	 */
	public JPasswordField getPasswordField2() {
		return passwordField2;
	}
}
