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
        Region region1, region2, region3, region4;
      
        // die Regionen erzeugen
        region1 = new Region("in Region1");
        region2 = new Region("in Region2");
        region3 = new Region("in Region3");
        region4 = new Region("in Region4");
        
        // die Ausgänge initialisieren
        region1.setzeAusgang("east", region2);
        region1.setzeAusgang("south", region3);
        
        region2.setzeAusgang("west", region1);
        region2.setzeAusgang("south", region4);
        
        region3.setzeAusgang("east", region4);
        region3.setzeAusgang("north", region1);
        
        region4.setzeAusgang("west", region3);
        region4.setzeAusgang("north", region2);
        
        aktuelleRegion = region1;  // das Spiel startet in region1
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
        System.out.println("Das Neuland ist ein sehr gut und weit unterentwickeltes");
        System.out.println("Land aber es braucht deine Hilfe um wieder auf die Beine zu kommen!");
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
                
            case QUIT:
                moechteBeenden = beenden(befehl);
                break;
        }
        // ansonsten: Befehl nicht erkannt.
        return moechteBeenden;
    }

    // Implementierung der Benutzerbefehle:

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlswörter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Sie haben sich verlaufen. Sie sind allein.");
        System.out.println("Sie irren auf dem Unigelände herum.");
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
        parser.zeigeBefehle();
    }

    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    

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
