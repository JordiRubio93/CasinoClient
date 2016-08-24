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
		if(event.getSource().equals(manager.getView())){
			try {
				manager.getServer().tancarConnexio(false);
				manager.getLedController().getSC().tancarConnexio(true);
			} catch (IOException e) {
				System.err.println("No he pogut tancar la connexió... hi havia?");;
			} finally {
				System.exit(0);
			}
		}else{
			event.getWindow().setVisible(false);
			event.getWindow().dispose();
			manager.getView().setEnabled(true);
			manager.getView().setVisible(true);
		}
	}
}
