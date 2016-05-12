package network.segment;

public class Play extends Segment{

	private static final long serialVersionUID = 1L;
	private String tipus;
	
	
	public Play( String tipus) {
		this.tipus = tipus;
	
	}
	public String getTipus(){
		return tipus;
	}
	
}
