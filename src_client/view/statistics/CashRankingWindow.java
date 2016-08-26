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

package view.statistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.BaseJPanel;
import view.Tapet;
import controller.Constants;

/**
 * The Class CashRankingWindow.
 * (Panell que mostra la taula amb el rànquing de monedes.)
 */
public class CashRankingWindow extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private JButton backButton = new JButton("Back");
	private JPanel panelTop = new JPanel();
	private MyTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private Tapet panel;

	private ArrayList<Object[]> data = new ArrayList<Object[]>();

	/**
	 * Instantiates a new cash ranking window.
	 */
	public CashRankingWindow() {
		super();
		initElements();
	}

	/**
	 * Instantiates a new cash ranking window.
	 *
	 * @param data
	 */
	public CashRankingWindow(ArrayList<Object[]> data) {
		this.data = data;
		initElements();
	}

	/**
	 * Gets scroll pane.
	 *
	 * @return scroll pane
	 */
	public JScrollPane getSPane() {
		return scrollPane;
	}

	/**
	 * Afegeix els controladors/listeners necessaris: el de botons i el de selecció de files.)
	 */
	@Override
	public void registerController() {
		backButton.addActionListener(getManager().getController());
		table.getSelectionModel().addListSelectionListener(getManager().getRowListener());
	}

	/**
	 * Sets data.
	 *
	 * @param data
	 */
	public void setData(ArrayList<Object[]> data) {
		this.data = data;
		initTable();
		configTable();
		repaint();
	}

	@Override
	protected void initElements() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) dim.getWidth();
		int h = (int) dim.getHeight();

		setLayout(new BorderLayout());

		backButton.setFont(Constants.boldFont);
		backButton.setBackground(Constants.coolGreen);
		backButton.setContentAreaFilled(true);
		backButton.setBorderPainted(false);
		backButton.putClientProperty("action", "Back Cash Evo");
		backButton.setPreferredSize(new Dimension((int) (width * 0.18), (int) (height * 0.06)));

		panelTop.setBackground(Color.BLACK);
		panelTop.add(backButton, 0);

		add(panelTop, BorderLayout.NORTH);

		table = new JTable();
		scrollPane = new JScrollPane(table);

		panel = new Tapet(w, h, Constants.PATH_WALL);
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.NORTH);

		add(panel, BorderLayout.CENTER);
	}

	/**
	 * Initializes table.
	 */
	public void initTable() {
		model = new MyTableModel(Constants.TABLE_COLUMN_NAMES, data);

		table.setModel(model);
	}

	/**
	 * Configures table.
	 */
	public void configTable() {
		table.getTableHeader().setFont(Constants.boldFont);
		table.setFont(Constants.plainFont);
		table.setRowHeight(30);

		ColorRenderer renderer = new ColorRenderer();

		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < 3; j++) {
				table.setDefaultRenderer(Object.class, renderer);
			}
		}

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
	}

	/**
	 * Gets selected data.
	 *
	 * @return selected data
	 */
	public int getSelectedData() {
		if (table.getSelectionModel().getValueIsAdjusting())
			return table.getSelectedRow();
		else
			return -1;
	}
}
