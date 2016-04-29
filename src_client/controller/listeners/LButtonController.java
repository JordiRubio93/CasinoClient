package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.horses.ChooseHorse;

public class LButtonController implements ActionListener{
	private ChooseHorse window;

	public LButtonController(ChooseHorse window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.passaEsquerra();
	}
}
