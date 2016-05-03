package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import network.segment.*;

public class ServerComunication {
	private Socket sServer;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	
	public ServerComunication() {
		try {
			sServer = new Socket("127.0.0.1", 6969);
			objectOut = new ObjectOutputStream(sServer.getOutputStream());
			objectIn = new ObjectInputStream(sServer.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Segment obtenirTrama() {
		try {
			return (Segment) objectIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void enviarTrama(Segment s) throws IOException {
		objectOut.writeObject(s);	
	}
	
	public synchronized void tancarConnexio() throws IOException{
		objectOut.writeObject(new Disconnect());
		sServer.close();
	}
	
	public Socket getSocket(){
		return sServer;
	}
}
