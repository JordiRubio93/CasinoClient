package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import model.struct.user.User;

public class Constants {
	public static final String LOGIN_VIEW_NAME = "LoginWindow";
	public static final String MAIN_VIEW_NAME = "MainWindow";
	public static final String GAME_VIEW_NAME = "Gameview";
	public static final String STATISTICS_VIEW_NAME = "statistics.StatisticsWindow";
	public static final String GRAPHICS_VIEW_NAME = "statistics.Graphics";
	public static final String BJ_VIEW_NAME = "blackjack.BlackjackView";
	public static final String R_VIEW_NAME = "roulette.RouletteView";
	public static final String H_VIEW_NAME = "cavalls.HorsesView";
	public static final String PROJECT_NAME = "LS Casino";
	public static final String BET_LABEL = "Bets...";
	public static final int SPLASH_TIME = 1969;
	public static final User guest = new User("guest", null, null, 100000, null, null, null, null, null);
	public static final int HEIGHT = 600;
	public static final int WIDTH = 1300;
	public static final int MIN_BET = 10;
	public static final int CARD_WIDTH = 101;
	public static final int CARD_HEIGHT = 147;
	public static final int DELAY = 300;
	public static boolean apostaFeta;
	public static final String IP = "localhost";
	public static final int PORT = 6969;
	public static final String PATH_CARRILS = "Resources/carrils.jpg";
	public static final String PATH_TAPET = "Resources/tapet.jpg";
	public static final String PATH_CASINO = "Resources/casino.jpg";
	public static final String PATH_BLACKJACK = "Resources/blackjack.jpg";
	public static final int nHorses = 12;
	public static final Color semiOpaqueBlack = new Color(0, 0, 0, 60);
	public static final Color coolOrange = new Color(215, 143, 35);
	public static final Color coolBlue = new Color(56, 192, 196);
	public static final Color coolGreen = new Color(17, 129, 58);
	public static final Color coolRed = new Color(176, 26, 26);
	public static final Color coolGray = new Color(114, 109, 109);
	public static final Color coolDarkGray = new Color(104, 99, 99);
	public static final Color coolIndigo = new Color(75, 0, 130);
	public static final Color coolDarkGreen = new Color(0,100,0);
	public static final Font boldFont = new Font("Calibri", Font.BOLD, 24);
	public static final Font plainFont = new Font("Calibri", Font.PLAIN, 18);
	public static final Dimension errorIconDimension = new Dimension (48, 48);
	public static final int GAME_HORSES = 2;
	public static final int GAME_ROULETTE = 1;
	public static final int GAME_BLACKJACK = 3;
	public static final String GIF = "Resources/ruleta.gif";
	public static final String BG = "Resources/fondoMain.jpg";
	public static final String CASINO = "Resources/casino.jpg";
	public static final String PATH_SPLASH = "Resources/splashscreen.gif";
	public static final String PATH_GIF_SPLASH = "Resources/token.gif";
	public static final String PICK_VIEW_NAME = "cavalls.PickHorseView";
}
