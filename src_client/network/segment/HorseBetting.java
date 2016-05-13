package network.segment;

import model.struct.bet.HorsesBet;

public class HorseBetting extends Betting {
	private static final long serialVersionUID = 1L;
	private HorsesBet hBet;
	
	public HorseBetting(String userName, HorsesBet hBet) {
		super(userName);
		this.hBet = hBet;
	}

	public HorsesBet gethBet() {
		return hBet;
	}

	public void sethBet(HorsesBet hBet) {
		this.hBet = hBet;
	}
}
