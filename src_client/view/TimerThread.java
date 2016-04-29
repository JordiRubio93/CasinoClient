package view;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

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