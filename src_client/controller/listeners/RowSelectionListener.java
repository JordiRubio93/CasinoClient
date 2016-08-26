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

package controller.listeners;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.CashEvoExecutor;
import controller.Constants;
import controller.Manager;
import network.segment.WhoAmI;
import view.Dialeg;
import view.statistics.CashRankingWindow;

/**
 * The listener interface for receiving rowSelection events. The class that is
 * interested in processing a rowSelection event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addRowSelectionListener<code> method. When the rowSelection
 * event occurs, that object's appropriate method is invoked.
 *
 * @see RowSelectionEvent
 */
public class RowSelectionListener implements ListSelectionListener {
	private ArrayList<Object[]> data;
	private Manager manager;
	private CashEvoExecutor ce;
	private int row;

	/**
	 * Instantiates a new row selection listener.
	 *
	 * @param manager
	 */
	public RowSelectionListener(Manager manager) {
		this.manager = manager;
	}

	/**
	 * (Quan la fila seleccionada canvia procedeix a mostrar l'evolució de saldo de l'usuari en qüestió.)
	 */
	@Override
	public void valueChanged(ListSelectionEvent event) {
		row = ((CashRankingWindow) manager.getPanel(Constants.CASH_RANKING_VIEW_NAME)).getSelectedData();
		if (row >= 0) {
			try {
				manager.getServer().enviarTrama(new WhoAmI(null));
				if (((WhoAmI) manager.getServer().obtenirTrama()).getId().equals(data.get(row)[0])) {
					ce = new CashEvoExecutor();
					ce.executaGrafic(manager, data.get(row)[0].toString(), data.get(row)[1].toString(),
							data.get(row)[2].toString());
					ce.setBack(true);
				} else
					new Dialeg().setWarningText("Access denied.\nYou're not this user...");
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
	}

	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public ArrayList<Object[]> getData() {
		return data;
	}

	/**
	 * Sets data.
	 *
	 * @param data
	 */
	public void setData(ArrayList<Object[]> data) {
		this.data = data;
	}
}
