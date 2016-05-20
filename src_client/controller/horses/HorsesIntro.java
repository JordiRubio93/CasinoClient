package controller.horses;

import java.util.LinkedList;

import controller.Manager;
import model.struct.horses.HorseData;
import view.cavalls.ChooseHorse;

public class HorsesIntro {
	private ChooseHorse choose;
	private LinkedList<HorseData> hdList;
	private boolean active;
	private Manager manager;
	
	public HorsesIntro(LinkedList<HorseData> hdList, Manager manager) {
		this.hdList = hdList;
		this.manager = manager;
		active = false;
	}
	
	private void creaControladors(){
		choose.registerController(manager.getController());
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
	
	public ChooseHorse getWindow(){
		return choose;
	}
}
