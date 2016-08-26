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

package view.statistics;

/**
 * The Class Axis.
 * (Pels eixos del gràfic.)
 */
public class Axis {
	private int primaryIncrements; // Separació entre guionets 1
	private int secondaryIncrements; // Separació entre guionets 2. No s'usa
	private int tertiaryIncrements; // Separació entre guionets 3. No s'usa
	private int maxValue; // Valor màxim per l'alçada de l'eix de les Y
	private int minValue; // Generalment serà 0
	private String yLabel; // Nom de l'eix

	/**
	 * Instantiates a new axis.
	 *
	 * @param name
	 */
	public Axis(String name) {
		this(100, 0, 50, 10, 5, name);
	}

	/**
	 * Instantiates a new axis.
	 *
	 * @param primaryIncrements
	 * @param secondaryIncrements
	 * @param tertiaryIncrements
	 * @param name
	 */
	public Axis(int primaryIncrements, int secondaryIncrements, int tertiaryIncrements, String name) {
		this(100, 0, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
	}

	/**
	 * Instantiates a new axis.
	 *
	 * @param maxValue
	 * @param minValue
	 * @param primaryIncrements
	 * @param secondaryIncrements
	 * @param tertiaryIncrements
	 * @param name
	 */
	public Axis(Integer maxValue, Integer minValue, int primaryIncrements, int secondaryIncrements,
			int tertiaryIncrements, String name) {
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.yLabel = name;

		if (primaryIncrements != 0)
			this.primaryIncrements = primaryIncrements;
		if (secondaryIncrements != 0)
			this.secondaryIncrements = secondaryIncrements;
		if (tertiaryIncrements != 0)
			this.tertiaryIncrements = tertiaryIncrements;
	}

	/**
	 * Gets primary increments.
	 *
	 * @return primary increments
	 */
	public int getPrimaryIncrements() {
		return primaryIncrements;
	}

	/**
	 * Sets primary increments.
	 *
	 * @param primaryIncrements
	 */
	public void setPrimaryIncrements(int primaryIncrements) {
		this.primaryIncrements = primaryIncrements;
	}

	/**
	 * Gets secondary increments.
	 *
	 * @return secondary increments
	 */
	public int getSecondaryIncrements() {
		return secondaryIncrements;
	}

	/**
	 * Sets secondary increments.
	 *
	 * @param secondaryIncrements
	 */
	public void setSecondaryIncrements(int secondaryIncrements) {
		this.secondaryIncrements = secondaryIncrements;
	}

	/**
	 * Gets tertiary increments.
	 *
	 * @return tertiary increments
	 */
	public int getTertiaryIncrements() {
		return tertiaryIncrements;
	}

	/**
	 * Sets tertiary increments.
	 *
	 * @param tertiaryIncrements
	 */
	public void setTertiaryIncrements(int tertiaryIncrements) {
		this.tertiaryIncrements = tertiaryIncrements;
	}

	/**
	 * Gets max value.
	 *
	 * @return max value
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * Sets max value.
	 *
	 * @param maxValue
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * Gets min value.
	 *
	 * @return min value
	 */
	public int getMinValue() {
		return minValue;
	}

	/**
	 * Sets min value.
	 *
	 * @param minValue
	 */
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	/**
	 * Gets y label.
	 *
	 * @return y label
	 */
	public String getyLabel() {
		return yLabel;
	}

	/**
	 * Sets y label.
	 *
	 * @param yLabel
	 */
	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}
}
