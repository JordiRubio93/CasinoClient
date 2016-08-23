package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.RegisterValidator;
import view.RegisterPanel;

public class RegisterController implements KeyListener {
	private RegisterPanel panel;
	private RegisterValidator validator;
	private boolean num, special, letter;
	
	public RegisterController(RegisterPanel panel){
		this.panel = panel;
		this.validator = new RegisterValidator();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(panel.getNameField())){
			if(panel.getNameField().getText().isEmpty()){
				panel.setFieldBackground(panel.getNameField(), false);
			}else if(validator.validateName(panel.getName())){
				panel.setFieldBackground(panel.getNameField(), false);
			}else{
				panel.setFieldBackground(panel.getNameField(), true);
				panel.enableRegisterButton(false);
			}
		}else if(e.getSource().equals(panel.getSurnameField())){
			if(panel.getSurnameField().getText().isEmpty()){
				panel.setFieldBackground(panel.getSurnameField(), false);
			}else if(validator.validateName(panel.getSurname())){
				panel.setFieldBackground(panel.getSurnameField(), false);
			}else{
				panel.setFieldBackground(panel.getSurnameField(), true);
				panel.enableRegisterButton(false);
			}	
		}else if(e.getSource().equals(panel.getMailField())){
			if(panel.getMailField().getText().isEmpty()){
				panel.setFieldBackground(panel.getMailField(), false);
			}else if(validator.validateEmailFormat(panel.getMail())){
				panel.setFieldBackground(panel.getMailField(), false);
			}else{
				panel.setFieldBackground(panel.getMailField(), true);
				panel.enableRegisterButton(false);
			}
		}else if(e.getSource().equals(panel.getPasswordField())){
			if(validator.validatePasswordFormat(panel.getPassword())){
				String cad = panel.getPassword();
				
				if(cad.isEmpty()){
					panel.setFieldBackground(panel.getPasswordField(), false);
					return;
				}
				
				if(validator.validatePasswordFormat(panel.getPassword())){
					panel.setFieldBackground(panel.getPasswordField(), false);
					return;
				}
				
				if(cad.matches(".*\\d.*")){
					num = true;
				}else{
					panel.setFieldBackground(panel.getPasswordField(), true);
					panel.enableRegisterButton(false);
					num = false;
				}
				
				if(!cad.matches("[a-zA-Z0-9]*")){
					special = true;
				}else{
					panel.setFieldBackground(panel.getPasswordField(), true);
					panel.enableRegisterButton(false);
					special = false;
				}
				
				if(cad.equals(cad.toLowerCase()) || cad.equals(cad.toUpperCase())){
					panel.setFieldBackground(panel.getPasswordField(), true);
					panel.enableRegisterButton(false);
					letter = false;
				}else{
					letter = true;
				}
				
				if(num && special && letter) panel.setFieldBackground(panel.getPasswordField(), false);
				else{
					panel.setFieldBackground(panel.getPasswordField(), true);
					panel.enableRegisterButton(false);
				}
			}else{
				panel.setFieldBackground(panel.getPasswordField(), true);
				panel.enableRegisterButton(false);
			}
		}else if(e.getSource().equals(panel.getPasswordField2())){
			if (panel.getPassword().length() > 5 && panel.getPassword().equals(panel.getPassword2())){
				panel.setFieldBackground(panel.getPasswordField2(), false);
				panel.enableRegisterButton(true);
			}else{
				panel.setFieldBackground(panel.getPasswordField2(), true);
				panel.enableRegisterButton(false);
			}
		}else{
			System.out.println("WTF?");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
