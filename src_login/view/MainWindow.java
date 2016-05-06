package view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	public MainWindow(){
		setLayout(new BorderLayout());
		BufferedImage img = null;
		JLabel background = null;
		
		try {
			img = ImageIO.read(new File("resources/CasinoBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		background = new JLabel(new ImageIcon(img));
		
		add(background, BorderLayout.CENTER);
		
		setTitle("LS Casino");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
