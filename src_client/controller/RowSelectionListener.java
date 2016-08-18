package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.CashEvoLogic;
import model.struct.user.HistoricSaldo;
import network.segment.CashEvo;
import network.segment.Segment;
import network.segment.WhoAmI;
import view.Dialeg;
import view.cashevo.CashEvoView;
import view.statistics.CashRankingWindow;

public class RowSelectionListener implements ListSelectionListener {
	private Manager manager;
	private ArrayList<Object[]> data;
	private int row, num;

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
					manager.getServer().enviarTrama(new CashEvo(data.get(row)[0].toString(), 0));
					Segment s = manager.getServer().obtenirTrama();
					if(s.getClass().getSimpleName().equals("Check")){
						new Dialeg().setWarningText("No data.\nTry playing...");
					}else{
						LinkedList<HistoricSaldo> hist = ((CashEvo)s).getHistoric();
						manager.showPanel(Constants.CASH_EVO_VIEW_NAME);
						HistoricSaldo zero = new HistoricSaldo(0, new Date(0));
						zero.setDiners(0);
						hist.add(0, zero);
						creaGrafic(hist);
						//Ara per joc!
						for(num = 1; num <= 3; num++){
							manager.getServer().enviarTrama(new CashEvo(data.get(row)[0].toString(), num));
							s = manager.getServer().obtenirTrama();
							if(!s.getClass().getSimpleName().equals("Check")){
								hist = ((CashEvo)s).getHistoric();
								zero = new HistoricSaldo(0, new Date(0));
								zero.setDiners(0);
								hist.add(0, zero);
								creaGrafic(hist);
							}
						}
					}
				}else new Dialeg().setWarningText("Access denied.\nYou're not this user...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void creaGrafic(LinkedList<HistoricSaldo> hist){
		CashEvoLogic logic = new CashEvoLogic();
		int index = 0;
		logic.setAltura(888);
		logic.findWidth(hist.size());
		logic.findMax();
		while(index < hist.size()){
			float x = logic.getAmplada() * index - (index == 0? 0 : 15);
			float y = logic.findPixel(hist.get(index).getDiners());
			if(logic.isFlag()){
				((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).removeAllPoints(num);
				((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).addPoint(x,y,num);
				LinkedList<Float> anteriors = logic.getAnteriors();
				for(int i = anteriors.size() - 2; i >= 0; i--){
					x = logic.getAmplada() * i - (i == 0? 0 : 15);
					y = logic.findNewPosition(anteriors.get(i));
					((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).addPoint(x,y,num);
					logic.updateList(i,y);
				}
				logic.setFlag(false);
			}else{
				((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).addPoint(x,y,num);
			}
			index++;
		}
		
		((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 0);
		((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setUserName(data.get(row)[1] + " " + data.get(row)[2]);
	}
	
	public ArrayList<Object[]> getData() {
		return data;
	}

	public void setData(ArrayList<Object[]> data) {
		this.data = data;
	}
}
