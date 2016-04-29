package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Dialeg{
	private JDialog jdDialeg;
	private int result;
	
	public Dialeg() {
		jdDialeg = new JDialog();
	}
	
	public void setConfirmText(String cad){
		result = JOptionPane.showConfirmDialog(jdDialeg, cad);
	}
	
	public void setWarningText(String cad){
		JOptionPane.showMessageDialog(jdDialeg, cad);
	}

	public JDialog getDialeg() {
		return jdDialeg;
	}
	
	public int getResult(){
		return result;
	}
}
