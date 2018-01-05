import java.util.ArrayList;
import java.util.List;

public class main {
    private static List<auftrag> auftragsListe = new ArrayList<auftrag>();
    private static boolean runProgramm = true;
    private static Integer menueToShow=0;

    public static void main(String[] args) {

        while (runProgramm) {
            switchMenuesToSow();
        }

//        SetSeedData();
        //       ShowAllAuftraege();
    }

    private static void switchMenuesToSow() {

        switch (menueToShow) {
            case 0:
                showMainMenue();
                break;
            case 1:
                showContractMenue();
                break;
            default:
                break;
        }


        showQuitMenue();
        System.exit(0);

    }


    private static void showMainMenue() {
        System.out.println("test");
        menueToShow = 1;

    }

    public static void showContractMenue() {

    }


    public static void showQuitMenue() {

    }


    private static void ShowAllAuftraege() {

        Integer counter = 0;
        for (auftrag a : auftragsListe) {
            counter++;
            System.out.println("Auftrag " + counter + ":");
            a.showDetails();
            System.out.println("Rechungsbetrag: " + a.getGesamtWert());
            System.out.println("####");
        }


    }

    private static void SetSeedData() {
        // Seed Daten beginn

        String Sachbearbeiter1 = "Sachb 1";
        String Kunde1 = "Kunde 1";
        String Sachbearbeiter2 = "Sachb 1";
        String Kunde2 = "Kunde 1";


        produkt Prod1 = new produkt(0.99, "Stift");
        produkt Prod2 = new produkt(1.99, "Papier");
        produkt Prod3 = new produkt(2.99, "Radiergummi");
        produkt Prod4 = new produkt(3.99, "Geodreieck");

        posten Posten1 = new posten();
        Posten1.addProdukt(Prod1, 1);
        posten Posten2 = new posten();
        Posten2.addProdukt(Prod2, 2);

        posten Posten3 = new posten();
        Posten3.addProdukt(Prod3, 3);

        posten Posten4 = new posten();
        Posten4.addProdukt(Prod4, 4);

        auftrag Auftrag1 = new auftrag(Kunde1, Sachbearbeiter1);
        auftrag Auftrag2 = new auftrag(Kunde2, Sachbearbeiter2);
        Auftrag1.addPosten(Posten1);
        Auftrag1.addPosten(Posten2);
        Auftrag2.addPosten(Posten3);
        Auftrag2.addPosten(Posten4);

        auftragsListe.add(Auftrag1);
        auftragsListe.add(Auftrag2);

    }

}
