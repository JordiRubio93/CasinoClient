package view.statistics;


/**
 * Pels eixos d'abscisses i ordenades (bàsicament Y)
 * 
 * @version 1.0 19/05/2016
 * @author  Pol ValÃ©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi RubiÃ³ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaciÃ³ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */

public class Axis {
	private int primaryIncrements; //Separació entre guionets 1
	private int secondaryIncrements; //Separació entre guionets 2
	private int tertiaryIncrements; //Separació entre guionets 3
	private	int maxValue; //Valor màxim per l'alçada de l'eix de les Y
	private int minValue; //Generalment serà 0
	private String yLabel; //Nom de l'eix
 
	public Axis(String name) {
		this(100, 0, 50, 10, 5, name);
	}
 
	public Axis(int primaryIncrements, int secondaryIncrements, int tertiaryIncrements, String name) {
        this(100, 0, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }
	
	public Axis(Integer maxValue, Integer minValue, int primaryIncrements, int secondaryIncrements, int tertiaryIncrements, String name) {
        this.maxValue = maxValue; 
        this.minValue = minValue;
        this.yLabel = name;
 
        if (primaryIncrements != 0) this.primaryIncrements = primaryIncrements; 
        if (secondaryIncrements != 0) this.secondaryIncrements = secondaryIncrements;
        if (tertiaryIncrements != 0) this.tertiaryIncrements = tertiaryIncrements;
    }

	public int getPrimaryIncrements() {
		return primaryIncrements;
	}

	public void setPrimaryIncrements(int primaryIncrements) {
		this.primaryIncrements = primaryIncrements;
	}

	public int getSecondaryIncrements() {
		return secondaryIncrements;
	}

	public void setSecondaryIncrements(int secondaryIncrements) {
		this.secondaryIncrements = secondaryIncrements;
	}

	public int getTertiaryIncrements() {
		return tertiaryIncrements;
	}

	public void setTertiaryIncrements(int tertiaryIncrements) {
		this.tertiaryIncrements = tertiaryIncrements;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public String getyLabel() {
		return yLabel;
	}

	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}
}
