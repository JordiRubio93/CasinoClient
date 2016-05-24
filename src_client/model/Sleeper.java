package model;
/**
 * 
 * <p>
 * <b> Classe: Sleeper </b> <br/>
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class Sleeper extends Thread {
	private Object obj;
	private long time;

	public Sleeper(Object obj, long time){
		this.obj = obj;
		this.time = time;
	}
	
	public synchronized void run(){
		try {
			synchronized(obj){
				obj.wait(time);
				obj.notify();
			}
		} catch (InterruptedException e) {
			this.interrupt();
		}
	}
}
