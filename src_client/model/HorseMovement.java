package model;

import view.horses.HorseFrame;

public class HorseMovement implements Runnable {
	public final static int DELAY = 100;
	private boolean isRunning;
	private HorseFrame horse;

	public HorseMovement(HorseFrame horse) {
		super();
		this.isRunning = true;
		this.horse = horse;
	}
	
	@Override
	public synchronized void run() {
    	while (isRunning) {
            horse.run();
            
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {}
        }
    }
}
