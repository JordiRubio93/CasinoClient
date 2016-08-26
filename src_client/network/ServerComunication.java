/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

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
 * The Class ServerComunication.
 * (Gestiona les lectures i escriptures amb el servidor.)
 */
public class ServerComunication {
	private Socket sServer;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private Manager manager;
	private ConfigurationFile cf;
	private Segment s;

	/**
	 * Instantiates a new server comunication.
	 *
	 * @param manager
	 * @param cf
	 */
	public ServerComunication(Manager manager, ConfigurationFile cf) {
		this.setManager(manager);
		this.cf = cf;
	}

	/**
	 * (Obre la connexió amb el servidor.)
	 *
	 * @param led
	 * @throws IOException
	 */
	public void establirConnexio(boolean led) throws IOException {
		if (led) {
			// System.out.println("conectant a:"+
			// cf.getIP_SDB()+":"+cf.getPORT_LED());
			sServer = new Socket(cf.getIP_SDB(), cf.getPORT_LED());

		} else
			sServer = new Socket(cf.getIP_SDB(), cf.getPORT_Client());
		objectOut = new ObjectOutputStream(sServer.getOutputStream());
		objectIn = new ObjectInputStream(sServer.getInputStream());
	}

	/**
	 * (Llegeix la trama del servidor.)
	 *
	 * @return segment
	 */
	public Segment obtenirTrama() {
		try {
			return (Segment) objectIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// System.out.println("UI!");
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * (Escriu la trama al servidor.)
	 *
	 * @param s
	 * @throws IOException
	 */
	public void enviarTrama(Segment s) throws IOException {
		objectOut.writeObject(s);
	}

	/**
	 * (Tanca la connexió amb el servidor.)
	 *
	 * @param led
	 * @throws IOException
	 */
	public void tancarConnexio(boolean led) throws IOException {
		objectOut.writeObject(new Disconnect(led));
		sServer.close();
	}

	/**
	 * Gets socket.
	 *
	 * @return socket
	 */
	public Socket getSocket() {
		return sServer;
	}

	/**
	 * (Obté la instrucció retornant el nom de la classe.)
	 *
	 * @return string
	 */
	public String obtenirInstruccio() {
		try {
			s = (Segment) objectIn.readObject();
			return (s.getClass().getSimpleName());
		} catch (ClassNotFoundException | IOException e) {

		}
		return null;
	}

	/**
	 * Gets manager.
	 *
	 * @return manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Sets manager.
	 *
	 * @param manager
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Gets objectIn.
	 *
	 * @return objectIn
	 */
	public ObjectInputStream getObjectIn() {
		return objectIn;
	}

	/**
	 * Gets objectOut.
	 *
	 * @return objectOut
	 */
	public ObjectOutputStream getObjectOut() {
		return objectOut;
	}
}
