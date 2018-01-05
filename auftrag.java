import java.util.ArrayList;
import java.util.List;

public class auftrag {

    private List<posten> listPosten = new ArrayList<posten>();
    private String kundenName;
    private String sachbearbeiterName;
    private double gesamtWert;

    public auftrag(String _kunde, String _sachbearbeiter) {

        this.kundenName = _kunde;
        this.sachbearbeiterName = _sachbearbeiter;

    }

    public void addPosten(posten _posten) {
        this.listPosten.add(_posten);
        this.gesamtWert += _posten.getGesamtWert();
    }


    public void showDetails() {
        Integer counter = 0;
        for (posten p : listPosten) {
            counter++;
            System.out.print("Posten "+counter+": ");
            p.showDetails();
        }


    }

    public double getGesamtWert() {
        return gesamtWert;
    }

    public void setGesamtWert(double gesamtWert) {
        this.gesamtWert = gesamtWert;
    }

    public String getKundenName() {
        return kundenName;
    }

    public void setKundenName(String kundenName) {
        this.kundenName = kundenName;
    }

    public String getSachbearbeiterName() {
        return sachbearbeiterName;
    }

    public void setSachbearbeiterName(String sachbearbeiterName) {
        this.sachbearbeiterName = sachbearbeiterName;
    }

    public List<posten> getListPosten() {
        return listPosten;
    }
}
