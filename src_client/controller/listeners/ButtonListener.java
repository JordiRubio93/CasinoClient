package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.LoginWindow;
import view.MainWindow;
import view.RegisterWindow;

public class ButtonListener implements ActionListener{
	private LoginWindow loginWindow;
	private MainWindow mainWindow;
	private RegisterWindow registerWindow;	
	
	public ButtonListener(LoginWindow view) {
		this.loginWindow = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String button = ((JButton)event.getSource()).getText();
		
		if(button.equals("Log in")){
			loginWindow.setVisible(false);
			loginWindow.dispose();
			mainWindow = new MainWindow();
			mainWindow.setVisible(true);
		}
		if(button.equals("Register")){
			registerWindow = new RegisterWindow();
			registerWindow.setVisible(true);
			registerWindow.registerController(this);
		}
		if(button.equals("Join us!")){
			registerWindow.setVisible(false);
			registerWindow.dispose();
			loginWindow.setVisible(false);
			loginWindow.dispose();
			mainWindow = new MainWindow();
			mainWindow.setVisible(true);

		}
	}

}
