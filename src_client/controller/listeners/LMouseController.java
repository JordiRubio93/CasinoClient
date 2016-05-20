package controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.cavalls.ChooseHorse;

public class LMouseController implements MouseListener{
	private ChooseHorse window;

	public LMouseController(ChooseHorse window){
		this.window = window;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//window.pintaBoto(window.getL());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//window.despintaBoto(window.getL());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
