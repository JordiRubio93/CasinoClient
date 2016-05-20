package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import controller.listeners.MainButtonsController;
import model.struct.user.LoginInfo;
import model.struct.user.User;
import network.ServerComunication;
import network.segment.AddUser;
import network.segment.LoginUser;
import network.segment.RouletteBetting;
import network.segment.Seconds;
import network.segment.Segment;
import tools.excepcions.FileException;
import tools.excepcions.TCPException;
import view.BaseJPanel;
import view.LoginWindow;
import view.MainFrame;
import view.MainWindow;
import view.RegisterPanel;
import view.roulette.RouletteView;

public class Manager {
	private final String rutejson = "config.json";
	private ServerComunication server;
	private MainButtonsController controller;
	private MainFrame view;
	private GameManager gameManager;
	private ConfigurationFile cf;
	private FileManager fileManager;
	private boolean serverOn;
	private LoginInfo loginSaved;
	
	public Manager(){
		serverOn = false;
		fileManager = new FileManager();
		loginSaved = fileManager.carregarDades();
		gameManager = new GameManager(this);
		controller = new MainButtonsController(this);			
	}
	
	public void checkServer() throws TCPException{
		try {
			cf = (new FileManager()).obtenirConfiguracio(rutejson);
			InetAddress address = InetAddress.getByName(cf.getIP_SDB());
			if(!address.isReachable(5000)) throw new TCPException("Server OFF");
		} catch (IOException | FileException e) {
			e.printStackTrace();
		}
	}
	
 	public LoginInfo getLoginSaved() {
		return loginSaved;
	}
 	
	public GameManager getGameManager() {
		return gameManager;
	}


	public void startServer() {
		if (!serverOn) {
			try {
				server = new ServerComunication(this, cf);
				server.establirConnexio();
			} catch (IOException e) {
				view.showError("Server not found");
				System.exit(0);
			}
		}
	}

	public void logout(){
		fileManager.logout();
	}

	public void setMainFrame(MainFrame view) {
		this.view = view;
	}
	
	public void startGame(){
		 view.setVisible(true);
		if (loginSaved == null)	
			view.showPanel(Constants.LOGIN_VIEW_NAME);
		else login(loginSaved);
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

	
	public void login(LoginInfo loginInfo){
		executelogin(true, loginInfo);
	}
	
	public void executelogin(boolean valid, LoginInfo loginInfo){
		startServer();
		loginInfo.EncryptPassword();
		if (valid){
			try {
				server.enviarTrama(new LoginUser(loginInfo));
				Segment s = (Segment) server.obtenirTrama();
				if (s instanceof AddUser){
					gameManager.setUser(((AddUser) s).getUser());
					view.showPanel(Constants.MAIN_VIEW_NAME);
					if (( (LoginWindow) view.getPanel(Constants.LOGIN_VIEW_NAME)).getRemember()) fileManager.saveLoginInfo(loginInfo);
					else fileManager.saveLoginInfo(new LoginInfo("", "", false));
				}
				else{
					view.showError("Wrong User");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else view.showError("Login Fail");
	}
	
	public void login(){
		LoginWindow p = (LoginWindow) view.getPanel(Constants.LOGIN_VIEW_NAME);
		User u = p.getUser();
		Boolean valid = true;
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
		
		executelogin(valid,  p.getUser().getLoginInfo());
	}
	
	public void comenzarJoc(String joc, BaseJPanel panel){
		switch (joc) {
			case ("Play Roulette"):
				gameManager.executaRuleta((RouletteView) panel);
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

	public void register(){
		startServer();
		RegisterPanel rp = ((LoginWindow) getPanel(Constants.LOGIN_VIEW_NAME)).getRegisterPanel();
		User registerInfo = new User(rp.getName(), rp.getSurname(), rp.getPassword(), 0.0, rp.getMail(), new Date(),
					new Date(), rp.getBirthday(), rp.getSex());
		Boolean valid = true;
		System.out.println(registerInfo.toString());
		
		// comprova que les dades estiguin ok
		if (!gameManager.comprovaName(registerInfo.getName())) valid = false;
		if (!gameManager.comprovaSurname(registerInfo.getSurname())) valid = false;
		if (!gameManager.comprovaLoginMail(registerInfo.getEmail())) valid = false;
		if (!gameManager.comprovaLoginPW(registerInfo.getPassword()))valid = false;
		if (!gameManager.comprovaAge(registerInfo.getBirthday())) valid = false;		
		if (valid) {
			registerInfo.getLoginInfo().EncryptPassword();
			try {
				server.enviarTrama(new AddUser(registerInfo));
				Segment s = (Segment) server.obtenirTrama();
				if (s instanceof AddUser){
					gameManager.setUser(((AddUser) s).getUser());
					view.showPanel(Constants.MAIN_VIEW_NAME);
				}
				else {
					view.showError("Failed to Register");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else view.showError("Register Fail");	
	}
	
	public int seconds(){
		try {
			server.enviarTrama(new Seconds(0));
			return ((Seconds) server.obtenirTrama()).getSegons();
		} catch (IOException e) {}
		return -1;
	}
	
	public void sendBet(){
		try {
			server.enviarTrama(new RouletteBetting(gameManager.getUser().getEmail(), gameManager.getRoulette().getApostesRuleta()));
		} catch (IOException e) {}
	}
}
