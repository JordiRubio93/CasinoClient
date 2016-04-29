package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import network.segment.*;

public class ServerComunication {

	private Socket sServer;
	private ObjectOutputStream objectOut;
	
	public ServerComunication() {
	}
	
	public void enviarTrama(Segment s) {
		try {
			// establim comunicacio amb el servidor
			sServer = new Socket("127.0.0.1", 6969);
			// enviem l'alumne
			objectOut = new ObjectOutputStream(sServer.getOutputStream());
			objectOut.writeObject(s);
			// tanquem la connexio
			sServer.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
