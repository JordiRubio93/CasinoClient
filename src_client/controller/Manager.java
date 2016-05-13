package controller;

import java.io.IOException;
import java.net.ConnectException;

import controller.listeners.ButtonListener;
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
import view.PanellPrincipal;

public class Manager {
	private final String rutejson = "config.json";
	private ServerComunication server;
	private ButtonListener controller;
	private PanellPrincipal view;
	private GameManager gameManager;
	private ConfigurationFile cf;

	public Manager(PanellPrincipal view) {
		this.view = view;
		try {
			controller = new ButtonListener(this);
			view.registerController(this);
			view.setPanel(new LoginWindow());
			cf = (new FileManager()).obtenirConfiguracio(rutejson);
			server = new ServerComunication(this, cf);
			server.establirConnexio();
			gameManager = new GameManager(this);
		} catch (FileException e) {
			view.showError("No es troba configuració");
			System.exit(0);
		}  catch (ConnectException e){
			view.showError("No es troba el servidor");
			System.exit(0);
		}catch (IOException e) {
			view.showError("Error");
			System.exit(0);
		}
		
	}

	public void setPanel(BaseJPanel panel) {
		view.setPanel(panel);
	}

	public ButtonListener getController() {
		return controller;
	}


	public ButtonListener getButtonListener() {
		return controller;
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
