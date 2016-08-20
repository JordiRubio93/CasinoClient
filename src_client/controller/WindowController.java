package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import network.segment.Disconnect;

public class WindowController extends WindowAdapter {
	private Manager manager;

	public WindowController(Manager manager){
		this.manager = manager;
	}
	
	@Override
	public void windowClosing(WindowEvent event){
		try {
			manager.getServer().enviarTrama(new Disconnect());
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
