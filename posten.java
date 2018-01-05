import java.util.ArrayList;
import java.util.List;

public class posten {

    private double gesamtWert;
    private Integer menge;
    private List<produkt> listProdukte = new ArrayList<produkt>();

    public posten() {

    }


    public void addProdukt(produkt _produkt, Integer _menge) {
        this.listProdukte.add(_produkt);
        this.gesamtWert += (_produkt.getPreis() * _menge);
        this.menge = _menge;
    }


    public void showDetails() {

        for (produkt p : listProdukte) {
            System.out.println("Name: " + p.getName() + ", Menge: " + this.getMenge() + ", Einzelpreis: " + p.getPreis()+ ", Gesamtpreis: "+this.getGesamtWert());
        }

    }

    public Integer getMenge() {
        return menge;
    }

    public void setMenge(Integer menge) {
        this.menge = menge;
    }

    public double getGesamtWert() {
        return gesamtWert;
    }

    public void setGesamtWert(double gesamtWert) {
        this.gesamtWert = gesamtWert;
    }

    public List<produkt> getListPosten() {
        return listProdukte;
    }

}
