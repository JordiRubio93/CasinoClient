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
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.BaseJPanel;
import view.Tapet;
import controller.Constants;

public class CashRankingWindow extends BaseJPanel{
	private static final long serialVersionUID = 1L;
	private JButton backButton = new JButton("Back");
	private JPanel panelTop = new JPanel(new FlowLayout());
	private MyTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private Tapet panel;
	
	private ArrayList<Object[]> data = new ArrayList<Object[]>();
	
	public CashRankingWindow(){
		super();
		initElements();
	}
	
	public CashRankingWindow(ArrayList<Object[]> data){
		this.data = data;
		initElements();
	}
	
	public JScrollPane getSPane(){
		return scrollPane;
	}
	
	@Override
	public void registerController() {
		backButton.addActionListener(getManager().getController());
		table.getSelectionModel().addListSelectionListener(getManager().getRowListener());
	}

	public void setData(ArrayList<Object[]> data){
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
		backButton.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.06)));
		
		panelTop.setBackground(Color.BLACK);
		panelTop.add(backButton, 0);

		add(panelTop, BorderLayout.NORTH);
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		
		panel = new Tapet(w, h, Constants.PATH_METAL);
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.NORTH);
		
		add(panel, BorderLayout.CENTER);
	}
	
	public void initTable(){
		model = new MyTableModel(Constants.TABLE_COLUMN_NAMES, data);
		
		table.setModel(model);
	}
	
	public void configTable (){
		table.getTableHeader().setFont(Constants.boldFont);
		table.setFont(Constants.plainFont);
		table.setRowHeight(30);		
		
		ColorRenderer renderer = new ColorRenderer();
		
		for(int i = 0; i < data.size(); i++){
			for(int j = 0; j < 3; j++){
				table.setDefaultRenderer(Object.class, renderer);
			}
		}
		
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
	}
	
	public int getSelectedData(){
		if(table.getSelectionModel().getValueIsAdjusting()) return table.getSelectedRow();
		else return -1;
	}
}
