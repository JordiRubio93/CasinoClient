package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.GameManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel background_1;

	public MainWindow(){
		BufferedImage img = null;
		JLabel background = null;
		
		try {
			img = ImageIO.read(new File("resources/CasinoBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		
		background_1 = new JLabel(new ImageIcon(img));
		background_1.setBounds(0, 986, 1912, 0);
		
		getContentPane().add(background_1);
		
		JButton btnNewButton = new JButton("Settings");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(187, 139, 115, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Horses");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager gm = new GameManager("horses");
				gm.executaCasino();
				
			}
		});
		btnNewButton_1.setBounds(187, 265, 115, 29);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Roullete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager gm = new GameManager("ruleta");
				gm.executaCasino();
			}
		});
		btnNewButton_2.setBounds(447, 139, 115, 29);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("BlackJack");
		btnNewButton_3.setBounds(447, 265, 115, 29);
		getContentPane().add(btnNewButton_3);
		
		setTitle("LS Casino");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
