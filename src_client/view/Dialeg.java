package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Dialeg{
	private JDialog jdDialeg;
	private int result;
	private String amount;
	
	public Dialeg() {
		jdDialeg = new JDialog();
		amount = new String();
	}
	
	public void setConfirmText(String cad){
		result = JOptionPane.showConfirmDialog(jdDialeg, cad);
	}
	
	public void setWarningText(String cad){
		JOptionPane.showMessageDialog(jdDialeg, cad);
	}
	
	public void setInputText(String cad){
		amount = JOptionPane.showInputDialog(jdDialeg, cad);
	}

	public JDialog getDialeg() {
		return jdDialeg;
	}
	
	public String getAmount() {
		return amount;
	}

	public int getResult(){
		return result;
	}
}
