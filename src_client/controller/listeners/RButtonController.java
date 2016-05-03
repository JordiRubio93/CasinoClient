package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.cavalls.ChooseHorse;

public class RButtonController implements ActionListener{
	private ChooseHorse window;

	public RButtonController(ChooseHorse window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.passaDreta();
	}
}
