package view.blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Constants;
import model.Utilities;
import view.BaseJPanel;
import view.Tapet;

public class BlackjackView extends BaseJPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnHit, btnStand, btnExit, btnBet;
	private JLabel jlbCash, jlbBet;
	private JPanel menu, display, central, p1, p2, p3, p4;
	private Tapet background;
	private JTextField jtfCash, jtfBet;
	private ImageIcon cardImg;
	private JLabel jlbCard;
	
	public BlackjackView() {
		initElements();
	}
	
	protected void initElements(){
		Rectangle rectangle = Utilities.getUsableScreenBounds();
		int width = (int) rectangle.getWidth();
		int height = (int) rectangle.getHeight();
		background = new Tapet(width, height, Constants.PATH_BLACKJACK);
		background.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());
		
		menu = new JPanel(new GridLayout(1, 5));
		
		display = new JPanel(new BorderLayout());
		
		p3 = new JPanel(new GridLayout());
		jlbCash = new JLabel("CASH: ");
		jtfCash = new JTextField();
		jtfCash.setHorizontalAlignment(JTextField.CENTER);
		jtfCash.setEditable(false);
		p3.add(jlbCash);
		p3.add(jtfCash);
		display.add(p3,BorderLayout.NORTH);
		p4 = new JPanel(new GridLayout());
		jlbBet = new JLabel("BET: ");
		jtfBet = new JTextField();
		jtfBet.setHorizontalAlignment(JTextField.CENTER);
		p4.add(jlbBet);
		p4.add(jtfBet);
		display.add(p4, BorderLayout.SOUTH);
		menu.add(display);
		background.add(menu, BorderLayout.SOUTH);
		
		btnBet = new JButton ("BET");
		btnBet.setBackground(new Color(153, 204, 255));
		btnBet.setOpaque(true);
		btnBet.setBorderPainted(false);
		btnBet.putClientProperty("action", "BET_BJ");
		
		menu.add(btnBet);
		btnHit = new JButton ("HIT");
		btnHit.setBackground(new Color(190, 0, 0));
		btnHit.setOpaque(true);
		btnHit.setBorderPainted(false);
		btnHit.putClientProperty("action", "HIT_BJ");
		
		menu.add(btnHit);
		btnStand = new JButton ("STAND");
		btnStand.setBackground(new Color(233, 227, 51));
		btnStand.setOpaque(true);
		btnStand.setBorderPainted(false);
		btnStand.putClientProperty("action", "STAND_BJ");
		
		menu.add(btnStand);
		btnExit = new JButton ("EXIT");
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setOpaque(true);
		btnExit.setBorderPainted(false);
		btnExit.putClientProperty("action", "EXIT_GAME");
		menu.add(btnExit);
		
		central = new JPanel(new BorderLayout());
		central.setOpaque(false);
		
		p1 = new JPanel();
		((FlowLayout) p1.getLayout()).setAlignment(FlowLayout.CENTER);
		p1.setOpaque(false);
		p2 = new JPanel();
		((FlowLayout)p2.getLayout()).setAlignment(FlowLayout.CENTER);
		p2.setOpaque(false);
		central.add(p1, BorderLayout.NORTH);
		central.add(p2, BorderLayout.SOUTH);
		background.add(central, BorderLayout.CENTER);
		
		this.add(background, BorderLayout.CENTER);
		central.setVisible(true);
	}
	
	public float getBet() {
		if(jtfBet.getText().isEmpty()) return 0;
		else return Float.valueOf(jtfBet.getText());
	}
	
	public void addCard(int idCard, int destination) {
		cardImg = new ImageIcon("Resources/cards/"+ String.valueOf(idCard) +".png");
		jlbCard = new JLabel(cardImg);
		if (destination == 1){
			p2.revalidate();
			p2.add(jlbCard);
		}else{
			p1.revalidate();
			p1.add(jlbCard);
		}
		setVisible(true);
	}
	
	public void clearTable(double cash) {
		p1.removeAll();
		p1.revalidate();
		p1.repaint();
		p2.removeAll();
		p2.revalidate();
		p2.repaint();
		jtfCash.setText(Double.toString(cash));
	}
	
	public void standAction() {
		p1.removeAll();
		p1.revalidate();
		p1.repaint();
	}
	
	public void dealerCards() {
		cardImg = new ImageIcon("Resources/cardback.png");
		if(cardImg != null) jlbCard = new JLabel(cardImg);
		
		p1.add(jlbCard);
		setVisible(true);
	}

	public void registerController(){
		btnBet.addActionListener(getManager().getController());
		btnHit.addActionListener(getManager().getController());
		btnStand.addActionListener(getManager().getController());
		btnExit.addActionListener(getManager().getController());		
	}
}
