/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

import controller.listeners.MainButtonsController;
import controller.listeners.MouseController;
import controller.listeners.RowSelectionListener;
import controller.listeners.WindowController;
import model.struct.user.LoginInfo;
import model.struct.user.User;
import network.ServerComunication;
import network.segment.AddCash;
import network.segment.AddUser;
import network.segment.ChangePassword;
import network.segment.Check;
import network.segment.LoginUser;
import network.segment.Segment;
import network.segment.UserWanted;
import tools.excepcions.FileException;
import tools.excepcions.TCPException;
import view.BaseJPanel;
import view.Dialeg;
import view.LoginWindow;
import view.MainFrame;
import view.MainWindow;
import view.RegisterPanel;

/**
 * The Class Manager.
 * (Gestiona tot el programa client.)
 */
public class Manager {
	// Atributs de la classe
	private ServerComunication server;
	private MainButtonsController controller;
	private RowSelectionListener rowListener;
	private MainFrame view;
	private GameManager gameManager;
	private ConfigurationFile cf;
	private FileManager fileManager;
	private boolean serverOn;
	private LoginInfo loginSaved;
	private LedController ledController;
	private WindowController wc;
	private MouseController mouseListener;

	/**
	 * Instantiates a new manager.
	 */
	public Manager() {
		serverOn = false;
		fileManager = new FileManager();
		loginSaved = fileManager.carregarDades();
		gameManager = new GameManager(this);
		controller = new MainButtonsController(this);
		rowListener = new RowSelectionListener(this);
		mouseListener = new MouseController();
	}// Tancament del constructor

