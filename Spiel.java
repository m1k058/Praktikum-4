/**
 *  Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul".
 *  "Die Welt von Zuul" ist ein sehr einfaches, textbasiertes
 *  Adventure-Game. Ein Spieler kann sich in einer Umgebung bewegen,
 *  mehr nicht. Das Spiel sollte auf jeden Fall ausgebaut werden,
 *  damit es interessanter wird!
 * 
 *  Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und
 *  an ihr die Methode "spielen" aufgerufen werden.
 * 
 *  Diese Instanz dieser Klasse erzeugt und initialisiert alle
 *  anderen Objekte der Anwendung: Sie legt alle Räume und einen
 *  Parser an und startet das Spiel. Sie wertet auch die Befehle
 *  aus, die der Parser liefert und sorgt für ihre Ausführung.
 * 
 * @author  Michael Kölling, David J. Barnes, Michal Kos und Cedrik Wilke
 * @version 21.05.2025
 */

class Spiel 
{
    private Parser parser;
    private Region aktuelleRegion;
    private Spieler spieler;

    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        regionAnlegen();
        spieler = new Spieler(5, 0, 100);
        parser = new Parser();
    }

    /**
     * Erzeuge alle Regionen und verbinde ihre Ausgänge miteinander.
     */
    private void regionAnlegen()
    {
        Region Voltavia, Wattental, Windhain, Kraftia, SolariaWest, SolariaOst, Westseekueste, Südseekueste, Hauptstadt, Windhavn;
        
        // die Regionen erzeugen
        Voltavia = new Region(" in Voltavia");
        Wattental = new Region(" in Wattental");
        Windhain = new Region(" in Windhain");
        Kraftia = new Region(" in Kraftia");
        SolariaWest = new Region(" in Solaria-West");
        SolariaOst = new Region(" in Solaria-Ost");
        Westseekueste = new Region(" in Westseeküste");
        Südseekueste = new Region(" in Südseeküste");
        Hauptstadt = new Region(" in Hauptstadt");
        Windhavn = new Region(" in Windhavn");

        // die Ausgänge initialisieren
        Voltavia.setzeAusgang("rechts", Wattental);
        Voltavia.setzeAusgang("unten", Windhain);

        Wattental.setzeAusgang("links", Kraftia);
        Wattental.setzeAusgang("unten", Voltavia);

        Windhain.setzeAusgang("rechts", Kraftia);
        Windhain.setzeAusgang("unten", SolariaWest);
        Windhain.setzeAusgang("oben", Voltavia);

        Kraftia.setzeAusgang("links", Windhain);
        Kraftia.setzeAusgang("unten",SolariaOst );
        Kraftia.setzeAusgang("oben", Wattental);

        SolariaWest.setzeAusgang("rechts", SolariaOst);
        SolariaWest.setzeAusgang("unten", Westseekueste);
        SolariaWest.setzeAusgang("oben", Windhain);

        SolariaOst.setzeAusgang("links", SolariaWest);
        SolariaOst.setzeAusgang("unten", Südseekueste);
        SolariaOst.setzeAusgang("oben", Kraftia);

        Westseekueste.setzeAusgang("rechts", Südseekueste);
        Westseekueste.setzeAusgang("oben", SolariaWest);

        Südseekueste.setzeAusgang("links", Westseekueste);
        Südseekueste.setzeAusgang("oben", SolariaOst);

        aktuelleRegion = Südseekueste;  // das Spiel startet in Südseekueste
    }

    /**
     * Die Hauptmethode zum Spielen. Läuft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und führen sie aus, bis das Spiel beendet wird.

        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Danke für dieses Spiel. Auf Wiedersehen.");
    }

    /**
     * Einen Begrüßungstext für den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen im Neuland Alpha!");
        System.out.println("Das Neuland ist ein weit unterentwickeltes Land und benötigt dringend deine Hilfe!");
        System.out.println("Nur du kannst das Land noch retten!!!");
        System.out.println("Tippen sie '" + Befehlswort.HELP + "', wenn Sie Hilfe brauchen.");
        spieler.ausgeben();
        System.out.println();
        System.out.println(aktuelleRegion.gibLangeBeschreibung());
    }

    /**
     * Verarbeite einen gegebenen Befehl (führe ihn aus).
     * @param befehl Der zu verarbeitende Befehl.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        Befehlswort befehlswort = befehl.gibBefehlswort();

        switch(befehlswort) { 
            case UNKNOWN:
                System.out.println("Ich weiss nicht, was Sie meinen...");
                break;

            case HELP:
                hilfstextAusgeben();
                break;

            case GO:
                if (aktuelleRegion.wechsleRaum(befehl)) {
                    spieler.ausgeben();
                }
                break;

            case TRAIN:
                wechsleRegion(befehl);
                break;

            case QUIT:
                moechteBeenden = beenden(befehl);
                break;

            case BUILD:
                baueAnlage(befehl);
                break;
        }
        // ansonsten: Befehl nicht erkannt.
        return moechteBeenden;
    }

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlswörter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Sie sind die letzte Hoffnung für das Land!!!");        
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
        parser.zeigeBefehle();
    }

    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt 
     * und der Spieler am Bahnhof ist, wechsele in die neue Region,
     * ansonsten gib eine Fehlermeldung aus.
     */
    private void wechsleRegion(Befehl befehl) 
    {
        if(aktuelleRegion.amBahnhof()){
            if(!befehl.hatZweitesWort()) {
                // Gibt es kein zweites Wort, wissen wir nicht, wohin...
                System.out.println("Wohin möchten sie mit dem Zug fahren?");
                return;
            }

            String richtung = befehl.gibZweitesWort();

            // Wir versuchen, die Region zu verlassen.
            Region naechsteRegion = aktuelleRegion.gibAusgang(richtung);

            if (naechsteRegion == null) {
                System.out.println("Dort hin gibt es aktuell keine Verbindung.");
            }
            else {
                aktuelleRegion = naechsteRegion;
                System.out.println(aktuelleRegion.gibLangeBeschreibung());
                spieler.aendereGeld(spieler.gibEinkommen());
                spieler.ausgeben();
            }
        }
        else {
            System.out.println("Hier fahren keine Züge!");
        }
    }

    private void baueAnlage(Befehl befehl) {
        if(aktuelleRegion.amFeld()){
            Raum aktuellerRaum = aktuelleRegion.gibAktuellerRaum();
            if(!aktuellerRaum.gibBebaut()) {
                if(!befehl.hatZweitesWort()) {
                    // Gibt es kein zweites Wort, wissen wir nicht, was gebaut werden soll...
                    System.out.println("Welche Art von Anlage soll gebaut werden?");
                    return;
                }

                String anlagenArt = befehl.gibZweitesWort();

                if (anlagenArt.equals("wind")) {
                    System.out.println("Es wurde eine Windkraftanlage" + aktuelleRegion.gibBeschreibung() + 
                    " gebaut (-1 Münze).");
                    System.out.println("Du erhältst nun immer 1 Münze, wenn du mit einem Zug fährst.");
                    aktuellerRaum.setzeBebaut(true);
                    spieler.aendereGeld(-1);
                    spieler.aendereEinkommen(1);
                    spieler.aendereAnsehen(1);
                } else if (anlagenArt.equals("solar")) {
                    System.out.println("Es wurde eine Solaranlage" + aktuelleRegion.gibBeschreibung() + 
                    " gebaut (-2 Münzen).");
                    System.out.println("Du erhältst nun immer 2 Münzen, wenn du mit einem Zug fährst.");
                    aktuellerRaum.setzeBebaut(true);
                    spieler.aendereGeld(-2);
                    spieler.aendereEinkommen(2);
                    spieler.aendereAnsehen(2);
                }
                else {
                    System.out.println("Es können Windkraftanlagen (Befehl 'wind') " +
                    "oder Solaranlagen (Befehl 'solar') gebaut werden");
                }

            }
            else {
                System.out.println("Hier wurde schon eine Anlage gebaut!");
            }
        }
        else {
            System.out.println("Eine Anlage kann nur auf einem freien Feld gebaut werden!");
        }
    }

    /**
     * "quit" wurde eingegeben. Überprüfe den Rest des Befehls,
     * ob das Spiel wirklich beendet werden soll.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else {
            return true;  // Das Spiel soll beendet werden.
        }
    }
}
