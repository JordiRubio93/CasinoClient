package view.statistics;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import controller.Constants;

public class ColorRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
		this.setOpaque(true);
	   
		if (row%2 != 0){
			this.setBackground(Constants.coolSeaGreen);
		}else{
			this.setBackground(Constants.coolAquaGreen);
		}
		
		if(column == 2){
			this.setFont(Constants.italicFont);
		}else{
			this.setFont(Constants.cellFont);
		}
		
		this.setForeground(Color.WHITE);
		
		return this;
	}
}

