package model.struct.user;

import java.io.Serializable;

public class HistoricPartides implements Serializable {
	private static final long serialVersionUID = 1L;
	private PublicUser client;
	private int joc;
	private float guanys;
	
	public HistoricPartides(PublicUser client, int joc, float guanys) {
		super();
		this.client = client;
		this.joc = joc;
		this.guanys = guanys;
	}

	public PublicUser getClient() {
		return client;
	}

	public void setClient(PublicUser client) {
		this.client = client;
	}

	public int getJoc() {
		return joc;
	}

	public void setJoc(int joc) {
		this.joc = joc;
	}

	public float getGuanys() {
		return guanys;
	}

	public void setGuanys(float guanys) {
		this.guanys = guanys;
	}
}
