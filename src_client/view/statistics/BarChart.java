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

import controller.Constants;
import model.Sleeper;
import model.Utilities;

public class BarChart extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;
    
	//offsets (padding of actual chart to its border)
    private int leftOffset = 140;
    private int topOffset = 120;
    private int bottomOffset = 100;
    private int rightOffset = 15;
 
    //height of X labels (must be significantly smaller than bottomOffset)
    private int xLabelOffset = 40; 
    //width of Y labels (must be significantly smaller than leftOffset)
    private int yLabelOffset = 40; 
 
    private String title = "TOP 5";
    
    //tick widths
    private int majorTickWidth = 10;
    private int secTickWidth = 5;
    private int minorTickWidth = 2;
    
    private int width;
    private int height;
 
    private Color backgroundColor = new Color(0,0,0,0);
 
    private Font yFont = new Font("Cambria", Font.PLAIN, 12);
    private Font xFont = new Font("Cambria", Font.BOLD, 12);
    private Font titleFont = new Font("Cambria", Font.BOLD, 30);
 
    private Font yCatFont = new Font("Cambria", Font.BOLD, 20);
    private Font xCatFont = new Font("Cambria", Font.BOLD, 20);
 
    private ArrayList<Bar> bars;
    private Axis yAxis;
    private int barWidth = 100;
    
	private int pointDistance;
	private int[] currentBarHeight;
	private int[] y;

	private Sleeper sleep;
 
    public BarChart(ArrayList<Bar> bars, Axis yAxis, String game) {
    	this.bars = bars;
        this.yAxis = yAxis;
        
        Rectangle r = Utilities.getUsableScreenBounds();
       	width = (int) r.getWidth();
        height = (int) r.getHeight() - 100;
        
        title = title + " - " + game;
        
        currentBarHeight = new int[5];
        y = new int[5];
        for(int i = 0; i < 5; i++) y[i] = topOffset + height - (topOffset + bottomOffset);
    }

    public void paintComponent(Graphics g) {
    	try {
			File fitxer = new File(Constants.BG);
			Image imatge = ImageIO.read(fitxer);
			g.drawImage(imatge, 0, 0, width, height, null);
		} catch (IOException e) {}
    	
    	Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
        g.drawRect(0, 0, width, height);
        g2d.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);
        g2d.setColor(Color.WHITE);
 
        int heightChart = height - (topOffset + bottomOffset);
        int widthChart = width - (leftOffset + rightOffset);
 
        //left
        g.drawLine(leftOffset, topOffset, leftOffset, heightChart + topOffset);
 
        //bottom
        g.drawLine(leftOffset, heightChart + topOffset, leftOffset + widthChart, heightChart + topOffset);
 
        if (this.yAxis.getPrimaryIncrements() != 0)
            drawTick(heightChart, this.yAxis.getPrimaryIncrements(), g, Color.WHITE, majorTickWidth);
        if (this.yAxis.getSecondaryIncrements() != 0)
            drawTick(heightChart, this.yAxis.getSecondaryIncrements(), g, Color.WHITE, secTickWidth);
        if (this.yAxis.getTertiaryIncrements() != 0)
            drawTick(heightChart, this.yAxis.getTertiaryIncrements(), g, Color.WHITE, minorTickWidth);
 
        drawYLabels(heightChart, this.yAxis.getPrimaryIncrements(), g, Color.WHITE);
 
        drawBars(heightChart, widthChart, g);
 
        drawLabels(heightChart, widthChart, g);
    }
 
    private void drawTick(int heightChart, int increment, Graphics g, Color c, int tickWidth) { 
        int incrementNo = yAxis.getMaxValue() / increment;
 
        double factor = ((double) heightChart / (double) yAxis.getMaxValue());
        
        double incrementInPixel = (double) (increment * factor);
 
        g.setColor(c);
 
        for (int i = 0; i < incrementNo; i++) {
            int fromTop = heightChart + topOffset - (int) (i * incrementInPixel);
            g.drawLine(leftOffset, fromTop, leftOffset + tickWidth, fromTop);
        }
    }
 
    private void drawYLabels(int heightChart, int increment, Graphics g, Color c) {
    	int incrementNo = yAxis.getMaxValue() / increment;
    	 
        double factor = ((double) heightChart / (double) yAxis.getMaxValue());
 
        int incrementInPixel = (int) (increment * factor);
 
        g.setColor(c);
        FontMetrics fm = getFontMetrics(yCatFont);
 
        for (int i = 0; i < incrementNo; i++) {
            int fromTop = heightChart + topOffset - (i * incrementInPixel);
 
            String yLabel = "" + (i * increment);
 
            int widthStr = fm.stringWidth(yLabel);
            int heightStr = fm.getHeight();
 
            g.setFont(yCatFont);
            g.drawString(yLabel, (leftOffset - yLabelOffset) + (yLabelOffset/2 - widthStr/2), fromTop + (heightStr / 2));
        }
    }
 
    private void drawBars(int heightChart, int widthChart, Graphics g) {
    	int i = 0;
        int barNumber = bars.size();
 
        pointDistance = (int) (widthChart / (barNumber + 1));
 
        for (Bar bar : bars) {
            i++;
 
            g.setColor(bar.getColor());
            g.fillRect(leftOffset + (i * pointDistance) - (barWidth / 2), y[i-1], barWidth, currentBarHeight[i-1]);
 
            //draw tick
            g.drawLine(leftOffset + (i * pointDistance),
                    topOffset + heightChart,
                    leftOffset + (i * pointDistance),
                    topOffset + heightChart + 2);
 
            FontMetrics fm = getFontMetrics(xCatFont);
            int widthStr = fm.stringWidth(bar.getName());
            int heightStr = fm.getHeight();
 
            g.setFont(xCatFont);
            g.setColor(Color.WHITE);
 
            int xPosition = leftOffset + (i * pointDistance) - (widthStr / 2);
            int yPosition = topOffset + heightChart + xLabelOffset - heightStr/2;
 
            //draw tick
            g.drawString(bar.getName(), xPosition, yPosition);
        }
    }
 
    private void drawLabels(int heightChart, int widthChart, Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
 
        AffineTransform oldTransform = g2d.getTransform();

        FontMetrics fmT = getFontMetrics(titleFont);
        int titleStringWidth = fmT.stringWidth(title);
        int titleStringHeight = fmT.getHeight();
 
        g2d.setColor(Color.WHITE);
        //draw tick
        g2d.rotate(Math.toRadians(270)); //rotates to above out of screen.

        //pull down, which is basically the left offset, topOffset, then middle it by 
        //usin chart height and using text height.
 
        g2d.setFont(yFont);
 
        //reset
        g2d.setTransform(oldTransform);
 
        //x label        
        g2d.setFont(xFont);
 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //title
        g2d.setFont(titleFont);
        int titleX = (leftOffset + rightOffset + widthChart)/2 - titleStringWidth/2;
        int titleY = topOffset/2 + titleStringHeight/2;
 
        g2d.drawString(title, titleX, titleY);
    }
    
    public void redrawBars(){
    	int[] scaledBarHeight = new int[5];
    	int heightChart = height - (topOffset + bottomOffset);
    	sleep = new Sleeper(this, 50);
    	
    	for (int i = 0; i < bars.size() && i < 5; i++) {
    		Bar bar = bars.get(i);
    		
            double factor = ((double) heightChart / (double) yAxis.getMaxValue());
            
            scaledBarHeight[i] = (int) (bar.getValue() * factor);
    		
            int amount = 10;
            
            currentBarHeight[i] = 0;
    		while(currentBarHeight[i] < scaledBarHeight[i] && !sleep.isInterrupted()){
				y[i] -= amount;
				currentBarHeight[i] += amount;
				
				sleep.run();
				revalidate();
				repaint();
			}
    	}
    }

	@Override
	public synchronized void run() {
		redrawBars();
	}
	
	public synchronized void stop(){
		bars.clear();
		bars = null;
		sleep.interrupt();
	}
}
