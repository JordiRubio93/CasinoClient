package controller;

import java.awt.Color;
import java.util.LinkedList;

import controller.listeners.CentralMouseController;
import controller.listeners.HorseButtonController;
import controller.listeners.LButtonController;
import controller.listeners.LMouseController;
import controller.listeners.RButtonController;
import controller.listeners.RMouseController;
import model.JsonExecutor;
import model.struct.horses.HorseData;
import view.horses.ChooseHorse;
import view.horses.HorseFrame;

public class HorsesIntro {
	private static HorsesIntro hm;
	private static ChooseHorse choose;
	private LinkedList<HorseData> hdList;
	private LinkedList<HorseFrame> hfList;
	
	public void creaCavalls(){
		if(JsonExecutor.llegeixFitxer("horses.txt")){
			hdList = JsonExecutor.getList();
			
			assignaSprites();
		}
	}
	
	private void assignaSprites(){
		hfList = new LinkedList<HorseFrame>();
		
		for(int i = 0; i < 12; i++){
			HorseFrame hf = new HorseFrame();
			
			switch(hdList.get(i).getColor()){
			case "WHITE": hf.getSprite().setForeground(new Color(1.0f, 1.0f, 1.0f, 0.5f)); break;
			}
			
			hfList.add(hf);
		}
	}
	
	public LinkedList<HorseData> getList(){
		return hdList;
	}
	
	private void creaControladors(){
		RButtonController rb = new RButtonController(choose);
		LButtonController lb = new LButtonController(choose);
		HorseButtonController hb = new HorseButtonController(choose);
		CentralMouseController cm = new CentralMouseController(choose);
		RMouseController rm = new RMouseController(choose);
		LMouseController lm = new LMouseController(choose);
		choose.registerController(rb,lb,hb,cm,rm,lm);
	}
	
	public void executaIntro(){
		hm = new HorsesIntro();
		hm.creaCavalls();
		
		choose = new ChooseHorse(hm.getList());
		hm.creaControladors();
		
		choose.setVisible(true);
	}
}
