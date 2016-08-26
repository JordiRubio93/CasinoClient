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

package view.statistics;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * The Class MyTableModel.
 * (Conté les dades de la taula del rànquing de monedes.)
 */
public class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	String[] columnNames;
	ArrayList<Object[]> data;

	/**
	 * Instantiates a new my table model.
	 *
	 * @param columnNames
	 * @param data
	 */
	public MyTableModel(String[] columnNames, ArrayList<Object[]> data) {
		this.columnNames = columnNames;
		this.data = data;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
