package controller;

import java.io.IOException;
import java.net.ConnectException;

import controller.listeners.MainButtonsController;
import model.struct.user.LoginInfo;
import model.struct.user.User;
import network.ServerComunication;
import network.segment.LoginUser;
import network.segment.NotifyConRoom;
import network.segment.Segment;
import tools.excepcions.FileException;
import view.BaseJPanel;
import view.LoginWindow;
import view.MainFrame;
import view.MainWindow;

public class Manager {
	private final String rutejson = "config.json";
	private ServerComunication server;
	private MainButtonsController controller;
	private MainFrame view;
	private GameManager gameManager;
	private ConfigurationFile cf;
	private FileManager fileManager;
	
	public Manager(MainFrame view) {
		this.view = view;
		try {
			fileManager = new FileManager();
			LoginInfo loginSaved = fileManager.carregarDades();
			controller = new MainButtonsController(this);
			cf = (new FileManager()).obtenirConfiguracio(rutejson);
			server = new ServerComunication(this, cf);
			server.establirConnexio();
			gameManager = new GameManager(this);
			//TODO REMEMBER ME 			view.showPanel("MainWindow");
			view.showPanel("LoginWindow");
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
	
	public void lateralMainPanel(boolean open){
		((MainWindow) view.getPanel("MainWindow")).lateralPanel(open);
	}
	
	public void showPanel(String s){
		view.showPanel(s);
	}
	
	public MainButtonsController getController() {
		return controller;
	}

	public ServerComunication getServer() {
		return server;
	}

	public boolean login(){
		LoginWindow p = (LoginWindow) getPanel("LoginWindow");
		User u = p.getUser();
		Boolean valid = true;
		Boolean logged = false;
		
		if (!gameManager.comprovaLoginMail(u.getEmail())){
			valid = false;
			view.showError("Wrong mail");
			p.showEmailError(true);
		}
		else p.showEmailError(false);
		
		if (!gameManager.comprovaLoginPW(u.getPassword())){
			valid = false;
			view.showError("Wrong pw");
			p.showPasswordError(true);
		}
		else p.showPasswordError(false);
		u.getLoginInfo().EncryptPassword();
		if (valid){
			try {
				server.enviarTrama(new LoginUser(u));
				Segment s = (Segment) server.obtenirTrama();
				switch(( s.getClass().getSimpleName()) ){
				case "LoginUser":
					LoginUser not = (LoginUser) s;
					gameManager.setUser(not.getU());
					view.showPanel("MainWindow");
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
		switch (joc) {
			case ("Play Roulette"):
				gameManager.executaRuleta();
				break;
			case ("Play Horses"):
				gameManager.executaHorses();
				break;
			case ("Play BlackJack"):
				gameManager.executaBlackjack();
				break;
			case ("Stadistics"):
				break;
		}
	}

	public BaseJPanel getPanel(String string) {
		return view.getPanel(string);
	}

	public void register() {
		// TODO Auto-generated method stub
		
	}
}
