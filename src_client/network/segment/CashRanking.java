package network.segment;

import java.util.ArrayList;

public class CashRanking extends Segment{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Object[]> data;
	
	public CashRanking(ArrayList<Object[]> data){
		this.data = data;
	}

	public ArrayList<Object[]> getData(){
		return data;
	}
}
