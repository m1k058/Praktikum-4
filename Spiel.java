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
 * @author  Michael KÃ¶lling, David J. Barnes, Michal Kos und Cedrik Wilke
 * @version 21.05.2025
 */

class Spiel 
{
    private Parser parser;
    private Spieler spieler;
    
    private Spielumgebung spielumgebung;
    private Region aktuelleRegion;
    private Raum aktuellerRaum;

    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        spieler = new Spieler(5, 0, 100);
        parser = new Parser();
        try {
            spielumgebung = new Spielumgebung("map_v2.json");
        } catch (Exception e) {
            // Fehlerbehandlung wenn die Datei nicht gefunden wird oder fehlerhaft ist
            System.err.println("Kritischer Fehler beim Laden der Spielumgebung: " + e.getMessage());
            System.err.println("Das Spiel kann nicht gestartet werden. Bitte überprüfe die Datei 'map_v1.json'.");
        }
        aktuelleRegion = spielumgebung.gibRegion("Voltavia");
        aktuellerRaum = aktuelleRegion.gibRaum("Bahnhof");
        
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
     * Einen Begruessungstext fuer den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen im Neuland Alpha!");
        System.out.println("Das Neuland ist ein weit unterentwickeltes Land und benÃ¶tigt dringend deine Hilfe!");
        System.out.println("Nur du kannst das Land noch retten!!!");
        System.out.println("Tippen sie '" + Befehlswort.HELP + "', wenn Sie Hilfe brauchen.");
        spieler.ausgeben();
        System.out.println();
        raumInfoAusgeben();
    }
    
    /**
     * Gibt die Informationen über den aktuellen Raum und die aktuelle Region aus.
     */
    private void raumInfoAusgeben() {
        System.out.println();
        if (aktuellerRaum == null || aktuelleRegion == null) {
            System.out.println("Fehler: Aktueller Raum oder Region nicht definiert.");
            return;
        }
        System.out.println("Du befindest dich " + aktuellerRaum.gibBeschreibung() + //
                           " in der Region '" + aktuelleRegion.gibBeschreibung() + "'."); //
        System.out.println(aktuellerRaum.gibRaumAusgaengeAlsString()); //

        // Zeige Regionsausgänge nur, wenn der Raum ein TravelRaum ist
        if (aktuellerRaum instanceof TravelRaum) {
            // Prüfen ob es sich um einen Bahnhof handelt
             if (aktuellerRaum.gibKategorie() == Raumkategorie.BAHNHOF) { //
                System.out.println("Von dieser Region '" + aktuelleRegion.gibBeschreibung() + //
                                   "' kannst du reisen nach:" + aktuelleRegion.gibRegionAusgaengeAlsString()); //
            }
        }
        // Wenn aktuellerRaum ein BauRaum ist, zeige gebaute Anlagen
        if (aktuellerRaum instanceof BauRaum) {
            BauRaum bauRaum = (BauRaum) aktuellerRaum;
            System.out.println(bauRaum.gibAnlagenString()); //
        }
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
                wechsleRaumInnerhalbRegion(befehl);
                break;

            case DRIVE:
                wechsleInAndereRegion(befehl);
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
     * aus, sowie eine Liste der BefehlswÃ¶rter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Sie sind die letzte Hoffnung fuer das Land!!!");        
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfuegung:");
        parser.zeigeBefehle();
    }

     /**
     * Versuche, innerhalb der aktuellen Region in einen anderen Raum zu gehen.
     */
    private void wechsleRaumInnerhalbRegion(Befehl befehl) {
        if (!befehl.hatZweitesWort()) {
            System.out.println("Wohin gehen?");
            return;
        }
        String richtung = befehl.gibZweitesWort();
        Raum naechsterRaum = aktuellerRaum.gibAusgangRaum(richtung);

        if (naechsterRaum == null) {
            System.out.println("Dort geht es nicht lang!");
        } else {
            aktuellerRaum = naechsterRaum;
            // Spielerstats (Geld, Ansehen) ändern sich nicht beim Raumwechsel innerhalb einer Region
            spieler.ausgeben(); //
            raumInfoAusgeben();
        }
    }
    
    /**
     * Versuche, mit einem Transportmittel in eine andere Region zu wechseln.
     * Kommt mit "Zug" im Raum "Bahnhof" der Zielregion an.
     * Kommt mit "Auto" im Raum "Autobahn" der Zielregion an.
     */
    private void wechsleInAndereRegion(Befehl befehl) {
        if (!(aktuellerRaum instanceof TravelRaum)) {
            System.out.println("Von hier ("+ aktuellerRaum.gibBeschreibung() +") aus kannst du nicht reisen.");
            return;
        }
        Raum alterRaum =  aktuellerRaum;

        if (!befehl.hatZweitesWort()) { //
            System.out.println("Wohin möchtest du fahren?");
            System.out.println("Mögliche Regionen:" + aktuelleRegion.gibRegionAusgaengeAlsString()); //
            return;
        }
        Region naechsteRegion = spielumgebung.gibRegion(befehl.gibZweitesWort());

        if (naechsteRegion == null) {
            System.out.println("Dorthin gibt es aktuell keine Verbindung von dieser Region.");
        } 
        else {
            Region alteRegion = aktuelleRegion;
            aktuelleRegion = naechsteRegion; // Wechsel zur neuen Region

            Raum ankunftsRaum = null;
            String zielRaumName = null;

            if (alterRaum.gibKategorie() == Raumkategorie.BAHNHOF) {
                ankunftsRaum = aktuelleRegion.gibRaum("Bahnhof");
            } else if (alterRaum.gibKategorie() == Raumkategorie.AUTOBAHN) {
                ankunftsRaum = aktuelleRegion.gibRaum("Autobahn");
            }

            if (ankunftsRaum != null) {
                aktuellerRaum = ankunftsRaum;
                System.out.println("Du reist von " + alteRegion.gibBeschreibung() + " nach " + aktuelleRegion.gibBeschreibung() + ".");
                spieler.aendereGeld(spieler.gibEinkommen());
                spieler.ausgeben();
                raumInfoAusgeben();
            } else {
                System.out.println("Reise fehlgeschlagen. Du bleibst in " + alteRegion.gibBeschreibung() + ".");
                aktuelleRegion = alteRegion; // Zurück zur alten Region, da Ankunftsort ungültig
            }
        }
    }

    private void baueAnlage(Befehl befehl) { //
        if (!(aktuellerRaum instanceof BauRaum)) {
            System.out.println("Hier (" + aktuellerRaum.gibBeschreibung() + ") können keine Anlagen gebaut werden."); //
            return;
        }
        BauRaum bauRaum = (BauRaum) aktuellerRaum;

        if (!befehl.hatZweitesWort()) { //
            System.out.println("Welche Art von Anlage soll gebaut werden? Verfügbar: 'solar' oder 'wind'."); //
            System.out.println(bauRaum.gibAnlagenString()); // Zeige aktuelle Belegung
            return;
        }
        String anlagenArt = befehl.gibZweitesWort(); //

        // TODO: Kosten, Einkommens- und Ansehensänderungen hier definieren oder aus Raumkategorie/Konstanten beziehen.
        // Die Werte aus dem alten Spiel (-1 Muenze, +1 Einkommen etc.) sind hier als Platzhalter.
        int kosten = 0;
        int einkommenPlus = 0;
        int ansehenPlus = 0;
        boolean erfolgreich = false;

        if (anlagenArt.equals("solar")) { //
            kosten = 2; // Beispiel Kosten
            einkommenPlus = 2; // Beispiel Einkommen
            ansehenPlus = 2; // Beispiel Ansehen
            if (spieler.gibGeld() >= kosten) { //
                if (bauRaum.bauSolar()) { //
                    System.out.println("Eine Solaranlage wurde " + bauRaum.gibBeschreibung() + " gebaut."); //
                    erfolgreich = true;
                } else {
                    System.out.println("Solaranlage konnte nicht gebaut werden. " + bauRaum.gibAnlagenString()); //
                }
            } else {
                System.out.println("Nicht genug Geld für eine Solaranlage. Benötigt: " + kosten + " Münzen.");
            }
        } else if (anlagenArt.equals("wind")) { //
            kosten = 1; // Beispiel Kosten
            einkommenPlus = 1; // Beispiel Einkommen
            ansehenPlus = 1; // Beispiel Ansehen
             if (spieler.gibGeld() >= kosten) { //
                if (bauRaum.bauWind()) { //
                    System.out.println("Eine Windkraftanlage wurde " + bauRaum.gibBeschreibung() + " gebaut."); //
                    erfolgreich = true;
                } else {
                    System.out.println("Windkraftanlage konnte nicht gebaut werden. " + bauRaum.gibAnlagenString()); //
                }
            } else {
                System.out.println("Nicht genug Geld für eine Windkraftanlage. Benötigt: " + kosten + " Münzen.");
            }
        } else {
            System.out.println("Es können nur 'solar'- oder 'wind'-Anlagen gebaut werden."); //
        }

        if(erfolgreich) {
            spieler.aendereGeld(-kosten); //
            spieler.aendereEinkommen(einkommenPlus); //
            spieler.aendereAnsehen(ansehenPlus); //
            System.out.println("Du erhältst nun " + einkommenPlus + " zusätzliche Münze(n) Einkommen und dein Ansehen steigt um " + ansehenPlus + ".");
            spieler.ausgeben(); //
            raumInfoAusgeben();
        }
    }

    /**
     * "quit" wurde eingegeben. Ãœberpruefe den Rest des Befehls,
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
