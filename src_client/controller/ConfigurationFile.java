
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

package controller;

import java.util.regex.Pattern;

import tools.excepcions.FileException;

/**
 * The Class ConfigurationFile.
 * (Tracta amb el fitxer de configuració "config.json", que conté la direcció IP i els ports necessaris per la comunicació amb el servidor.)
 */
public class ConfigurationFile {

	// Atributs de la classe
	private String IP_SDB;
	private int PORT_Client;
	private int PORT_LED;

	/**
	 * Instantiates a new configuration file.
	 *
	 * @param iP_SDB
	 * @param pORT_Client
	 * @param pORT_LED
	 */
	public ConfigurationFile(String iP_SDB, int pORT_Client, int pORT_LED) {
		IP_SDB = iP_SDB;
		PORT_Client = pORT_Client;
		PORT_LED = pORT_LED;
	}// Tancament del metode

	/**
	 * Gets iP_SDB.
	 *
	 * @return iP_SDB
	 */
	public String getIP_SDB() {
		return IP_SDB;
	}// Tancament del metode

	/**
	 * Sets iP_SDB.
	 *
	 * @param iP_SDB
	 */
	public void setIP_SDB(String iP_SDB) {
		IP_SDB = iP_SDB;
	}// Tancament del metode

	/**
	 * Gets PORT_Client.
	 *
	 * @return PORT_Client
	 */
	public int getPORT_Client() {
		return PORT_Client;
	}// Tancament del metode

	/**
	 * Sets PORT_Client.
	 *
	 * @param PORT_Client
	 */
	public void setPORT_Client(int pORT_Client) {
		PORT_Client = pORT_Client;
	}// Tancament del metode

	/**
	 * Gets PORT_LED.
	 *
	 * @return PORT_LED
	 */
	public int getPORT_LED() {
		return PORT_LED;
	}

	/**
	 * Sets PORT_LED.
	 *
	 * @param PORT_LED
	 */
	public void setPORT_LED(int pORT_LED) {
		PORT_LED = pORT_LED;
	}

	/**
	 * Checks if IP is valid
	 *
	 * @throws FileException
	 */
	public void isValidIPV4() throws FileException {
		final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
		Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
		if (!IPV4_PATTERN.matcher(IP_SDB).matches() && !(IP_SDB.toLowerCase().equals("localhost")))
			throw new FileException("Invalid IPV4: " + IP_SDB);
	}// Tancament del metode

	/**
	 * Checks if ports are valid.
	 *
	 * @throws FileException
	 */
	public void isValidPort() throws FileException {
		if (PORT_Client < 1001 || PORT_Client > 65535) {
			throw new FileException("Invalid client port: " + PORT_Client);
		}
		if (PORT_LED < 1001 || PORT_LED > 65535) {
			throw new FileException("Invalid led port: " + PORT_LED);
		}
	}// Tancament del metode
}// Tancament de la classe
