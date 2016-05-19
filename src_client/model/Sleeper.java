package model;

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
		} catch (InterruptedException e) {}
	}
}
