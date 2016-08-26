/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package model;

/**
 * The Class Sleeper.
 * (Fil per adormir i aturar el programa de forma segura.)
 */
public class Sleeper extends Thread {
	private Object obj;
	private long time;

	/**
	 * Instantiates a new sleeper.
	 *
	 * @param obj
	 * @param time
	 */
	public Sleeper(Object obj, long time) {
		this.obj = obj;
		this.time = time;
	}
	
	@Override
	public synchronized void run() {
		try {
			synchronized (obj) {
				obj.wait(time);
				obj.notify();
			}
		} catch (InterruptedException e) {
			this.interrupt();
		}
	}
}
