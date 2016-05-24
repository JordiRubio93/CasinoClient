package controller;

import java.util.regex.Pattern;

import tools.excepcions.FileException;

/**
 * 
 * <p>
 * <b> Classe: ConfigurationFile </b> <br/>
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class ConfigurationFile {
	
	//Atributs de la classe
	private String IP_SDB;
	private int PORT_Client;


	public ConfigurationFile(String iP_SDB, int pORT_Client) {
		IP_SDB = iP_SDB;
		PORT_Client = pORT_Client;
	}//Tancament del metode
	
	/**
     * Getter de IP_SDB.
     */
	public String getIP_SDB() {
		return IP_SDB;
	}//Tancament del metode

	/**
     * Setter de IP_SDB.
     */
	public void setIP_SDB(String iP_SDB) {
		IP_SDB = iP_SDB;
	}//Tancament del metode

	/**
     * Getter de PORT_Client.
     */
	public int getPORT_Client() {
		return PORT_Client;
	}//Tancament del metode

	/**
     * Setter de PORT_Client.
     */
	public void setPORT_Client(int pORT_Client) {
		PORT_Client = pORT_Client;
	}//Tancament del metode

	
	public void isValidIPV4() throws FileException{
		final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
		Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
		if (!IPV4_PATTERN.matcher(IP_SDB).matches() && !(IP_SDB.toLowerCase().equals("localhost")))
			throw new FileException("Invalid IPV4: " + IP_SDB);		
	}//Tancament del metode
	
	public void isValidPort() throws FileException{
		if (PORT_Client < 1001 || PORT_Client > 65535) {
	        throw new FileException("Invalid client port: " + PORT_Client);
	    }
	}//Tancament del metode
}//Tancament de la classe
