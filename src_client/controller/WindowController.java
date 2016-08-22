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
			manager.getServer().tancarConnexio(false);
			manager.getLedController().getSC().tancarConnexio(true);
		} catch (IOException e) {
			System.err.println("No he pogut tancar la connexió... hi havia?");;
		} finally {
			System.exit(0);
		}
	}
}
