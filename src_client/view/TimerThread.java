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

package view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

/**
 * The Class TimerThread.
 */
public class TimerThread implements Runnable {
	private boolean isRunning;

	private JLabel jlTime;

	private SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/**
	 * Instantiates a new timer thread.
	 *
	 * @param jlTime
	 */
	public TimerThread(JLabel jlTime) {
		this.jlTime = jlTime;
		this.isRunning = true;
	}

	/**
	 * (Actualitza la data i l'hora.)
	 */
	@Override
	public synchronized void run() {
		while (isRunning) {
			Calendar currentCalendar = Calendar.getInstance();
			Date currentTime = currentCalendar.getTime();
			jlTime.setText(time.format(currentTime));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Sets running.
	 *
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}