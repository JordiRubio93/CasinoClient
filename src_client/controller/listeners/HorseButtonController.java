package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.Constants;
import model.struct.bet.HorsesBet;
import network.ServerComunication;
import network.segment.HorseBetting;
import view.Dialeg;
import view.cavalls.ChooseHorse;

public class HorseButtonController implements ActionListener{
	private ChooseHorse window;
	private ServerComunication sc;
	private String name;
	private HorsesBet bet;

	public HorseButtonController(ChooseHorse window, ServerComunication sc){
		this.sc = sc;
		this.window = window;
		this.bet = new HorsesBet(Float.MIN_VALUE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(window.getAmount().isEmpty() || Float.parseFloat(window.getAmount()) <= 0){
			Dialeg dialeg = new Dialeg();
			dialeg.setWarningText("You must enter a positive amount!");
		}else{
			window.obreDialeg();
			
			if(window.getDialeg().getResult() == JOptionPane.OK_OPTION){
				name = window.getHorseName();
				
				bet = new HorsesBet(Float.parseFloat(window.getAmount()), name);
				
				try {
					sc.enviarTrama(new HorseBetting("David", bet));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				window.setVisible(false);
				
				Constants.apostaFeta = true;
			}
		}
	}
}
