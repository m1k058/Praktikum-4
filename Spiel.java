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
 *  anderen Objekte der Anwendung: Sie legt alle Raeume und einen
 *  Parser an und startet das Spiel. Sie wertet auch die Befehle
 *  aus, die der Parser liefert und sorgt fuer ihre Ausfuehrung.
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
     * Erzeuge alle Regionen und verbinde ihre Ausgaenge miteinander.
     */
    private void regionAnlegen()
    {
        Region Voltavia, Wattental, Windhain, Kraftia, SolariaWest, SolariaOst, Westseekueste, Suedseekueste, Hauptstadt, Windhavn;
      
        // die Regionen erzeugen
        Voltavia = new Region(" in Voltavia", 0);
        Wattental = new Region(" in Wattental", 0);
        Windhain = new Region(" in Windhain", 0);
        Kraftia = new Region(" in Kraftia", 0);
        SolariaWest = new Region(" in Solaria-West", 0);
        SolariaOst = new Region(" in Solaria-Ost", 0);
        Westseekueste = new Region(" an der Westseekueste", 1);
        Suedseekueste = new Region(" an der Suedseekueste", 2);
        Hauptstadt = new Region(" in der Hauptstadt", 0);
        Windhavn = new Region(" in Windhavn", 0);
        
        // die Ausgaenge initialisieren
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
        SolariaOst.setzeAusgang("unten", Suedseekueste);
        SolariaOst.setzeAusgang("oben", Kraftia);

        Westseekueste.setzeAusgang("rechts", Suedseekueste);
        Westseekueste.setzeAusgang("oben", SolariaWest);

        Suedseekueste.setzeAusgang("links", Westseekueste);
        Suedseekueste.setzeAusgang("oben", SolariaOst);
        
        aktuelleRegion = Suedseekueste;  // das Spiel startet in Suedseekueste

    }

    /**
     * Die Hauptmethode zum Spielen. Laeuft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und fuehren sie aus, bis das Spiel beendet wird.

        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Danke fuer dieses Spiel. Auf Wiedersehen.");
    }

    /**
     * Einen Begrueßungstext fuer den Spieler ausgeben.
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
     * Verarbeite einen gegebenen Befehl (fuehre ihn aus).
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
        System.out.println("Sie sind die letzte Hoffnung fuer das Land!!!");        
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfuegung:");
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
            System.out.println("Hier fahren keine Zuege!");
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
                    " gebaut (-1 Muenze).");
                    System.out.println("Du erhaeltst nun immer 1 Muenze, wenn du mit einem Zug faehrst.");
                    aktuellerRaum.setzeBebaut(true);
                    spieler.aendereGeld(-1);
                    spieler.aendereEinkommen(1);
                    spieler.aendereAnsehen(1);
                } else if (anlagenArt.equals("solar")) {
                    System.out.println("Es wurde eine Solaranlage" + aktuelleRegion.gibBeschreibung() + 
                    " gebaut (-2 Muenzen).");
                    System.out.println("Du erhaeltst nun immer 2 Muenzen, wenn du mit einem Zug faehrst.");
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
     * "quit" wurde eingegeben. Überpruefe den Rest des Befehls,
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
