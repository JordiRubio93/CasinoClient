package view;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
/**
 * 
 * <p>
 * <b> Classe: TimerThread </b> <br/>
 * </p>
 * 
 * Permet agafar el dia i la hora
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
public class TimerThread implements Runnable {
	private boolean isRunning;

	private JLabel jlTime;

	private SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public TimerThread(JLabel jlTime) {
	    this.jlTime = jlTime;
	    this.isRunning = true;
	}

    @Override
    public synchronized void run() {
    	while (isRunning) {
            Calendar currentCalendar = Calendar.getInstance();
            Date currentTime = currentCalendar.getTime();
            jlTime.setText(time.format(currentTime));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}