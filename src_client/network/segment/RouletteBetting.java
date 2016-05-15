package network.segment;

import java.util.LinkedList;

import model.struct.bet.RouletteBet;

public class RouletteBetting extends Betting {
	private static final long serialVersionUID = 1L;
	private LinkedList<RouletteBet> bets;

	public RouletteBetting(String userName, LinkedList<RouletteBet> bets) {
		super(userName);
		this.setBets(bets);
	}

	public LinkedList<RouletteBet> getBets() {
		return bets;
	}

	public void setBets(LinkedList<RouletteBet> bets) {
		this.bets = bets;
	}
}
