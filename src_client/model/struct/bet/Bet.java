package model.struct.bet;

public class Bet {
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
