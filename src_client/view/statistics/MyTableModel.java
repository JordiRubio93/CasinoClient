package view.statistics;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	String[] columnNames;
	ArrayList<Object[]> data; 
	
	public MyTableModel(String[] columnNames, ArrayList<Object[]> data){
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
