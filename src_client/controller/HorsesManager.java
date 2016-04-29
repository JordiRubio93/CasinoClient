package controller;

import javax.swing.SwingUtilities;


public class HorsesManager {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				HorsesIntro hIntro = new HorsesIntro();
				hIntro.executaIntro();
			}
		});
	}
}