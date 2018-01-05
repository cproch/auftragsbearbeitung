public class produkt {
    private double preis;
    private String name;

    public produkt(double _preis, String _name) {
        this.preis = _preis;
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

}
