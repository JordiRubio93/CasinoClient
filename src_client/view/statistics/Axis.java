package view.statistics;

public class Axis {
	private int primaryIncrements; 
	private int secondaryIncrements;
	private int tertiaryIncrements;
	private	int maxValue;
	private int minValue;
	private String yLabel;
 
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
