package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.blackjack.Blackjack;
import network.segment.GameOver;

import javax.swing.JButton;

import view.MainWindow;
import view.blackjack.BlackjackView;

public class BlackjackButtonsController implements ActionListener {
	private BlackjackView mainView;
	private Blackjack blackjack;
	private boolean okBet;

	public BlackjackButtonsController() {
		this.mainView = new BlackjackView();	
		this.blackjack = new Blackjack();
		mainView.addActionListeners(this);
	}
	
	private void resetTable() {
		mainView.clearTable(blackjack.getCashAmount());
		if (blackjack.getCashAmount() == 0){
			JOptionPane.showMessageDialog(mainView, "You ran out of cash! Please, go back\n to the menu and make a deposit to\n keep playing.", "** NO MONEY **", JOptionPane.PLAIN_MESSAGE);
		}
		okBet = false;
	}

	public void startGame() {
		//Iniciem un nou joc
		blackjack.newGame();
		mainView.addCard(blackjack.giveCard(1), 1);
		mainView.addCard(blackjack.giveCard(1), 1);
		mainView.dealerCards();
		mainView.dealerCards();
		
		if (blackjack.getCardCount(1) == 21){
			JOptionPane.showMessageDialog(mainView, "You got Blackjack!!! Congratulations!!!", "** BLACKJACK **", JOptionPane.PLAIN_MESSAGE);
			blackjack.playerBlackjack();
			resetTable();
		}
	}
	
	public void actionPerformed(ActionEvent event) {
		String whichButton = ((JButton)event.getSource()).getText();
		
		if (whichButton.equals("BET") && !okBet){
			if (mainView.getBet() >= 10 && mainView.getBet() <= blackjack.getCashAmount()){
			okBet = true;
			blackjack.addBet(mainView.getBet());
			this.startGame();
			}else{
				JOptionPane.showMessageDialog(mainView, "There has been an error with the bet:\n The minimum bet is 10, or\n You have not enough funds", "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
		if (whichButton.equals("HIT")){
			if(okBet){
				if (blackjack.getCardCount(1) < 21){
					mainView.addCard(blackjack.giveCard(1), 1);
					if (blackjack.getCardCount(1) > 21){
						blackjack.playerLoses();
						JOptionPane.showMessageDialog(mainView, "You lose!", "BUSTED", JOptionPane.PLAIN_MESSAGE);
						resetTable();
					}
				}
			}else{
				JOptionPane.showMessageDialog(mainView, "You must bet something", "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
		if (whichButton.equals("STAND")){
			if(okBet){
				mainView.standAction();
				while (blackjack.getCardCount(2) < 17){
					mainView.addCard(blackjack.giveCard(2), 2);
				}
				if (blackjack.getCardCount(2) > 21){
					JOptionPane.showMessageDialog(mainView, "You win!", "DEALER BUSTS", JOptionPane.PLAIN_MESSAGE);
					blackjack.playerWins();
				}else{
					if (blackjack.getCardCount(1) > blackjack.getCardCount(2)){
						JOptionPane.showMessageDialog(mainView, "You win!", "YOU WIN", JOptionPane.PLAIN_MESSAGE);
						blackjack.playerWins();
					}else if (blackjack.getCardCount(1) == blackjack.getCardCount(2)){
						JOptionPane.showMessageDialog(mainView, "You push.", "PUSH", JOptionPane.PLAIN_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(mainView, "You lose!", "YOU LOSE", JOptionPane.PLAIN_MESSAGE);
						blackjack.playerLoses();
					}
				}
				resetTable();
			}else{
				JOptionPane.showMessageDialog(mainView, "You must bet something", "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
		if (whichButton.equals("EXIT")){
			JOptionPane.showMessageDialog(mainView, "See you soon!", "* GOOD BYE *", JOptionPane.PLAIN_MESSAGE);
			// Funcio que doni al servidor la informacio necesaria (ganancies)
			
			try {
				mainView.getManager().setPanel(new MainWindow());
				mainView.getManager().getServer().enviarTrama(new GameOver());
			} catch (IOException e) {}
		}
	}

	public BlackjackView getMainView() {
		return mainView;
	}
}
