package model.struct.user;

import java.io.Serializable;
import java.util.Date;

public class HistoricSaldo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int joc;
	private Date dia;
	private float diners;
	
	public HistoricSaldo(int joc, Date moment) {
		super();
		this.joc = joc;
		this.dia = moment;
	}

	public int getJoc() {
		return joc;
	}

	public void setJoc(int joc) {
		this.joc = joc;
	}

	public Date getMoment() {
		return dia;
	}

	public void setMoment(Date moment) {
		this.dia = moment;
	}

	public float getDiners() {
		return diners;
	}

	public void setDiners(float diners) {
		this.diners = diners;
	}
}
