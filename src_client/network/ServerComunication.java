package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.ConfigurationFile;
import controller.Manager;
import network.segment.Disconnect;
import network.segment.Segment;

public class ServerComunication{
	private Socket sServer;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private Manager manager;
	private ConfigurationFile cf;
	private Segment s;

	public ServerComunication(Manager manager, ConfigurationFile cf) {
		this.setManager(manager);
		this.cf = cf;
	}

	public void establirConnexio() throws IOException {
		sServer = new Socket(cf.getIP_SDB(), cf.getPORT_Client());
		objectOut = new ObjectOutputStream(sServer.getOutputStream());
		objectIn = new ObjectInputStream(sServer.getInputStream());
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

	public synchronized void tancarConnexio() throws IOException {
		objectOut.writeObject(new Disconnect());
		sServer.close();
	}

	public Socket getSocket() {
		return sServer;
	}

	public synchronized String obtenirInstruccio() {
		try {
			s = (Segment) objectIn.readObject();
			return (s.getClass().getSimpleName());
		} catch (ClassNotFoundException | IOException e) {
	
		}
		return null;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
