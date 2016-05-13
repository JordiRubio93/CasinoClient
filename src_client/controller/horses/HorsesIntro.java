package controller.horses;

import java.util.LinkedList;

import controller.listeners.CentralMouseController;
import controller.listeners.HorseButtonController;
import controller.listeners.LButtonController;
import controller.listeners.LMouseController;
import controller.listeners.RButtonController;
import controller.listeners.RMouseController;
import model.struct.horses.HorseData;
import network.ServerComunication;
import view.cavalls.ChooseHorse;

public class HorsesIntro {
	private ChooseHorse choose;
	private ServerComunication sc;
	private LinkedList<HorseData> hdList;
	private HorseButtonController hb;
	private boolean active;
	
	public HorsesIntro(LinkedList<HorseData> hdList, ServerComunication sc) {
		this.hdList = hdList;
		this.sc = sc;
		active = false;
	}
	
	private void creaControladors(){
		RButtonController rb = new RButtonController(choose);
		LButtonController lb = new LButtonController(choose);
		hb = new HorseButtonController(choose, sc);
		CentralMouseController cm = new CentralMouseController(choose);
		RMouseController rm = new RMouseController(choose);
		LMouseController lm = new LMouseController(choose);
		choose.registerController(rb,lb,hb,cm,rm,lm);
	}
	
	public void executaIntro(){
		if(!active){
			choose = new ChooseHorse(hdList);
			this.creaControladors();
			
			choose.setVisible(true);
		}else{
			choose.setVisible(true);
		}
		active = true;
	}
	
	public HorseButtonController getHorseButtonController(){
		return hb;
	}
	
	public ChooseHorse getWindow(){
		return choose;
	}
}
