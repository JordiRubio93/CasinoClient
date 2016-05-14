package controller;

import java.io.IOException;
import java.net.ConnectException;

import controller.listeners.MainButtonsController;
import model.struct.user.User;
import network.ServerComunication;
import network.segment.LoginUser;
import network.segment.NotifyConRoom;
import network.segment.Play;
import network.segment.Segment;
import tools.excepcions.FileException;
import view.BaseJPanel;
import view.LoginWindow;
import view.MainWindow;
import view.MainFrame;

public class Manager {
	private final String rutejson = "config.json";
	private ServerComunication server;
	private MainButtonsController controller;
	private MainFrame view;
	private GameManager gameManager;
	private ConfigurationFile cf;

	public Manager(MainFrame view) {
		this.view = view;
		try {
			controller = new MainButtonsController(this);
			view.registerController(this);
			view.setPanel(new LoginWindow());
			cf = (new FileManager()).obtenirConfiguracio(rutejson);
			server = new ServerComunication(this, cf);
			server.establirConnexio();
			gameManager = new GameManager(this);
		} catch (FileException e) {
			view.showError("Configuration file not found");
			System.exit(0);
		}  catch (ConnectException e){
			view.showError("Server not found");
			System.exit(0);
		}catch (IOException e) {
			view.showError("Error");
			System.exit(0);
		}
	}

	public void setPanel(BaseJPanel panel) {
		view.setPanel(panel);
	}
	
	public BaseJPanel getPanel() {
		return view.getPanel();
	}

	public MainButtonsController getController() {
		return controller;
	}

	public MainButtonsController getButtonListener() {
		return controller;
	}

	public ServerComunication getServer() {
		return server;
	}

	public boolean login(){
		LoginWindow p = (LoginWindow) view.getPanel();
		User u = p.getUser();
		Boolean valid = true;
		Boolean logged = false;
		//view controll of warning
		if (!gameManager.comprovaLoginMail(u.getEmail())){
			valid = false;
			p.showEmailError(true);
		}
		else p.showEmailError(false);

		if (!gameManager.comprovaLoginPW(u.getPassword())){
			valid = false;
			p.showPasswordError(true);
		}
		else p.showPasswordError(false);
		
		if (valid){
			try {
				server.enviarTrama(new LoginUser(u));
				Segment s = (Segment) server.obtenirTrama();
				switch(( s.getClass().getSimpleName()) ){
				case "NotifyConRoom":
					NotifyConRoom not = (NotifyConRoom) s;
					gameManager.setPublicUser(not.getPu());
					setPanel(new MainWindow());
					break;
				case "Check":
					view.showError("Wrong User");
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			view.showError("Login Fail");
		}
		return logged;
			
	}
	public void comenzarJoc(String joc){
		try {
			server.enviarTrama(new Play("joc"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (joc) {
			case ("Play Roulette"):
				gameManager.executaRuleta();
				break;
			case ("Play Horses"):
				gameManager.executaHorses();
				break;
			case ("Play Blackjack"):
				//game Manager.executaRuleta();
				break;
			case ("Stadistics"):
		
				break;
		}
	}
}
