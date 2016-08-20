package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;

import model.CashEvoLogic;
import model.struct.user.HistoricSaldo;
import network.segment.CashEvo;
import network.segment.Segment;
import view.Dialeg;
import view.cashevo.CashEvoView;

public class CashEvoExecutor {
	private Manager manager;
	private int num;
	
	public void executaGrafic(Manager manager, String email, String nom, String cognom) throws IOException {
		this.manager = manager;
		manager.getServer().enviarTrama(new CashEvo(email, 0));
		Segment s = manager.getServer().obtenirTrama();
		if(s.getClass().getSimpleName().equals("Check")){
			new Dialeg().setWarningText("No data.\nTry playing...");
		}else{
			manager.showPanel(Constants.CASH_EVO_VIEW_NAME);
			LinkedList<HistoricSaldo> hist = ((CashEvo)s).getHistoric();
			HistoricSaldo zero = new HistoricSaldo(0, new Date(0));
			zero.setDiners(0);
			hist.add(0, zero);
			creaGrafic(hist, email, nom, cognom);
			//Ara per joc!
			for(num = 1; num <= 3; num++){
				manager.getServer().enviarTrama(new CashEvo(email, num));
				s = manager.getServer().obtenirTrama();
				if(!s.getClass().getSimpleName().equals("Check")){
					hist = ((CashEvo)s).getHistoric();
					zero = new HistoricSaldo(0, new Date(0));
					zero.setDiners(0);
					hist.add(0, zero);
					creaGrafic(hist, email, nom, cognom);
				}
			}
		}
	}
	
	private void creaGrafic(LinkedList<HistoricSaldo> hist, String email, String nom, String cognom){
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
				((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).addPoint(x,y,false,num);
				LinkedList<Float> anteriors = logic.getAnteriors();
				for(int i = anteriors.size() - 2; i >= 0; i--){
					x = logic.getAmplada() * i - (i == 0? 0 : 15);
					y = logic.findNewPosition(anteriors.get(i));
					((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).addPoint(x,y,true,num);
					logic.updateList(i,y);
				}
				logic.setFlag(false);
			}else{
				((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).addPoint(x,y,false,num);
			}
			index++;
		}
		
		((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 4);
		((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setUserName(nom + " " + cognom);
		
		for(int j = 0; j < hist.size(); j++){
			String cad = hist.get(j).getMoment() + " - " + hist.get(j).getDiners() + "€";
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).addString(cad, num);
		}
	}
	
	public void setBack(boolean ranking){
		((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setBack(ranking);
	}
}
