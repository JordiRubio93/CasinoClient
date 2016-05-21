package controller.horses;

import java.util.LinkedList;

import controller.Manager;
import model.JsonExecutor;
import model.struct.horses.HorseData;
import view.cavalls.PickHorseView;

public class PickHorseController {
	private PickHorseView choose;
	private LinkedList<HorseData> hdList;
	private boolean active;
	private Manager manager;

	public PickHorseController(Manager manager) {
		this.manager = manager;
		this.hdList = JsonExecutor.getList();
		active = false;
	}

	public void executaIntro() {
		if (!active) {
			choose = new PickHorseView(hdList);
			choose.registerController(manager.getController());
			choose.setVisible(true);
		} else {
			choose.setVisible(true);
		}
		active = true;
	}
	
	public PickHorseView getWindow() {
		return choose;
	}

	public LinkedList<HorseData> getList() {
		return hdList;
	}
}
