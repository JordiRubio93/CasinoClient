package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
/**
 * 
 * <p>
 * <b> Classe: TranslucentWindow </b> <br/>
 * </p>
 * 
 * Finestra transl�cida a on es fica la pantalla de Splash
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class TranslucentWindow extends JWindow implements Runnable {

	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		setAlwaysOnTop(true);
		setBackground(new Color(0, 0, 0, 0));
		setContentPane(new TranslucentPane());
		Icon icon = new ImageIcon(controller.Constants.PATH_SPLASH);
		JLabel label = new JLabel(icon);
		add(label);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void stop() {
		dispose();
	}

	public class TranslucentPane extends JPanel {
		private static final long serialVersionUID = 1L;

		public TranslucentPane() {
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setComposite(AlphaComposite.SrcOver.derive(0.05f));
			g2d.setColor(getBackground());
			g2d.fillRect(0, 0, getWidth(), getHeight());

		}

	}
}
