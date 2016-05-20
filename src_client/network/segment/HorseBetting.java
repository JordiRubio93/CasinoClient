package network.segment;

import model.struct.bet.HorsesBet;

public class HorseBetting extends Segment {
	private static final long serialVersionUID = 1L;
	private HorsesBet hBet;
	
	public HorseBetting(HorsesBet hBet) {
		this.hBet = hBet;

	}
	public HorseBetting(float amount, String horse) {
		hBet = new HorsesBet(amount,horse);
	}

	public HorsesBet gethBet() {
		return hBet;
	}

	public void sethBet(HorsesBet hBet) {
		this.hBet = hBet;
	}
}
