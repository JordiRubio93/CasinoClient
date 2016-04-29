package view.horses;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.HorseMovement;

public class HorseFrame extends JFrame {
	private final static String frame1 = "Resources/cavall1_1.png";
	private final static String frame2 = "Resources/cavall1_2.png";
	private final static String frame3 = "Resources/cavall1_3.png";
	private final static String frame4 = "Resources/cavall1_4.png";
	private JLabel[] jlFrames;
	private JLabel jlSprite;
	private int index;
	
	public HorseFrame(){
		jlFrames = new JLabel[4];
		
		jlFrames[0] = new JLabel(new ImageIcon(frame1));
		jlFrames[1] = new JLabel(new ImageIcon(frame2));
		jlFrames[2] = new JLabel(new ImageIcon(frame3));
		jlFrames[3] = new JLabel(new ImageIcon(frame4));
		
		jlSprite = new JLabel(new ImageIcon());
		jlSprite.setIcon(jlFrames[0].getIcon());
		
	}
	
	public void run(){
		if(index == 3) index = 0;
		else index++;
		
		jlSprite.setIcon(jlFrames[index].getIcon());
	}
	
	public JLabel getSprite(){
		return jlSprite;
	}
	
	
	public static void main(String[] args) {
		HorseFrame hf = new HorseFrame();
		HorseMovement hm = new HorseMovement(hf);
		
		JPanel jPanel = new JPanel();
		jPanel.add(hf.getSprite());
		
		hf.getContentPane().add(jPanel);
		
		hf.setSize(100, 120);
		hf.setResizable(false);
		hf.setTitle("JOC");
		hf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		hf.setLocationRelativeTo(null);
		
		hf.setVisible(true);
		
		hm.run();
	}
	
}
