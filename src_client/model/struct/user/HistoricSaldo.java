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
import java.util.Date;

/**
 * The Class HistoricSaldo.
 * (Conté les dades de l'històric de saldo per l'evolució de saldo del jugador.)
 */
public class HistoricSaldo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int joc;
	private Date dia;
	private float diners;

	/**
	 * Instantiates a new historic saldo.
	 *
	 * @param joc
	 *            the joc
	 * @param moment
	 *            the moment
	 */
	public HistoricSaldo(int joc, Date moment) {
		super();
		this.joc = joc;
		this.dia = moment;
	}

	/**
	 * Gets joc.
	 *
	 * @return the joc
	 */
	public int getJoc() {
		return joc;
	}

	/**
	 * Sets joc.
	 *
	 * @param joc
	 *            the joc
	 */
	public void setJoc(int joc) {
		this.joc = joc;
	}

	/**
	 * Gets moment.
	 *
	 * @return the moment
	 */
	public Date getMoment() {
		return dia;
	}

	/**
	 * Sets moment.
	 *
	 * @param moment
	 *            the moment
	 */
	public void setMoment(Date moment) {
		this.dia = moment;
	}

	/**
	 * Gets diners.
	 *
	 * @return the diners
	 */
	public float getDiners() {
		return diners;
	}

	/**
	 * Sets diners.
	 *
	 * @param diners
	 *            the diners
	 */
	public void setDiners(float diners) {
		this.diners = diners;
	}
}
