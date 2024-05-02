package application.peseur_marqueur;

import java.util.Date;

public class BacsInfo {
    private String name;
    private String bateau;
    private Date datePeche;
    private float poids;
    private int bacID;

    public BacsInfo(String name, String bateau, Date datePeche, float poids, int bacID) {
        this.name = name;
        this.bateau = bateau;
        this.datePeche = datePeche;
        this.poids = poids;
        this.bacID = bacID;
    }

    public String getName() {
        return name;
    }

    public String getBateau() {
        return bateau;
    }

    public Date getDatePeche() {
        return datePeche;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }
}