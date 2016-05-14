package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Constants {
	public static final int HEIGHT = 600;
	public static final int WIDTH = 1300;
	public static final int DELAY = 300;
	public static boolean apostaFeta;
	public static final String IP = "localhost";
	public static final int PORT = 6969;
	public static final String PATH_CARRILS = "Resources/carrils.jpg";
	public static final String PATH_TAPET = "Resources/tapet.jpg";
	public static final String PATH_CASINO = "Resources/casino.jpg";
	public static final int nHorses = 12;
	public static final long MINUT = 60000;
	public static final Color semiOpaqueBlack = new Color(0, 0, 0, 60); 
	public static final Color coolOrange = new Color(215, 143, 35);
	public static final Color coolBlue = new Color(56, 192, 196);
	public static final Color coolGreen = new Color(17, 129, 58);
	public static final Font boldFont = new Font("Calibri", Font.BOLD, 24);
	public static final Font plainFont = new Font("Calibri", Font.PLAIN, 18);
	public static final Dimension errorIconDimension = new Dimension (48, 48);
}
