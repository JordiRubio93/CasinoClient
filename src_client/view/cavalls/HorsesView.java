package view.cavalls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Constants;
import model.Calcul;
import model.struct.horses.HorseData;
import view.Dialeg;
import view.GameView;

/**
 * Classe que hereta de GameView, com RouletteView
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Val√©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubi√≥ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaci√≥ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */

public class HorsesView extends GameView {
	
	private static final long serialVersionUID = 1L;
	private Stadium jpStadium; //Hereta de Tapet
	private GridLayout gridLayout;
	private JPanel jpCarrils;
	private LinkedList<HorseAnimation> list; //Animacions per l'sprite
	private Point[] coord; //Coordenades xy segons el cavall corresponent
	private PickHorseView phv; //Vista per escollir el cavall pel qual apostes
	
	/**
	 * Constructor
	 * @param phv: Vista per escollir el cavall pel qual apostes
	 */
	public HorsesView(PickHorseView phv){
		initElements();
		createDaemonTime(); //Crea el control de temps per la cursa
		this.phv = phv;
	}

	/**
	 * Inicialitza la vista, incloent GameView (super)
	 */
	protected void initElements() {
		super.initElements();
		jpStadium = new Stadium(width, height, Constants.PATH_TAPET);
		jpStadium.setLayout(new BorderLayout());
		add(jpStadium, BorderLayout.CENTER);
	}

	/**
	 * Col∑loca els carrils
	 */
	public void setCursa() {
		//jpStadium.setDimensions();
		jpStadium.setImatge(Constants.PATH_CARRILS);
		gridLayout = new GridLayout(Constants.nHorses, 1);
		gridLayout.setVgap(-80);
		jpCarrils = new JPanel(gridLayout);
		for(int i = 0; i < Constants.nHorses; i++){
			JLabel jlPos = new JLabel("   " + String.valueOf(Constants.nHorses - i) + "  ");
			jlPos.setHorizontalAlignment(JLabel.LEFT);
			jlPos.setFont(new Font("Serif", Font.BOLD, 34));
			jlPos.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
			jpCarrils.add(jlPos);
		}
		jpCarrils.setBackground(new Color(0,0,0,0));
		jpStadium.add(jpCarrils, BorderLayout.WEST);
		add(jpStadium, BorderLayout.CENTER);
		showCursa(true);
	}
	
	public void showCursa(boolean show){
		jpCarrils.setVisible(show);
	}
	
	/**
	 * Inicialitza els cavalls
	 * @param dades: llista de HorseData, que sÛn les dades lÚgiques/oficials/no-gr‡fiques/no-visuals/internes dels cavalls
	 */
	public void initHorses(LinkedList<HorseData> dades){
		list = new LinkedList<HorseAnimation>();
		coord = new Point[12];
		//A partir de les dades requerides, calcula la nova posiciÛ
		for(int i = 0; i < Constants.nHorses; i++){
			list.add(new HorseAnimation(dades.get(i).getColor(), i));
			coord[i] = new Point(Calcul.calculaX(dades.get(i).getSegons(), true), Calcul.calculaY(i));
		}
		jpStadium.setList(list);
		jpStadium.setCoordList(coord);
		jpStadium.setReady(true);
		jpStadium.repaint();
	}
	
	/**
	 * Fa cÛrrer els cavalls
	 * @param i: Ìndex pel cavall
	 * @param x: coordenada x pel cavall en q¸estiÛ
	 * @param y: coordenada y pel cavall en q¸estiÛ
	 */
	public void runHorses(int i, int x, int y){
		jpStadium.getList().get(i).run(); //Mou els cavalls en sÌ, les seves potes, els anima
		jpStadium.setCoord(x, y, i); //Trasllada els cavalls de posiciÛ perquË avancin
	}
	
	public void setCounter(){
		jlCount = new JLabel("...");
		jlCount.setHorizontalAlignment(JLabel.CENTER);
		jlCount.setVerticalAlignment(JLabel.CENTER);
		jlCount.setFont(new Font("Serif", Font.BOLD, 70));
		jlCount.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlCount.setBackground(Color.RED);
		jpStadium.add(jlCount, BorderLayout.CENTER);
	}
	
	public void showCounter(boolean show){
		jlCount.setVisible(show);
	}
	
	public void actualitzaCounter(int num){
		jlCount.setText(String.valueOf(num));
	}
	
	public void paintRed(boolean flag){
		jlCount.setOpaque(flag);
	}
	
	/**
	 * Mostra el JDialog amb el guanyador
	 * @param winner
	 */
	public void acabaPartida(String winner){
		new Dialeg().setWarningText(winner);
	}
	
	public void showPhv() {
		phv.clean();
		phv.setVisible(true);
	}
	
	public PickHorseView getPhv() {
		return phv;
	}

	/**
	 * Assigna els controladors pels botons
	 */
	@Override
	public void registerController(){
		//registrar el frame
		phv.setManager(getManager());
		jbBet.putClientProperty("action", "BET_H");
		jbExit.putClientProperty("action", "EXIT_H");
		super.registerController();
	}

	public void enableBet(){
		jbBet.setEnabled(true);
	}
	
	public void disableBet() {
		phv.setVisible(false);
		jbBet.setEnabled(false);
	}

}
