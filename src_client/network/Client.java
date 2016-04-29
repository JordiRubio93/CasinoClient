package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import excepcions.TCPException;
import network.segment.Segment;

public class Client implements Runnable{

	
	//TODO
	private Socket sClient;
	private boolean active;
	private ObjectInputStream objectIn;
	private Segment s;
	
	public Client( Socket client) {
	
		this.sClient = client;
		active = true;
	}

	public void run() {
		while (active) {
			try {
			
				switch (s.getClass().getSimpleName()) {
					case "AddUser":
				
						sClient.close();
						break;	
					case "Disconnect":
						break;
					case "Betting":
						break;
					case "Check":
						break;
					case "Play":
						break;
					case "LoginUser":
						break;
					case "GameOver":
						break;
					case "NotifyBet":
						break;
					case "NotifyConRoom":
						break;
					default:
						sClient.close();
						throw new TCPException("Error de connexió");	
				}
			} catch (IOException e) { 
				e.printStackTrace();
			}  catch (TCPException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		active = false;
	}
}
