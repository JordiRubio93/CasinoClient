package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import network.segment.Disconnect;
import network.segment.Segment;
import controller.ConfigurationFile;
import controller.Manager;

/**
 * 
 * <p>
 * <b> Classe: ServerComunication </b> <br/>
 * </p>
 * Classe encarregada d'establir i gestionar la comunicació amb el servidor
 * @version 1.0 19/05/2016
 * @author  Pol ValÃ©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi RubiÃ³ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaciÃ³ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
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
	/**
	 * Estableix connexió amb el servidor
	 * @throws IOException
	 */
	public void establirConnexio() throws IOException {
		sServer = new Socket(cf.getIP_SDB(), cf.getPORT_Client());
		objectOut = new ObjectOutputStream(sServer.getOutputStream());
		objectIn = new ObjectInputStream(sServer.getInputStream());
	}
	
	/**
	 * Obte objecte llegit
	 * @return objectIn.readObject()
	 */
	public synchronized Segment obtenirTrama() {
		try {
			return (Segment) objectIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * envia un objecte
	 */
	public synchronized void enviarTrama(Segment s) throws IOException {
		objectOut.writeObject(s);
	}

	/**
	 * Tanca la connexió amb el servidor
	 * @throws IOException
	 */
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

	public ObjectInputStream getObjectIn() {
		return objectIn;
	}

	public ObjectOutputStream getObjectOut() {
		return objectOut;
	}
}
