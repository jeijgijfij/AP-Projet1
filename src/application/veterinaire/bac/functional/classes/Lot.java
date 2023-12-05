package application.veterinaire.bac.functional.classes;

public class Lot {
	private String id;
	private String Bateau;
	private String datePeche;
	private String espece;
	private String taille;
	private String presentation;
	private String qualite;
	private String poids;
	
	//On creer une classe bateau qui reunira les infos relatives au bateau
	public Lot(String id, String bateau, String datePeche, String espece, String taille, String presentation, String qualite, String poids) {
		setId(id);
		setBateau(bateau);
		setDatePeche(datePeche);
		setEspece(espece);
		setTaille(taille);
		setPresentation(presentation);
		setQualite(qualite);
		setPoids(poids);
	}
	public  void setId(String id) {
		this.id = id;
	}
	public  void setBateau(String Bateau) {
		this.Bateau = Bateau;
	}
	public  void setDatePeche(String datePeche) {
		this.datePeche = datePeche;
	}
	public  void setEspece(String espece) {
		this.espece = espece;
	}
	public  void setTaille(String taille) {
		this.taille = taille;
	}
	public  void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	public  void setQualite(String qualite) {
		this.qualite = qualite;
	}
	public  void setPoids(String poids) {
		this.poids = poids;
	}
	
	public  String getId() {
		return this.id;
	}
	public  String getBateau() {
		return this.Bateau;
	}
	public  String getDatePeche() {
		return this.datePeche;
	}
	public  String getEspece() {
		return this.espece;
	}
	public  String getTaille() {
		return this.taille;
	}
	public  String getPresentation() {
		return this.presentation;
	}
	public  String getQualite() {
		return this.qualite;
	}
	public  String getPoids() {
		return this.poids;
	}
	
}