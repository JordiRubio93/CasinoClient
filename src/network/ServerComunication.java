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
		// establim comunicacio amb el servidor
		try {
			sServer = new Socket("127.0.0.1", 6969);
			objectOut = new ObjectOutputStream(sServer.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarTrama(Segment s) throws IOException {
		objectOut.writeObject(s);	
	}
	public void tancarConnexio() throws IOException{
		objectOut.writeObject(new Disconnect());
		sServer.close();
		
	}
		
}
