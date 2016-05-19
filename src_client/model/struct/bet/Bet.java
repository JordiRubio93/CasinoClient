package model.struct.bet;

import java.io.Serializable;

public class Bet implements Serializable {
    private static final long serialVersionUID = 1L;
	protected double amount;
    
	public Bet(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
