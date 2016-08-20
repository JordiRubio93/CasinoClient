package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import network.segment.WhoAmI;
import view.Dialeg;
import view.statistics.CashRankingWindow;

public class RowSelectionListener implements ListSelectionListener {
	private ArrayList<Object[]> data;
	private Manager manager;
	private CashEvoExecutor ce;
	private int row;

	public RowSelectionListener(Manager manager){
		this.manager = manager;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		row = ((CashRankingWindow) manager.getPanel(Constants.CASH_RANKING_VIEW_NAME)).getSelectedData();
		if(row >= 0){
			try {
				manager.getServer().enviarTrama(new WhoAmI(null));
				if(((WhoAmI)manager.getServer().obtenirTrama()).getId().equals(data.get(row)[0])){
					ce = new CashEvoExecutor();
					ce.executaGrafic(manager, data.get(row)[0].toString(), data.get(row)[1].toString(), data.get(row)[2].toString());
					ce.setBack(true);
				}else new Dialeg().setWarningText("Access denied.\nYou're not this user...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Object[]> getData() {
		return data;
	}

	public void setData(ArrayList<Object[]> data) {
		this.data = data;
	}
}
