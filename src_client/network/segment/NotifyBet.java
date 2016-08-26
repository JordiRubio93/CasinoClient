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

package network.segment;

import model.Bet;
import model.struct.user.PublicUser;

/**
 * The Class NotifyBet.
 */
public class NotifyBet extends Segment {
	private static final long serialVersionUID = 1L;
	private PublicUser publicUser;
	private Bet aposta;

	/**
	 * Instantiates a new NotifyBet segment.
	 *
	 * @param publicUser
	 * @param aposta
	 */
	public NotifyBet(PublicUser publicUser, Bet aposta) {
		super();
		this.publicUser = publicUser;
		this.aposta = aposta;
	}

	/**
	 * Gets public user.
	 *
	 * @return public user
	 */
	public PublicUser getPublicUser() {
		return publicUser;
	}

	/**
	 * Sets public user.
	 *
	 * @param publicUser
	 */
	public void setPublicUser(PublicUser publicUser) {
		this.publicUser = publicUser;
	}

	/**
	 * Gets aposta.
	 *
	 * @return aposta
	 */
	public Bet getAposta() {
		return aposta;
	}

	/**
	 * Sets aposta.
	 *
	 * @param aposta
	 */
	public void setAposta(Bet aposta) {
		this.aposta = aposta;
	}
}