	/**
	 * (Comprova que la connexió amb el servidor sigui correcta.)
	 *
	 * @throws TCPException
	 */
	public void checkServer() throws TCPException {
		try {
			cf = (new FileManager()).obtenirConfiguracio(Constants.CONFIG);
			URL url = new URL(cf.getIP_SDB());
			InetAddress address = InetAddress.getByName(url.getHost());
			if (!address.isReachable(5000))
				throw new TCPException("Server OFF");
		} catch (FileException e) {
			// e.printStackTrace();
		} catch (MalformedURLException e) {
		} catch (UnknownHostException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}// Tancament del metode

	/**
	 * Gets login saved.
	 *
	 * @return login saved
	 */
	public LoginInfo getLoginSaved() {
		return loginSaved;
	}// Tancament del getter

	/**
	 * Gets game manager.
	 *
	 * @return game manager
	 */
	public GameManager getGameManager() {
		return gameManager;
	}// Tancament del getter

	/**
	 * (Estableix el servei de connexió amb el servidor.)
	 */
	public void startServer() {
		if (!serverOn) {
			try {
				server = new ServerComunication(this, cf);
				server.establirConnexio(Boolean.FALSE);
			} catch (IOException e) {
				view.showError("Server not found");
				System.exit(0);
			}
		}
	}// Tancament del metode

	/**
	 * (Deslogueja l'usuari.)
	 */
	public void logout() {
		fileManager.deleteUserData();
	}// Tancament del metode

	/**
	 * Sets main frame.
	 *
	 * @param view
	 */
	public void setMainFrame(MainFrame view) {
		this.view = view;
	}// Tancament del setter

	/**
	 * Start game.
	 */
	public void startGame() {
		view.setVisible(true);
		if (loginSaved == null)
			view.showPanel(Constants.LOGIN_VIEW_NAME);
		else
			login(loginSaved);
	}

	/**
	 * (Mostra i oculta el panell lateral de configuració d'usuari.)
	 *
	 * @param open
	 */
	public void lateralMainPanel(boolean open) {
		((MainWindow) view.getPanel("MainWindow")).lateralPanel(open);
	}

	/**
	 * (Mostra el panell desitjat del CardLayout.)
	 *
	 * @param s
	 */
	public void showPanel(String s) {
		view.showPanel(s);
	}

	/**
	 * Gets controller.
	 *
	 * @return controller
	 */
	public MainButtonsController getController() {
		return controller;
	}

	/**
	 * Gets server.
	 *
	 * @return server
	 */
	public ServerComunication getServer() {
		return server;
	}

	/**
	 * (Entra a loguejar a l'usuari directament, sense comprovar dades.)
	 *
	 * @param loginInfo
	 */
	public void login(LoginInfo loginInfo) {
		executelogin(true, loginInfo);
	}

	/**
	 * (Logueja a l'usuari i aquest inicia sessió.)
	 *
	 * @param valid
	 * @param loginInfo
	 */
	public void executelogin(boolean valid, LoginInfo loginInfo) {
		startServer();
		loginInfo.encryptPassword();
		if (valid) {
			try {
				server.enviarTrama(new LoginUser(loginInfo));
				Segment s = (Segment) server.obtenirTrama();
				if (s instanceof AddUser) {
					gameManager.setUser(((AddUser) s).getUser());
					view.showPanel(Constants.MAIN_VIEW_NAME);
					((MainWindow) this.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
							.setLabels(gameManager.getUser(), false);
					this.getController().setGuest(false);
					if (((LoginWindow) view.getPanel(Constants.LOGIN_VIEW_NAME)).getRemember())
						fileManager.saveLoginInfo(loginInfo);
					else
						fileManager.saveLoginInfo(new LoginInfo("", "", false));
				} else {
					view.showError("Login Fail");
					showPanel(Constants.LOGIN_VIEW_NAME);
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
		} else
			view.showError("Login Fail");
	}

	/**
	 * (Recull les dades introduïdes per l'usuari que es vol loguejar i les
	 * tracta.)
	 */
	public void login() {
		LoginWindow p = (LoginWindow) view.getPanel(Constants.LOGIN_VIEW_NAME);
		User u = p.getUser();
		Boolean valid = true;
		if (!gameManager.comprovaLoginMail(u.getEmail())) {
			valid = false;
			view.showError("Wrong e-mail");
			p.showEmailError(true);
		} else
			p.showEmailError(false);
		if (!gameManager.comprovaLoginPW(u.getPassword())) {
			valid = false;
			view.showError("Wrong PW");
			p.showPasswordError(true);
		} else
			p.showPasswordError(false);

		executelogin(valid, p.getUser().getLoginInfo());
	}

	/**
	 * (Fa començar el joc corresponent.)
	 *
	 * @param joc
	 * @param panel
	 */
	public void comenzarJoc(String joc, BaseJPanel panel) {
		switch (joc) {
		case ("Play Roulette"):
			gameManager.executaRoulette();
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

	/**
	 * Gets panel.
	 *
	 * @param string
	 * @return panel
	 */
	public BaseJPanel getPanel(String string) {
		return view.getPanel(string);
	}

	/**
	 * (Registra al nou usuari.)
	 */
	public void register() {
		startServer();
		RegisterPanel rp = ((LoginWindow) getPanel(Constants.LOGIN_VIEW_NAME)).getRegisterPanel();
		User registerInfo = new User(rp.getName(), rp.getSurname(), rp.getPassword(), 0.0, rp.getMail(), new Date(),
				new Date(), rp.getBirthday(), rp.getSex());
		Boolean valid = true;
		// System.out.println(registerInfo.toString());

		// System.out.println(rp.getSex());

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
			registerInfo.getLoginInfo().encryptPassword();
			try {
				server.enviarTrama(new AddUser(registerInfo));
				Segment s = (Segment) server.obtenirTrama();
				if (s instanceof AddUser) {
					gameManager.setUser(((AddUser) s).getUser());
					view.showPanel(Constants.MAIN_VIEW_NAME);
					((MainWindow) this.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
							.setLabels(gameManager.getUser(), false);
					this.getController().setGuest(false);
				} else {
					view.showError("Failed to Register");
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
		} else
			view.showError("Register Fail");
	}

	/**
	 * Gets file manager.
	 *
	 * @return file manager
	 */
	public FileManager getFileManager() {
		return fileManager;
	}

	/**
	 * Sets file manager.
	 *
	 * @param fileManager
	 */
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	/**
	 * Gets cf.
	 *
	 * @return cf
	 */
	public ConfigurationFile getCf() {
		return cf;
	}

	/**
	 * Sets cf.
	 *
	 * @param cf
	 */
	public void setCf(ConfigurationFile cf) {
		this.cf = cf;
	}

	/**
	 * (Canvia la contrasenya de l'usuari.)
	 *
	 * @param password
	 */
	public void changePW(String password) {
		try {
			User user = getGameManager().getUser();
			user.setLoginInfo(new LoginInfo(user.getEmail(), password));
			user.encryptPassword();
			server.enviarTrama(new ChangePassword(user.getPassword()));
			Segment s = (Segment) server.obtenirTrama();
			if (s instanceof Check) {
				if (((Check) s).isOk()) {
					new Dialeg().setWarningText("PW accepted!");
					getGameManager().setUser(user);
					fileManager.deleteUserData();
				} else
					new Dialeg().setWarningText("ERROR with PW!");
			}
		} catch (IOException e) { // e.printStackTrace();
		}
	}// Tancament del metode

	/**
	 * (Afegeix diners al compte d'usuari.)
	 *
	 * @param cash
	 * @param password
	 */
	public void addCash(float cash, String password) {
		try {
			User user = new User(getGameManager().getUser().getName(), password);
			user.setLoginInfo(new LoginInfo(getGameManager().getUser().getEmail(), password));
			user.encryptPassword();
			server.enviarTrama(new AddCash(cash, user.getPassword()));
			Segment s = (Segment) server.obtenirTrama();
			if (s instanceof Check) {
				if (((Check) s).isOk()) {
					new Dialeg().setWarningText("Money accepted!");
					getServer().enviarTrama(new UserWanted(null));
					User u = ((UserWanted) getServer().obtenirTrama()).getUser();
					u.setLoginInfo(user.getLoginInfo());
					getGameManager().setUser(u);
				} else
					new Dialeg().setWarningText("ERROR with PW or CASH!\nYour maximum cash is 100000");
			} else {
				System.err.println(s.getClass());
			}
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}// Tancament del metode

	/**
	 * Gets row listener.
	 *
	 * @return row listener
	 */
	public RowSelectionListener getRowListener() {
		return rowListener;
	}

	/**
	 * Gets view.
	 *
	 * @return view
	 */
	public MainFrame getView() {
		return view;
	}

	/**
	 * Gets ledController.
	 *
	 * @return ledController
	 */
	public LedController getLedController() {
		return ledController;
	}

	/**
	 * Sets ledController.
	 *
	 * @param ledController
	 */
	public void setLedController(LedController ledController) {
		this.ledController = ledController;
	}

	/**
	 * Sets window.
	 *
	 * @param wc
	 */
	public void setWindow(WindowController wc) {
		this.wc = wc;
	}

	/**
	 * Gets window.
	 *
	 * @return window
	 */
	public WindowController getWindow() {
		return wc;
	}

	/**
	 * Gets mouse listener
	 * 
	 * @return mouse listener
	 */
	public MouseController getMouseListener() {
		return mouseListener;
	}

	/**
	 * Sets mouse listener
	 * 
	 * @param mouse listener
	 */
	public void setMouseListener(MouseController mouseListener) {
		this.mouseListener = mouseListener;
	}

}// Tancament de la classe
