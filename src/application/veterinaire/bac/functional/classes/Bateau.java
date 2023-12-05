package application.veterinaire.bac.functional.classes;

public class Bateau {
	private String nom;
	private String matricul;
	
	//On creer une classe bateau qui reunira les infos relatives au bateau
	public Bateau(String Nom, String Matricul) {
		setNom(Nom);
		setMatricul(Matricul);
	}
	public  void setNom(String nom) {
		this.nom = nom;
	}
	public  void setMatricul(String matricul) {
		this.matricul = matricul;
	}
	public  String getNom() {
		return this.nom;
	}
	public  String getMatricul() {
		return this.matricul;
	}
}
