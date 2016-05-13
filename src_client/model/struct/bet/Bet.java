package model.struct.bet;

import java.io.Serializable;

public class Bet implements Serializable {
    private static final long serialVersionUID = 1L;
	protected float amount;
    
	public Bet(float amount) {
		this.amount = amount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
