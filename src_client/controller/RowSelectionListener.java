package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.struct.user.HistoricSaldo;
import network.segment.CashEvo;
import network.segment.Segment;
import network.segment.WhoAmI;
import view.Dialeg;
import view.statistics.CashRankingWindow;

public class RowSelectionListener implements ListSelectionListener {
	private Manager manager;
	private ArrayList<Object[]> data;

	public RowSelectionListener(Manager manager){
		this.manager = manager;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		int row = ((CashRankingWindow) manager.getPanel(Constants.CASH_RANKING_VIEW_NAME)).getSelectedData();
		if(row >= 0){
			try {
				manager.getServer().enviarTrama(new WhoAmI(null));
				if(((WhoAmI)manager.getServer().obtenirTrama()).getId().equals(data.get(row)[0])){
					manager.getServer().enviarTrama(new CashEvo(data.get(row)[0].toString(), 0));
					Segment s = manager.getServer().obtenirTrama();
					if(s.getClass().getSimpleName().equals("Check")){
						new Dialeg().setWarningText("No data.\nTry playing...");
					}else{
						LinkedList<HistoricSaldo> hist = ((CashEvo)s).getHistoric();
						//...
					}
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
