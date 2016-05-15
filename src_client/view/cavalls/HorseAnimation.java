package view.cavalls;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HorseAnimation {
	private String color;
	private Image gif;
	private int index;
	private String s;
	private int flag;
	
	public HorseAnimation(String color, int flag){
		this.flag = flag;
		index = 1;
		this.color = color;
		try{
			s = "Resources/horses/" + color + String.valueOf(1) + ".png";
			File fitxer = new File(s);
			gif = ImageIO.read(fitxer);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int getFlag() {
		return flag;
	}

	public void run() {
		try {
			if(index == 4) index = 1;
			else index++;
			s = "Resources/horses/" + color + String.valueOf(index) + ".png";
			File fitxer = new File(s);
			gif = ImageIO.read(fitxer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getGif() {
		return gif;
	}
}
