package view.horses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.struct.bet.Bet;
import model.struct.bet.HorsesBet;
import model.struct.users.PublicUser;
import view.GameView;

public class HorsesView extends GameView {
	private static final long serialVersionUID = 1L;
	private GridLayout gridLayout;
	private JPanel jpPos;

	public void setCursa() {
		gridLayout = new GridLayout(12,1);
		jpPos = new JPanel(gridLayout);
		
		for(int i = 0; i < 12; i++){
			JLabel jlCarril = new JLabel("   " + String.valueOf(i+1) + "  ");
			jpPos.add(jlCarril);
			jlCarril.setHorizontalAlignment(JLabel.RIGHT);
			jlCarril.setFont(new Font("Serif", Font.BOLD, 16));
			jlCarril.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
			jlCarril.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		}
		
		jpPos.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		gridLayout.setVgap(-40);
		jpTapet.add(jpPos, BorderLayout.WEST);
		
		jpTapet.setImatge(jpTapet.getWidth(), jpTapet.getHeight(), "Resources/carrils.png");
	}
	
	public static void main(String[] args) {
		
		LinkedList<PublicUser> lu = new LinkedList<PublicUser>();
		PublicUser u1 = new PublicUser("Resources/avatar.png", "David Estepa", 19, 'M', "Spain");
		
		HorsesBet hb1 = new HorsesBet(33, "Tornado");
		LinkedList<Bet> lb = new LinkedList<Bet>();
		lb.add(hb1);
		u1.setBets(lb);
		lu.add(u1);

		GameView game = new HorsesView();
		game.ompleLlista(lu);
		game.actualitzaTemps();
		
		game.setVisible(true);
		
		((HorsesView)game).setCursa();
	}
}
