package controller.horses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.Constants;
import controller.Manager;
import network.segment.InitHorses;
import network.segment.NotifyConRoom;
import network.segment.Segment;
import view.cavalls.HorsesView;

public class HorsesExecutor implements Runnable {
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private boolean active;
	private Segment s;
	private Manager manager;
	private HorsesView game;
	
	public HorsesExecutor(ObjectInputStream objectIn, ObjectOutputStream objectOut, Manager manager){
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		active = true;
		this.manager = manager;
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
	}
	
	@Override
	public void run() {
		while(active){
			System.out.println("holaaa");
			try {
				if(obtenirInstruccio() instanceof NotifyConRoom){
					System.err.println("aposta");		//game.ompleLlista(listUsers);
				}else if(obtenirInstruccio() instanceof InitHorses){
					game.showCounter(false);
					game.setCursa();
					game.initHorses(((InitHorses) s).getList());
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void close(){
		active = false;
	}
	public synchronized Segment obtenirInstruccio() throws ClassNotFoundException, IOException{
		s = (Segment) objectIn.readObject();
		System.out.println(s.toString());
		return s;
	}

}
