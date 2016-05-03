package model.struct.bet;

public class HorsesBet extends Bet {
	private String horse;

	public HorsesBet(float amount){
		super(amount);
	}
	
	public HorsesBet(float amount, String horse) {
		super(amount);
		this.horse = horse;
	}
	
	public String getHorse() {
		return horse;
	}
	public void setHorse(String horse) {
		this.horse = horse;
	}
}
