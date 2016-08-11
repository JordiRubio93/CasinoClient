/**
 * Panell de la GUI referent als gr�fics de taula
 *
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */


package view.statistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.Constants;
import view.BaseJPanel;

public class CashRankingWindow extends BaseJPanel{
	private static final long serialVersionUID = 1L;
	private JButton backButton = new JButton("Back");
	private JPanel panelTop = new JPanel(new FlowLayout());
	private MyTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	
	private ArrayList<Object[]> data = new ArrayList<Object[]>();
	
	public CashRankingWindow(){
		super();
		initElements();
	}
	
	public CashRankingWindow(ArrayList<Object[]> data){
		super();
		this.data = data;
		initElements();
	}
	
	public JScrollPane getSPane(){
		return scrollPane;
	}
	
	@Override
	public void registerController() {
		backButton.addActionListener(getManager().getController());
	}

	@Override
	protected void initElements() {		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		model = new MyTableModel(Constants.TABLE_COLUMN_NAMES, data);
		
		table = new JTable(model);

		table.setPreferredScrollableViewportSize(new Dimension((int)(width * 0.8), (int)(height * 0.9)));
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.BLACK);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.add(scrollPane);

		setLayout(new BorderLayout());
		
		backButton.setFont(Constants.boldFont);
		backButton.setBackground(Constants.coolGreen);
		backButton.setContentAreaFilled(true);
		backButton.setBorderPainted(false);
		backButton.putClientProperty("action", "Back");
		backButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		
		panelTop.setBackground(Color.BLACK);
		panelTop.add(backButton, 0);

		add(panelTop, BorderLayout.NORTH);

		add(panel, BorderLayout.CENTER);
		
	}
	
	public JTable configTable (JTable table){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		table.getTableHeader().setBackground(Color.BLACK);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(Constants.boldFont);
		table.setBackground(new Color(20, 20, 20));
		table.setForeground(Color.WHITE);
		table.setRowHeight(30);
		table.setFont(Constants.plainFont);
		table.setPreferredScrollableViewportSize(new Dimension((int)(width * 0.8), (int)(height * 0.9)));
		
		return table;
	}

}
