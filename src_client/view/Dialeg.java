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

package view;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * The Class Dialeg.
 * (Utilitzada per mostrar JDialogs.)
 */
public class Dialeg {
	private JDialog jdDialeg;
	private int result;
	private String amount;

	/**
	 * Instantiates a new Dialeg.
	 */
	public Dialeg() {
		jdDialeg = new JDialog();
		amount = new String();
	}

	/**
	 * Sets confirm text.
	 *
	 * @param cad
	 */
	public void setConfirmText(String cad) {
		result = JOptionPane.showConfirmDialog(jdDialeg, cad);
	}

	/**
	 * Sets warning text.
	 *
	 * @param cad
	 */
	public void setWarningText(String cad) {
		JOptionPane.showMessageDialog(jdDialeg, cad);
	}

	/**
	 * (Mostra amb el color guanyador de la partida de la ruleta.)
	 * 
	 * @param cadena
	 * @param color
	 */
	public void setWarningText(String cad, Color color) {
		Color op = UIManager.getColor("OptionPane.background");
		Color p = UIManager.getColor("Panel.background");

		UIManager.put("OptionPane.background", color);
		UIManager.put("Panel.background", color);

		JOptionPane.showMessageDialog(jdDialeg, cad);

		UIManager.put("OptionPane.background", op);
		UIManager.put("Panel.background", p);
	}

	/**
	 * Sets input text.
	 *
	 * @param cad
	 */
	public void setInputText(String cad) {
		amount = JOptionPane.showInputDialog(jdDialeg, cad);
	}

	/**
	 * Gets dialeg.
	 *
	 * @return dialeg
	 */
	public JDialog getDialeg() {
		return jdDialeg;
	}

	/**
	 * Gets amount, from input dialog.
	 *
	 * @return amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * Gets result, from confirm dialog.
	 *
	 * @return result
	 */
	public int getResult() {
		return result;
	}
}
