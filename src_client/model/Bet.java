package model;

import java.io.Serializable;

public class Bet implements Serializable {
    private static final long serialVersionUID = 1L;
	private double amount;
	private String slot;

	
	public Bet(double amount, String slot) {
		this.amount = amount;
		this.slot = slot;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	
}

