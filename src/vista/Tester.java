package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import network.ServerComunication;
import network.segment.AddUser;
import network.segment.Betting;
import network.segment.Check;
import network.segment.Disconnect;
import network.segment.GameOver;
import network.segment.LoginUser;
import network.segment.NotifyBet;
import network.segment.NotifyConRoom;
import network.segment.Play;

public class Tester extends JFrame {

	private JPanel contentPane;
	private ServerComunication sc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tester frame = new Tester();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public String hashpassword(String p) {
		try {

			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(p.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return p;
	}

	public Tester() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		sc = new ServerComunication();
		JButton btnConnect = new JButton("Connect");
		contentPane.add(btnConnect, BorderLayout.NORTH);
		String[] clases = { "Agregar Usuario", "Apostar", "Comprovar", "Desconectar", "FinJuego", "login", "NotifyBet",
				"notifyconRoom", "Jugar" };
		JComboBox comboBox = new JComboBox(clases);
		contentPane.add(comboBox, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.SOUTH);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String clase = (String) cb.getSelectedItem();
				switch (clase) {
				case "Agregar Usuario":
					try {
						sc.enviarTrama(new AddUser());
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					System.out.println(clase);
					break;
				case "Apostar":
					try {
						sc.enviarTrama(new Betting());
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case "Comprovar":
					try {
						sc.enviarTrama(new Check());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					break;
				case "Desconectar":
					try {
						sc.tancarConnexio();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "FinJuego":
					try {
						sc.enviarTrama(new GameOver());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "login":
					try {
						sc.enviarTrama(new LoginUser());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Jugar":
					try {
						sc.enviarTrama(new Play());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "notifyconRoom":
					try {
						sc.enviarTrama(new NotifyConRoom());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "NotifyBet":
					try {
						sc.enviarTrama(new NotifyBet());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;

				default:
					textArea.setText("error, clase no identificada");
					break;
				}
			}
		});
		

	}
	
}
