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

package model.struct.user;

import java.io.Serializable;

/**
 * The Class HistoricPartides.
 * (Conté les dades de l'històric de partides pel Top 5.)
 */
public class HistoricPartides implements Serializable {
	private static final long serialVersionUID = 1L;
	private PublicUser client;
	private int joc;
	private float guanys;

	/**
	 * Instantiates a new historic partides.
	 *
	 * @param client
	 * @param joc
	 * @param guanys
	 */
	public HistoricPartides(PublicUser client, int joc, float guanys) {
		super();
		this.client = client;
		this.joc = joc;
		this.guanys = guanys;
	}

	/**
	 * Gets client.
	 *
	 * @return client
	 */
	public PublicUser getClient() {
		return client;
	}

	/**
	 * Sets client.
	 *
	 * @param client
	 */
	public void setClient(PublicUser client) {
		this.client = client;
	}

	/**
	 * Gets joc.
	 *
	 * @return joc
	 */
	public int getJoc() {
		return joc;
	}

	/**
	 * Sets joc.
	 *
	 * @param joc
	 */
	public void setJoc(int joc) {
		this.joc = joc;
	}

	/**
	 * Gets guanys.
	 *
	 * @return guanys
	 */
	public float getGuanys() {
		return guanys;
	}

	/**
	 * Sets guanys.
	 *
	 * @param guanys
	 */
	public void setGuanys(float guanys) {
		this.guanys = guanys;
	}
}
