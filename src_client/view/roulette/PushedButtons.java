package view.roulette;

import java.awt.Color;

import model.struct.bet.RouletteBet;
import view.Dialeg;

public class PushedButtons {
	private static final Color PUSHED = new Color(255,215,0);
	private RouletteBet bet;
	
	public void despintaBoto(MyButton jBoto){
		jBoto.canviaEstat();
		jBoto.setBackground(jBoto.getColor());
	}
	
	public void pintaBoto(MyButton jBoto){
		Dialeg dialeg = new Dialeg();
		dialeg.setInputText("How much money you want to bet?");
		
		if(dialeg.getAmount() != null && (dialeg.getAmount().isEmpty() || Float.parseFloat(dialeg.getAmount()) <= 0)){
			dialeg.setWarningText("Enter a correct amount!");
			pintaBoto(jBoto);
		}else if (dialeg.getAmount() != null){
			jBoto.canviaEstat();
			jBoto.setBackground(PUSHED);
			bet = new RouletteBet(Float.parseFloat(dialeg.getAmount()));
		}
	}
	
	
}
