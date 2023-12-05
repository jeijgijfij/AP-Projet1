package application.veterinaire.bac.functional.classes;

public class Bac {
	private String Id;
	private String Bateau;
	private String DatePeche;
	private String Lot;
	private String TypeBac;
	
	//On creer une classe bateau qui reunira les infos relatives au bateau
	public Bac(String Bateau, String Lot, String TypeBac) {
		setBateau(Bateau);
		setLot(Lot);
		setTypeBac(TypeBac);
	}
	public  void setId(String id) {
		this.Id = id;
	}
	public  void setBateau(String Bateau) {
		this.Bateau = Bateau;
	}
	public  void setDatePeche(String DatePeche) {
		this.DatePeche = DatePeche;
	}
	public  void setLot(String Lot) {
		this.Lot = Lot;
	}
	public  void setTypeBac(String TypeBac) {
		this.TypeBac = TypeBac;
	}
	
	public  String getId() {
		return this.Id;
	}
	public  String getBateau() {
		return this.Bateau;
	}
	public  String getDatePeche() {
		return this.DatePeche;
	}
	public  String getLot() {
		return this.Lot;
	}
	public  String getTypeBac() {
		return this.TypeBac;
	}
	
}

