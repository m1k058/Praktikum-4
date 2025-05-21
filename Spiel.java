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
    
        
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        regionAnlegen();
        parser = new Parser();
    }

    /**
     * Erzeuge alle Regionen und verbinde ihre Ausgänge miteinander.
     */
    private void regionAnlegen()
    {
        Region Voltavia, Wattental, Windhain, Kraftia, SolariaWest, SolariaOst, Westseekueste, Südseekueste, Hauptstadt, Windhavn;
      
        // die Regionen erzeugen
        Voltavia = new Region(" in Voltavia", 0);
        Wattental = new Region(" in Wattental", 0);
        Windhain = new Region(" in Windhain", 0);
        Kraftia = new Region(" in Kraftia", 0);
        SolariaWest = new Region(" in Solaria-West", 0);
        SolariaOst = new Region(" in Solaria-Ost", 0);
        Westseekueste = new Region(" in Westseeküste", 0);
        Südseekueste = new Region(" in Südseeküste", 0);
        Hauptstadt = new Region(" in Hauptstadt", 0);
        Windhavn = new Region(" in Windhavn", 0);
        
        // die Ausgänge initialisieren
        Voltavia.setzeAusgang("east", Wattental);
        Voltavia.setzeAusgang("south", Windhain);
        
        Wattental.setzeAusgang("west", Kraftia);
        Wattental.setzeAusgang("south", Voltavia);
        
        Windhain.setzeAusgang("east", Kraftia);
        Windhain.setzeAusgang("south", SolariaWest);
        Windhain.setzeAusgang("north", Voltavia);
        
        Kraftia.setzeAusgang("west", Windhain);
        Kraftia.setzeAusgang("south",SolariaOst );
        Kraftia.setzeAusgang("north", Wattental);
        
        SolariaWest.setzeAusgang("east", SolariaOst);
        SolariaWest.setzeAusgang("south", Westseekueste);
        SolariaWest.setzeAusgang("north", Windhain);
        
        SolariaOst.setzeAusgang("west", SolariaWest);
        SolariaOst.setzeAusgang("south", Südseekueste);
        SolariaOst.setzeAusgang("north", Kraftia);
        
        Westseekueste.setzeAusgang("east", Südseekueste);
        Westseekueste.setzeAusgang("north", SolariaWest);
        
        Südseekueste.setzeAusgang("west", Westseekueste);
        Südseekueste.setzeAusgang("north", SolariaOst);
        
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
                aktuelleRegion.wechsleRaum(befehl);
                break;
                
            case TRAIN:
                 wechsleRegion(befehl);
                break;
                
            case QUIT:
                moechteBeenden = beenden(befehl);
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
            }
        }
        else {
            System.out.println("Hier fahren keine Züge!");
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
