package view.roulette;

import java.awt.Color;

import javax.swing.JButton;

public class MyButton extends JButton {
	private static final long serialVersionUID = 1L;
	private boolean pushed;
	private Color color;
	
	public MyButton(String s, Color color) {
		super(s);
		this.color = color;
		this.pushed = true;
	}

	public void canviaEstat(){
		if(pushed == false) pushed = true;
		else pushed = false;
	}
	
	public boolean consultaEstat(){
		return pushed;
	}

	public Color getColor() {
		return color;
	}
}
