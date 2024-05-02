package application.peseur_marqueur;

public class LotsInfo {
    private String name;
    private int lotID;

    public LotsInfo(String name, int lotID) {
        this.name = name;
        this.lotID = lotID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLotID() {
        return lotID;
    }

    public void setLotID(int lotID) {
        this.lotID = lotID;
    }
}
