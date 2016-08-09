package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.LinkedList;

import model.struct.horses.HorseData;
import model.struct.user.LoginInfo;
import model.struct.user.User;
import network.ServerComunication;
import network.segment.AddCash;
import network.segment.AddUser;
import network.segment.ChangePassword;
import network.segment.Check;
import network.segment.LoginUser;
import network.segment.Segment;
import tools.excepcions.FileException;
import tools.excepcions.TCPException;
import view.BaseJPanel;
import view.Dialeg;
import view.LoginWindow;
import view.MainFrame;
import view.MainWindow;
import view.RegisterPanel;

/**
 * 
 * <p>
 * <b> Classe: Manager </b> <br/>
 * Implementa el manager del client.
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class Manager {
	//Atributs de la classe
	private final String rutejson = "config.json";
	private ServerComunication server;
	private MainButtonsController controller;
	private MainFrame view;
	private GameManager gameManager;
	private ConfigurationFile cf;
	private FileManager fileManager;
	private boolean serverOn;
	private LoginInfo loginSaved;

	/**
	 * Constructor del Manager.
	 */
	public Manager() {
		serverOn = false;
		fileManager = new FileManager();
		loginSaved = fileManager.carregarDades();
		gameManager = new GameManager(this);
		controller = new MainButtonsController(this);
	}//Tancament del constructor

	/**
	 * Metode que no retorna res i que s'encarrega de checkejar el server.
	 */
	public void checkServer() throws TCPException {
		try {
			cf = (new FileManager()).obtenirConfiguracio(rutejson);
			InetAddress address = InetAddress.getByName(cf.getIP_SDB());
			if (!address.isReachable(5000))
				throw new TCPException("Server OFF");
		} catch (IOException | FileException e) {
			e.printStackTrace();
		}
	}//Tancament del metode

	/**
	 * Getter de LoginSaved.
	 */
	public LoginInfo getLoginSaved() {
		return loginSaved;
	}//Tancament del getter

	/**
	 * Getter de GameManager.
	 */
	public GameManager getGameManager() {
		return gameManager;
	}//Tancament del getter

	/**
	 * Metode que no retorna res i que s'encarrega de conectar amb el server.
	 */
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
	}//Tancament del metode

	/**
	 * Metode que no retorna res i que s'encarrega de fer el logout.
	 */
	public void logout() {
		fileManager.logout();
	}//Tancament del metode

	/**
	 * Setter de MainFrame.
	 */
	public void setMainFrame(MainFrame view) {
		this.view = view;
	}//Tancament del setter

	
	public void startGame() {
		view.setVisible(true);
		if (loginSaved == null)
			view.showPanel(Constants.LOGIN_VIEW_NAME);
		else
			login(loginSaved);
	}

	public void lateralMainPanel(boolean open) {
		((MainWindow) view.getPanel("MainWindow")).lateralPanel(open);
	}

	public void showPanel(String s) {
		view.showPanel(s);
	}

	public MainButtonsController getController() {
		return controller;
	}

	public ServerComunication getServer() {
		return server;
	}

	public void login(LoginInfo loginInfo) {
		executelogin(true, loginInfo);
	}

	public void executelogin(boolean valid, LoginInfo loginInfo) {
		startServer();
		loginInfo.EncryptPassword();
		if (valid) {
			try {
				server.enviarTrama(new LoginUser(loginInfo));
				Segment s = (Segment) server.obtenirTrama();
				if (s instanceof AddUser) {
					gameManager.setUser(((AddUser) s).getUser());
					view.showPanel(Constants.MAIN_VIEW_NAME);
					if (((LoginWindow) view.getPanel(Constants.LOGIN_VIEW_NAME)).getRemember())
						fileManager.saveLoginInfo(loginInfo);
					else
						fileManager.saveLoginInfo(new LoginInfo("", "", false));
				} else {
					view.showError("Login Fail");
					showPanel(Constants.LOGIN_VIEW_NAME);
				}
					

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			view.showError("Login Fail");
	}

	public void login() {
		LoginWindow p = (LoginWindow) view.getPanel(Constants.LOGIN_VIEW_NAME);
		User u = p.getUser();
		Boolean valid = true;
		if (!gameManager.comprovaLoginMail(u.getEmail())) {
			valid = false;
			view.showError("Wrong mail");
			p.showEmailError(true);
		} else p.showEmailError(false);
		if (!gameManager.comprovaLoginPW(u.getPassword())) {
			valid = false;
			view.showError("Wrong pw");
			p.showPasswordError(true);
		} else
			p.showPasswordError(false);

		executelogin(valid, p.getUser().getLoginInfo());
	}

	public void comenzarJoc(String joc, BaseJPanel panel) {
		Check c;
		switch (joc) {
		case ("Play Roulette"):
			c = ((Check) server.obtenirTrama());
			if (c instanceof Check && (c.isOk())) gameManager.executaRoulette();
			else new Dialeg().setWarningText("You can't bet");
			break;
		case ("Play Horses"):
			System.out.println();
			c = ((Check) server.obtenirTrama());
			if (c instanceof Check && (c.isOk())) gameManager.executaHorses();
			else new Dialeg().setWarningText("You can't bet");
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
		startServer();
		RegisterPanel rp = ((LoginWindow) getPanel(Constants.LOGIN_VIEW_NAME)).getRegisterPanel();
		User registerInfo = new User(rp.getName(), rp.getSurname(), rp.getPassword(), 0.0, rp.getMail(), new Date(),
				new Date(), rp.getBirthday(), rp.getSex());
		Boolean valid = true;
		System.out.println(registerInfo.toString());

		// comprova que les dades estiguin ok
		if (!gameManager.comprovaName(registerInfo.getName()))
			valid = false;
		if (!gameManager.comprovaSurname(registerInfo.getSurname()))
			valid = false;
		if (!gameManager.comprovaLoginMail(registerInfo.getEmail()))
			valid = false;
		if (!gameManager.comprovaLoginPW(registerInfo.getPassword()))
			valid = false;
		if (!gameManager.comprovaAge(registerInfo.getBirthday()))
			valid = false;
		if (valid) {
			registerInfo.getLoginInfo().EncryptPassword();
			try {
				server.enviarTrama(new AddUser(registerInfo));
				Segment s = (Segment) server.obtenirTrama();
				if (s instanceof AddUser) {
					gameManager.setUser(((AddUser) s).getUser());
					view.showPanel(Constants.MAIN_VIEW_NAME);
				} else {
					view.showError("Failed to Register");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			view.showError("Register Fail");
	}	

	public LinkedList<HorseData> getHorsesList() {
		return fileManager.getHorsesList();
	}

	/**
	 * Getter de FileManager.
	 */
	public FileManager getFileManager() {
		return fileManager;
	}
	/**
	 * Setter de FileManager.
	 */
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	/**
	 * Metode que no retorna res, rep un String i s'encarrega de canviar el password de l'usuari.
	 * @param password
	 */
	public void changePW(String password) {
		try {
			User user = getGameManager().getUser();
			user.setLoginInfo(new LoginInfo(user.getEmail(), password));
			user.EncryptPassword();
			server.enviarTrama(new ChangePassword(user.getPassword()));
			Segment s = (Segment) server.obtenirTrama();
			if (s instanceof Check) {
				if (((Check) s).isOk()) {
					new Dialeg().setWarningText("PW accepted");
					getGameManager().setUser(user);
				} else
					new Dialeg().setWarningText("ERROR with PW");
			}
		} catch (IOException e){ e.printStackTrace();}
	}//Tancament del metode
	public void addCash(float cash, String password) {
		try {
			/**encriptem la password abans de enviarla**/
			User user = new User(getGameManager().getUser().getName(), password);
			user.setLoginInfo(new LoginInfo(user.getEmail(), password));
			user.EncryptPassword();
			server.enviarTrama(new AddCash(cash, user.getPassword()));
			Segment s = (Segment) server.obtenirTrama();
			if (s instanceof Check) {
				if (((Check) s).isOk()) {
					new Dialeg().setWarningText("Money accepted!");
					getGameManager().getUser().addCash(cash);
				} else
					new Dialeg().setWarningText("ERROR with PW");
			}
		} catch (IOException e){ e.printStackTrace();}
	}//Tancament del metode
 
}//Tancament de la classe
