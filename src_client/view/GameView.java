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
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import model.Bet;
import model.struct.user.PublicUser;
import controller.Constants;

/**
 * The Class GameView.
 * (Classe genèrica que conforma la vista pels jocs de la ruleta i la cursa de cavalls, amb compte enrere i panell d'apostes.)
 */
public abstract class GameView extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private static final String text = "You've bet to: ";

	protected BaseJPanel gamePanel;
	protected JPanel jpFinestra;
	protected JPanel jpTemps;
	protected JLabel jlTemps;
	protected GridLayout columnLayout;
	protected JScrollPane jspList;
	protected JPanel jpDades;
	protected JPanel jpList;
	protected JPanel jpAux;
	protected TimerThread timer;
	protected boolean isRuleta;
	protected JButton jbBet;
	protected JButton jbPredict;
	protected JPanel jpOptions;
	protected JLabel jlCount;
	protected JPanel jpApostaPropia;
	protected JLabel jlApostaPropia;
	protected JPanel jpSuperior;
	protected JProgressBar jpbTime;
	private int offset;

	/**
	 * Instantiates a new game view.
	 */
	public GameView() {
		initElements();
	}
	
	protected void initElements() {
		setLayout(new BorderLayout());

		// Label temps
		jlTemps = new JLabel();
		jlTemps.setForeground(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		jlTemps.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		// panell temps
		jpTemps = new JPanel();
		jpTemps.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		jpTemps.add(jlTemps);
		jpSuperior = new JPanel(new BorderLayout());
		jpSuperior.add(jpTemps, BorderLayout.NORTH);

		// ProgressBar
		jpbTime = new JProgressBar();
		jpbTime.setMinimum(0);
		jpSuperior.add(jpbTime, BorderLayout.SOUTH);
		add(jpSuperior, BorderLayout.NORTH);

		// boton salir
		jbPredict = new JButton("Predict");
		jbPredict.setFont(Constants.plainFont);
		jbPredict.setBackground(Color.WHITE);
		jbPredict.setForeground(Constants.coolBlue);

		// boton Apostar
		jbBet = new JButton("Bet!");
		jbBet.setFont(Constants.plainFont);
		jbBet.setBackground(Color.WHITE);
		jbBet.setForeground(Constants.coolOrange);

		// barra Inferior (botones)
		jpOptions = new JPanel(new GridLayout(1, 2));
		jpOptions.add(jbPredict);
		jpOptions.add(jbBet);

		// label aposta
		jlApostaPropia = new JLabel(text);
		jpApostaPropia = new JPanel();
		jpApostaPropia.setBackground(Color.WHITE);
		jpApostaPropia.setBorder(BorderFactory.createEtchedBorder());
		jpApostaPropia.add(jlApostaPropia);

		// panell de apostes
		jpDades = new JPanel(new BorderLayout());
		columnLayout = new GridLayout();
		columnLayout.setColumns(1);
		columnLayout.setVgap(10);

		// tirar apostas cap adalt
		jpList = new JPanel(columnLayout);
		jpAux = new JPanel(new BorderLayout());
		jpAux.add(jpList, BorderLayout.NORTH);
		jspList = new JScrollPane(jpAux);
		jpDades.add(jspList, BorderLayout.CENTER);
		jpDades.add(jpOptions, BorderLayout.SOUTH);
		jpDades.add(jpApostaPropia, BorderLayout.NORTH);
		jpDades.setBorder(BorderFactory.createTitledBorder(Constants.BET_LABEL));
		add(jpDades, BorderLayout.EAST);
	}

	/**
	 * (Actualitza l'etiqueta d'aposta pròpia.)
	 *
	 * @param slot
	 */
	public void actualitzaLabelApostaPropia(String slot) {
		jlApostaPropia.setText(jlApostaPropia.getText() + slot + " ");
	}

	/**
	 * (Actualitza la barra de progrés per l'inici de partida.)
	 *
	 * @param sec
	 */
	public void actualitzaProgressBar(int sec) {
		jpbTime.setValue(sec - offset);
	}

	/**
	 * Sets actual.
	 *
	 * @param max
	 */
	public void setActual(int max) {
		this.offset = max;
		jpbTime.setMaximum(45 - max);
	}

	/**
	 * Disable bet.
	 */
	public void disableBet() {
		jbBet.setEnabled(false);
	}

	/**
	 * (Afegeix controladors/listeners.)
	 */
	public void registerController() {
		jbBet.addActionListener(getManager().getController());
		jbPredict.addActionListener(getManager().getController());
	}

	/**
	 * (Actualitza l'etiqueta que mostra la data i l'hora.)
	 *
	 * @param time
	 */
	private void actualitzaTemps(String time) {
		jlTemps.setText(time);
	}

	/**
	 * Creates daemon time.
	 */
	protected void createDaemonTime() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			@Override
			public void run() {
				try {
					actualitzaTemps(dateFormat.format(Calendar.getInstance().getTime()));
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		}, Constants.SPLASH_TIME, TimeUnit.SECONDS.toMillis(1));
	}

	/**
	 * (Afegeix una nova aposta al panell lateral d'apostes dins del joc.)
	 *
	 * @param user
	 * @param bet
	 */
	public void addAtList(PublicUser user, Bet bet) {
		columnLayout.setRows(columnLayout.getRows() + 1);

		JPanel jpCell = new JPanel(new BorderLayout());
		JPanel jpInfo = new JPanel(new GridLayout(2, 1));
		JLabel jlAvatar = null;
		if (user.getGender())
			jlAvatar = new JLabel(new ImageIcon(Constants.AVATAR_MALE));
		else
			jlAvatar = new JLabel(new ImageIcon(Constants.AVATAR_FEMALE));
		JLabel jlUser = new JLabel("   " + user.getSurname() + " - " + bet.getAmount() + " €   ");
		JLabel jlAposta = new JLabel("   " + bet.getSlot() + "   ");

		jpInfo.add(jlUser);
		jpInfo.add(jlAposta);

		jlUser.setFont(new Font("Serif", Font.BOLD, 20));
		jlAposta.setFont(new Font("Serif", Font.PLAIN, 18));

		jpCell.add(jlAvatar, BorderLayout.WEST);
		jpCell.add(jpInfo, BorderLayout.CENTER);

		jpList.add(jpCell, BorderLayout.CENTER);
	}

	/**
	 * Reset.
	 */
	public abstract void reset();
}
