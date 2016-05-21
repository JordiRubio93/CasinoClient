package view;

import java.awt.Rectangle;

import javax.swing.JPanel;

import controller.Manager;
import model.Utilities;

public abstract class BaseJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Manager manager;
	protected int width;
	protected int height;
	
	public BaseJPanel(){
		Rectangle rectangle = Utilities.getUsableScreenBounds();
		this.width = (int) rectangle.getWidth();
		this.height = (int) rectangle.getHeight();
	}
	
	/**
	 * Obte la capa del controlador
	 * @return Capa on tenim tots els controladors
	 */
	public Manager getManager() {
		return manager;
	}

	
	/**
	 * Dona la posibilitat de la vista cridar al controlador.
	 * @param manager Capa on tenim tots els controladors
	 */
	public void setManager(Manager manager){
		this.manager = manager;
	}
	
	/**
	 * Vincula els elements grafics amb la capa de controlador
	 */
	public abstract void registerController();
	
	/**
	 * Inicialitza tots els elements grafics que inclouen.
	 */
	protected abstract void initElements();
}
