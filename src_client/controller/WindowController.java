package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class WindowController extends WindowAdapter {
	private Manager manager;

	public WindowController(Manager manager){
		this.manager = manager;
	}
	
	@Override
	public void windowClosing(WindowEvent event){
		try {
			manager.getLedController().getSC().tancarConnexio(true);
			manager.getServer().tancarConnexio(false);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
