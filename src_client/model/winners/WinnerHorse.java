package model.winners;

public class WinnerHorse extends Winner{
	private static final long serialVersionUID = 1L;
	private String nom;
	
	public WinnerHorse(int num, String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
