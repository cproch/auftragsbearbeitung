import java.util.ArrayList;
import java.util.InputMismatchException;
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
    *
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
        int[] menueNumberRange = {0, 1, 2};
        int intMenueChoice;
        boolean inMenue = true;

        while (inMenue) {
            System.out.println("Produkt Menü");
            System.out.println("(0) zum Hauptmenü");
            System.out.println("(1) Vertrag eingeben");
            System.out.println("(2) Alle Verträge anzeigen");
            intMenueChoice = userInputIsInteger();

            if (userInputIsValidMenueNumber(menueNumberRange, intMenueChoice)) {

                if (intMenueChoice == 0) {
                    inMenue = false;
                }

                if (intMenueChoice == 1) {
                    enterNewContract();
                }

                if (intMenueChoice == 2) {
                    ShowAllAuftraege();
                }


            } else {
                System.out.println("Dieser Menüpunkt existiert nicht");
            }

        }
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
                    enterNewProduct();
                }

                if (intMenueChoice == 2) {
                    changeProductPrice();
                }


            } else {
                System.out.println("Dieser Menüpunkt existiert nicht");
            }

        }

    }


    public static void enterNewContract() {
        boolean inputNotAccepted = true;
        String nameContractAssignee, nameCustomer, addmoreProducts;
        int counter = 0;
        int chosenProduct = 0;
        int[] productRange = new int[produktListe.size()];
        int amountProduct;
        boolean addMoreProductsToContract = true;
        boolean yesOrNoNotEntered = true;

        while (inputNotAccepted) {
            System.out.println("Name des Sachbearbeiters: ");
            nameContractAssignee = userInputisString();
            System.out.println("Name des Kunden: ");
            nameCustomer = userInputisString();
            auftrag neuerAuftrag = new auftrag(nameCustomer, nameContractAssignee);

            while (addMoreProductsToContract) {
                counter = 0;


                System.out.println("Produkt auswahl: ");

                for (produkt p : produktListe) {
                    productRange[counter] = counter + 1;
                    counter++;
                    System.out.println("(" + counter + ") " + produktListe.get(counter - 1).getName());
                }

                chosenProduct = userInputIsInteger();

                while (!userInputIsValidMenueNumber(productRange, chosenProduct)) {
                    System.out.println("Falsche Produktnummer - bitte erneut eingeben:");
                    chosenProduct = userInputIsInteger();
                }


                //TODO: Hinweis, wenn das Produkt bereits als Posten vorhanden ist - dann evtl. die Anzahl korrigieren ?



                System.out.println("Anzahl von " + produktListe.get(chosenProduct - 1).getName() + ", die als Posten aufgenommen werden sollen: ");
                amountProduct = userInputIsInteger();
                posten newPosten = new posten();
                newPosten.addProdukt(produktListe.get(chosenProduct - 1), amountProduct);

                neuerAuftrag.addPosten(newPosten);
                auftragsListe.add(neuerAuftrag);

                System.out.println("Details zum bisherigen Auftrag: ");
                System.out.println("Kunde: "+auftragsListe.get(auftragsListe.size() - 1).getKundenName()+", Sachbearbeiter: "+auftragsListe.get(auftragsListe.size() - 1).getSachbearbeiterName());
                auftragsListe.get(auftragsListe.size() - 1).showDetails();

                System.out.println("Ein weiteres Produkt hinzufügen? (Y/N) ");

                addmoreProducts = userInputisString();

                if ((!addmoreProducts.equals("Y")) || (!addmoreProducts.equals("N"))) {
                    yesOrNoNotEntered = false;
                } else {
                    yesOrNoNotEntered = true;
                }


                while (yesOrNoNotEntered) {
                    if ((!addmoreProducts.equals("Y")) || (!addmoreProducts.equals("N"))) {
                        System.out.println("Bitte nur N oder Y eingeben");
                        System.out.println("Ein weiteres Produkt hinzufügen? (Y/N) ");
                        addmoreProducts = userInputisString();
                        System.out.println(addmoreProducts);
                    } else {
                        yesOrNoNotEntered = false;
                    }
                }


                if (addmoreProducts.equals("Y")) {
                    addMoreProductsToContract = true;

                } else {

                    addMoreProductsToContract = false;
                }


            }
            inputNotAccepted = false;
        }

    }

    public static void changeProductPrice() {
        String stringProduktListe = "";
        int counter = 0;
        boolean inputError = true;
        int[] menueNumberRange = new int[produktListe.size()];
        int intProductChoice;
        double newProductPrice;

        System.out.println("Folgende Produkte sind verfügbar:");

        for (produkt p : produktListe) {
            menueNumberRange[counter] = counter + 1;
            counter++;
            stringProduktListe += "(" + counter + ")" + p.getName() + ",";
        }


        while (inputError) {
            System.out.println(stringProduktListe);
            System.out.println("Bitte die Produktnummer eingeben:");
            intProductChoice = userInputIsInteger();
            if (userInputIsValidMenueNumber(menueNumberRange, intProductChoice)) {
                System.out.println("Alter Preis für " + produktListe.get(intProductChoice - 1).getName() + " ist: " + produktListe.get(intProductChoice - 1).getPreis());
                System.out.println("Neuer Preis soll sein: ");
                newProductPrice = userInputIsDouble();
                produktListe.get(intProductChoice - 1).setPreis(newProductPrice);
                System.out.println("Neuer Preis für " + produktListe.get(intProductChoice - 1).getName() + " ist: " + produktListe.get(intProductChoice - 1).getPreis());
                inputError = false;
            }
        }

        clearSanner();

    }

    public static void enterNewProduct() {
        boolean inputNotAccepted = true;
        String productName = "";
        double productPrice = 0.00;
        produkt newProduct = new produkt(0, "");

        while (inputNotAccepted) {
            boolean isNewProduct = true;
            System.out.println("Namen des Produktes eingeben: ");
            productName = userInputisString();

            while (isNewProduct) {
                if (productNotInProductList(productName)) {
                    newProduct.setName(productName);
                    System.out.println("Preis des Produktes eingeben: ");
                    productPrice = userInputIsDouble();
                    newProduct.setPreis(productPrice);
                    produktListe.add(newProduct);
                    isNewProduct = false;

                } else {
                    System.out.println("Dieses Produkt existiet bereits - bitte andernen Namen verwenden");
                    System.out.println("Namen des Produktes eingeben: ");
                    productName = userInputisString();
                    isNewProduct = true;
                }

            }
            inputNotAccepted = false;
        }
        clearSanner();
    }


    public static void clearSanner() {
        String clearScanner = userInput.nextLine();

    }

    public static void showQuitMenue() {
        System.out.println("Alle Aufträge:");
        ShowAllAuftraege();
        System.out.println("Auf wiedersehen");
    }


    private static boolean productNotInProductList(String prodName) {
        for (produkt prod : produktListe) {
            if (prod.getName().equals(prodName)) {
                return false;
            }
        }
        return true;
    }

    private static boolean userInputIsValidMenueNumber(int[] menueArray, int userInput) {
        for (int intNum : menueArray) {
            if (userInput == intNum)
                return true;
        }
        return false;
    }


    private static String userInputisString() {
        String s1 = "";
        boolean bError = true;
        while (bError) {
            try {
                s1 = userInput.nextLine();
                bError = false;
            } catch (NumberFormatException e) {
                System.out.println("Bitte nur Text eingeben");
            }
        }
        return s1;
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

    private static double userInputIsDouble() {
        double n1 = 0;
        boolean bError = true;
        while (bError) {
            try {
                n1 = userInput.nextDouble();
                bError = false;
            } catch (InputMismatchException e) {
                System.out.println("Falsches Eingabeformat ( 12.34 )");
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
