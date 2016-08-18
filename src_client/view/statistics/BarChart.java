package view.statistics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Sleeper;
import model.Utilities;
import controller.Constants;

/**
 * @version 1.0 19/05/2016
 * @author Pol ValÃ©s - ls30599@salleurl.edu <br/>
 *         Diego Bellino - ls30741@salleurl.edu <br/>
 *         Enric Marin - ls31308@salleurl.edu <br/>
 *         Jordi RubiÃ³ - ls31289@salleurl.edu <br/>
 *         David Estepa - ls30622@salleurl.edu <br/>
 *         Disseny i programaciÃ³ orientats a objectes. <br/>
 *         La Salle - Universitat Ramon Llull. <br/>
 */

public class BarChart extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	// offsets (dels eixos a les vores de la pantalla)
	private int leftOffset = 140;
	private int topOffset = 100;
	private int bottomOffset = 100;
	private int rightOffset = 140;

	// alçada de les labels de les X
	private int xLabelOffset = 40;
	// amplada de les labels de les Y
	private int yLabelOffset = 100;

	private String title = "TOP 5";

	// amplada de les línies
	private int majorTickWidth = 10;

	private int width;
	private int height;

	private Color backgroundColor = new Color(0, 0, 0, 0);

	// Fonts/tipografies
	private Font yFont = new Font("Cambria", Font.PLAIN, 12);
	private Font xFont = new Font("Cambria", Font.BOLD, 12);
	private Font titleFont = new Font("Cambria", Font.BOLD, 30);
	private Font yCatFont = new Font("Cambria", Font.BOLD, 20);
	private Font xCatFont = new Font("Cambria", Font.BOLD, 20);

	// Referències a altres classes
	private ArrayList<Bar> bars;
	private Axis yAxis;
	private int barWidth = 100;

	private int pointDistance; // Variable auxiliar
	private int[] currentBarHeight; // Valors de l'alçada en píxels de les
									// barres
	private int[] y; // Valors y reals de les barres

	// Per l'animació de quan creixen
	private Sleeper sleep;

	/**
	 * Constructor
	 * 
	 * @param bars
	 * @param yAxis
	 * @param game
	 */
	public BarChart(ArrayList<Bar> bars, Axis yAxis, String game) {
		this.bars = bars;
		this.yAxis = yAxis;

		// Agafem la mida usable de la pantalla (sense barres d'Inicio ni altres
		// elements foranis al programa)
		Rectangle r = Utilities.getUsableScreenBounds();
		width = (int) r.getWidth();
		height = (int) r.getHeight() - 100;


		title = title + " - " + game;

		currentBarHeight = new int[5];
		y = new int[5];
		for (int i = 0; i < 5; i++)
			y[i] = topOffset + height - (topOffset + bottomOffset);
	}

	public void paintComponent(Graphics g) {
		try {
			File fitxer = new File(Constants.BG);
			Image imatge = ImageIO.read(fitxer);
			g.drawImage(imatge, 0, 0, width, height, null);
		} catch (IOException e) {
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawRect(0, 0, width, height);
		g2d.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		g2d.setColor(Color.WHITE);

		int heightChart = height - (topOffset + bottomOffset);
		int widthChart = width - (leftOffset + rightOffset);

		// esquerra (eix Y)
		g.drawLine(leftOffset, topOffset, leftOffset, heightChart + topOffset);

		// inferior (eix X)
		g.drawLine(leftOffset, heightChart + topOffset, leftOffset + widthChart, heightChart + topOffset);

		if (this.yAxis.getPrimaryIncrements() != 0)
			drawTick(heightChart, 100, g, Color.WHITE, majorTickWidth);		
		//Dibuixa allò corresponent
		
		drawYLabels(heightChart, this.yAxis.getPrimaryIncrements(), g, Color.WHITE);

		drawBars(heightChart, widthChart, g);

		drawLabels(heightChart, widthChart, g);
	}

	/**
	 * Dibuixa les "ralletes" per l'eix de les Y's
	 * @param heightChart
	 * @param increment
	 * @param g
	 * @param c
	 * @param tickWidth
	 */
	
	private void drawTick(int heightChart, int increment, Graphics g, Color c, int tickWidth) {
		int incrementNo = 11;

		double factor = (heightChart - 50) / highestValue(bars);
		
		int incrementInPixel = (int) ((highestValue(bars)/10) * factor);

		g.setColor(c);

		for (int i = 0; i < incrementNo; i++) {
			int fromTop = heightChart + topOffset - (int) (i * incrementInPixel);
			g.drawLine(leftOffset, fromTop, leftOffset + tickWidth, fromTop);
		}
	}

	/**
	 * Dibuixa els valors que apareixen en l'eix Y
	 * @param heightChart
	 * @param increment
	 * @param g
	 * @param c
	 */
	private void drawYLabels(int heightChart, int increment, Graphics g, Color c) {
		//Sempre sortiran 10 labels
		int incrementNo = 11;

		double factor = (heightChart - 50) / highestValue(bars);
		

		int incrementInPixel = (int) ((highestValue(bars)/10) * factor);

		g.setColor(c);
		FontMetrics fm = getFontMetrics(yCatFont);

		for (int i = 0; i < incrementNo; i++) {
			int fromTop = heightChart + topOffset - (i * incrementInPixel);

			String yLabel = "" + (i * highestValue(bars)/10);

			int widthStr = fm.stringWidth(yLabel);
			int heightStr = fm.getHeight();

			g.setFont(yCatFont);
			g.drawString(yLabel, (leftOffset - yLabelOffset) + (yLabelOffset / 2 - widthStr / 2),
					fromTop + (heightStr / 2));
		}
	}
	
	/**
	 * Troba el valor màxim de l'array bars
	 * @param bars
	 * @return el màxim valor de l'array bars
	 */
	private double highestValue(ArrayList<Bar> bars){
		double max = 0;
		for(int i = 0; i < bars.size(); i++){
			if(bars.get(i).getValue() > max){
				max = bars.get(i).getValue();
			}
		}
		return max;
	}

	/**
	 * Dibuixa les barres. En realitat les inicialitza, ja que les dibuixa amb alçada 0, perquè després creixin
	 * @param heightChart
	 * @param widthChart
	 * @param g
	 */
	private void drawBars(int heightChart, int widthChart, Graphics g) {
		int i = 0;
		int barNumber = bars.size();

		pointDistance = (int) (widthChart / (barNumber + 1));

		for (Bar bar : bars) {
			i++;

			g.setColor(bar.getColor());
			g.fillRect(leftOffset + (i * pointDistance) - (barWidth / 2), y[i - 1], barWidth, currentBarHeight[i - 1]);

			// draw tick
			g.drawLine(leftOffset + (i * pointDistance), topOffset + heightChart, leftOffset + (i * pointDistance),
					topOffset + heightChart + 2);

			FontMetrics fm = getFontMetrics(xCatFont);
			int widthStr = fm.stringWidth(bar.getName());
			int heightStr = fm.getHeight();

			g.setFont(xCatFont);
			g.setColor(Color.WHITE);

			int xPosition = leftOffset + (i * pointDistance) - (widthStr / 2);
			int yPosition = topOffset + heightChart + xLabelOffset - heightStr / 2;

			g.drawString(bar.getName(), xPosition, yPosition);
		}
	}

	private void drawLabels(int heightChart, int widthChart, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform oldTransform = g2d.getTransform();

		FontMetrics fmT = getFontMetrics(titleFont);
		int titleStringWidth = fmT.stringWidth(title);
		int titleStringHeight = fmT.getHeight();

		g2d.setColor(Color.WHITE);
		g2d.rotate(Math.toRadians(270));

		g2d.setFont(yFont);

		// reseteja
		g2d.setTransform(oldTransform);

		g2d.setFont(xFont);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setFont(titleFont);
		int titleX = (leftOffset + rightOffset + widthChart) / 2 - titleStringWidth / 2;
		int titleY = topOffset / 2 + titleStringHeight / 2;

		g2d.drawString(title, titleX, titleY);
	}

	/**
	 * Dibuixa les barres animades creixent
	 */
	public void redrawBars() {
		int[] scaledBarHeight = new int[5];
		int heightChart = (height - (topOffset + bottomOffset));
		sleep = new Sleeper(this, 50);

		//Per cada barra (5 o menys):
		for (int i = 0; i < bars.size() && i <= 5; i++) {
			Bar bar = bars.get(i);

			double factor = (heightChart - 50) / highestValue(bars);

			scaledBarHeight[i] = (int) (bar.getValue() * factor);

			int amount = 10;

			currentBarHeight[i] = 0;
			//Fa que creixi:
			while (currentBarHeight[i] < scaledBarHeight[i] && !sleep.isInterrupted()) {
				y[i] -= amount;
				currentBarHeight[i] += amount;

				sleep.run();
				revalidate();
				repaint();
			}
			repaint();
		}
	}

	/**
	 * Executa la classe com a Runnable per poder pintar les barres correctament
	 */
	@Override
	public synchronized void run() {
		redrawBars();
	}

	/**
	 * Atura el pintatge de les barres, suprimint tot allò que pugui interferir negativament
	 */
	public synchronized void stop() {
		bars.clear();
		bars = null;
		sleep.interrupt();
	}
}
