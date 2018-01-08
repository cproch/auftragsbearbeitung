import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class main {
    private static List<auftrag> auftragsListe = new ArrayList<auftrag>();
    private static List<produkt> produktListe = new ArrayList<produkt>();
    private static boolean runProgramm = true;
    private static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        SetSeedData();
        while (runProgramm) {
            showMainMenue();
        }
        showQuitMenue();
    }



    /*Menüs:
    *
    *
    * Main:
    *   - Aufträge
    *       - Produkt hinzufügen
    *           - Prüfen, ob Produkt bereits vorhanden, wenn ja hinweis ( menge erhöhen, abbrechen )
    *             wenn nein, eingabe zulassen
    *       - Bearbeiten
    *           - Sachbearbeiter Name Ändern
    *       - Übersicht aller Aufträge ( eines speziellen Sachbearbeiters ? )
    *         angezeigt werden soll:
    *                               - Pro Posten: Produktname, Anzahl und Gesamtpreis des Posten
    *       - Gesamtpreis des Auftrages anzeigen lassen
    *   - Produkte
    *       - Einzelpreis ändern
    *       - Eingabe von Produkten
    *
    *  ------ Nacheinander sollen mehrere Aufträge eingegeben werden können
    *
    * */


    private static void showMainMenue() {
        int[] menueNumberRange = {0, 1, 2};
        Integer intMenueChoice = 0;

        System.out.println("Was möchten sie als nächstes tun?");
        System.out.println("(1) Auftragsmenü");
        System.out.println("(2) Produktmenü");
        System.out.println("(0) Beenden");
        intMenueChoice = userInputIsInteger();

        if (userInputIsValidMenueNumber(menueNumberRange, intMenueChoice)) {

            if (intMenueChoice == 0) {
                runProgramm = false;
            }

            if (intMenueChoice == 1) {
                showContractMenue();
            }

            if (intMenueChoice == 2) {
                showProductMenue();
            }

        } else {
            System.out.println("Dieser Menüpunkt existiert nicht");
            showMainMenue();
        }


    }

    public static void showContractMenue() {
        System.out.println("Vertrags Menü");

    }

    public static void showProductMenue() {
        int[] menueNumberRange = {0, 1, 2};
        int intMenueChoice;
        boolean inMenue = true;

        while (inMenue) {
            System.out.println("Produkt Menü");
            System.out.println("(0) zum Hauptmenü");
            System.out.println("(1) Produkt eingeben");
            System.out.println("(2) Preis ändern");
            intMenueChoice = userInputIsInteger();

            if (userInputIsValidMenueNumber(menueNumberRange, intMenueChoice)) {


                if (intMenueChoice == 0) {
                    inMenue = false;
                }

                if (intMenueChoice == 1) {
                    System.out.println("Produkt eingeben");
                    //produktListe
                }

                if (intMenueChoice == 2) {
                    System.out.println("Preis ändern");
                    //produktListe
                }


            } else {
                System.out.println("Dieser Menüpunkt existiert nicht");
            }

        }

    }


    public static void showQuitMenue() {
        System.out.println("Alle Aufträge:");
        ShowAllAuftraege();
        System.out.println("Auf wiedersehen");
    }


    private static boolean userInputIsValidMenueNumber(int[] menueArray, int userInput) {
        for (int intNum : menueArray) {
            if (userInput == intNum)
                return true;
        }
        return false;
    }

    private static int userInputIsInteger() {
        int n1 = 0;
        boolean bError = true;
        while (bError) {
            try {
                n1 = parseInt(userInput.nextLine());
                bError = false;
            } catch (NumberFormatException e) {
                System.out.println("Bitte nur Zahlen eingeben");
            }
        }
        return n1;
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

        produktListe.add(Prod1);
        produktListe.add(Prod2);
        produktListe.add(Prod3);
        produktListe.add(Prod4);

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
