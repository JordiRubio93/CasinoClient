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

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import controller.Constants;

/**
 * The Class ColorRenderer.
 * (S'encarrega de pintar les files de la taula amb dos colors alternats per a que sigui més bonica a la vista.)
 */
public class ColorRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		this.setOpaque(true);

		if (row % 2 != 0) {
			this.setBackground(Constants.coolSeaGreen);
		} else {
			this.setBackground(Constants.coolAquaGreen);
		}

		if (column == 2) {
			this.setFont(Constants.italicFont);
		} else {
			this.setFont(Constants.cellFont);
		}

		this.setForeground(Color.WHITE);

		return this;
	}
}
