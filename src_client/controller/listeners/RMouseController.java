package controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.cavalls.ChooseHorse;

public class RMouseController implements MouseListener{
	private ChooseHorse window;

	public RMouseController(ChooseHorse window){
		this.window = window;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//window.pintaBoto(window.getR());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//window.despintaBoto(window.getR());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
