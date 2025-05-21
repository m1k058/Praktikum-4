import java.util.HashMap;
import java.util.Set;

/**
 * Das ist eine Region. Sie besteht aus mehreren Räumen. Es gibt immer einen Raum in der Region von
 * dem man in eine andere Region reisen kann.
 *
 * @author Michal Kos
 * @version 0.1
 */
public class Region
{
    private String beschreibung;
    private HashMap<String, Region> ausgaenge;        // die Ausgänge dieser Region
    private Raum aktuellerRaum;
    private Raum bahnhofCheck;

    /**
     * Erzeuge eine Region mit einer Beschreibung. Eine Region
     * hat anfangs keine Ausgänge. 
     * Die Räume werden angelegt. Es sind verschiedene Variationen
     * möglich. 
     * @param beschreibung enthält eine Beschreibung in der Form
     *        "in einer Küche" oder "auf einem Sportplatz".
     *        Als zweites wird die Variation der Räume gewählt.
     */
    public Region(String beschreibung, int variation) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<String, Region>();
        raeumeAnlegen(variation);
    }
    
    /**
     * Erzeuge alle Räume und verbinde ihre Ausgänge miteinander.
     * Es sind verschiede variationen verfügbar.
     */
    private void raeumeAnlegen(int variation)
    {
        // standart Test Variation
        if(variation == 0){
            Raum bahnhof, marktplatz, bauamt, feld;
      
            // die Räume erzeugen
            bahnhof = new Raum("am Hauptbahnhof");
            marktplatz = new Raum("am Marktplatz");
            bauamt = new Raum("im Bauamt für Zukunftsenergie (BZEG)");
            feld = new Raum("auf einem Feld");
        
            // die Ausgänge initialisieren
            marktplatz.setzeAusgang("north", bauamt);
            marktplatz.setzeAusgang("east", bahnhof);
            marktplatz.setzeAusgang("south", feld);

            bauamt.setzeAusgang("west", marktplatz);

            bahnhof.setzeAusgang("west", marktplatz);
    
            feld.setzeAusgang("north", marktplatz);
        
            bahnhofCheck = bahnhof;     // zum vergleich         
            aktuellerRaum = bahnhof;  // das Spiel startet am Bahnhof
        }

        else{
            Raum bahnhof, marktplatz, bauamt, feld1, feld2;
      
            // die Räume erzeugen
            bahnhof = new Raum("am Hauptbahnhof");
            marktplatz = new Raum("am Marktplatz");
            bauamt = new Raum("im Bauamt für Zukunftsenergie (BZEG)");
            feld1 = new Raum("auf einem Feld");
            feld2 = new Raum("auf einem Feld");
        
            // die Ausgänge initialisieren
            marktplatz.setzeAusgang("east", bauamt);
            marktplatz.setzeAusgang("west", bahnhof);
            marktplatz.setzeAusgang("north", feld1);

            bauamt.setzeAusgang("west", marktplatz);

            bahnhof.setzeAusgang("east", marktplatz);
            
            feld1.setzeAusgang("north", feld2);
            feld1.setzeAusgang("south", marktplatz);
            feld2.setzeAusgang("south", feld1);
            
            if(variation == 1 || variation == 2)
            {
                Raum  strandMitte, strandOst, strandWest, strandWest2, feld3, feld4, offshore;
                
                strandMitte = new Raum("am Strand");
                strandOst = new Raum("am Strand");
                strandWest = new Raum("am Strand");
                strandWest2 = new Raum("am Strand");
                feld3 = new Raum("auf einem Feld");
                feld4 = new Raum("auf einem Feld");
                offshore = new Raum("am Rand der Offshore-Bauzone");
                    
                strandMitte.setzeAusgang("north", marktplatz);
                strandMitte.setzeAusgang("west", strandWest);
                strandMitte.setzeAusgang("east", strandOst);
                strandOst.setzeAusgang("west", strandMitte);
                strandWest.setzeAusgang("east", strandMitte);
                strandWest.setzeAusgang("west", strandWest2);
                strandWest2.setzeAusgang("east", strandWest);
                
                offshore.setzeAusgang("north", strandWest2);
                
                marktplatz.setzeAusgang("south", strandMitte);
                
                feld3.setzeAusgang("north", feld4);
                feld4.setzeAusgang("south", feld3);                
                feld1.setzeAusgang("west", feld3);
                feld2.setzeAusgang("west", feld4);                
                feld3.setzeAusgang("east", feld1);                
                feld4.setzeAusgang("east", feld2); 
                if(variation == 2){
                    Raum autobahn, marktplatz1;
                    
                    autobahn = new Raum("auf der Autobahn richtung Windhavn");
                    marktplatz1 = new Raum("am Marktplatz");
                    
                    marktplatz.setzeAusgang("west", marktplatz1);
                    marktplatz1.setzeAusgang("north", autobahn);
                    marktplatz1.setzeAusgang("east", marktplatz); 
                    marktplatz1.setzeAusgang("south", strandWest); 
                    marktplatz1.setzeAusgang("west", bahnhof); 
                    
                    autobahn.setzeAusgang("south", marktplatz1);                    
                    bahnhof.setzeAusgang("east", marktplatz1);
                    strandWest.setzeAusgang("south", offshore);
                    strandWest.setzeAusgang("north", marktplatz1);
                    offshore.setzeAusgang("north", strandWest);
                    
                    feld1.setzeAusgang("east", feld3);
                    feld2.setzeAusgang("east", feld4);                
                    feld3.setzeAusgang("west", feld1);                
                    feld4.setzeAusgang("west", feld2);                
                }
                else{
                    strandWest2.setzeAusgang("south", offshore);
                }
            }
                      
            bahnhofCheck = bahnhof;     // zum vergleich         
            aktuellerRaum = bahnhof;  // das Spiel startet am Bahnhof
        }
        
    }
    
    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
     public void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin möchten Sie gehen?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen, den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null) {
            System.out.println("Dort ist keine Tür!");
        }
        else {
            aktuellerRaum = naechsterRaum;
            System.out.println(gibLangeBeschreibung());
        }
    }
    
    /**
     * Definiere einen Ausgang für diese Region.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Region, der über diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Region nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieser Region (die dem Konstruktor
     * übergeben wurde).
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }

    /**
     * Liefere eine lange Beschreibung diese Region, in der Form:
     *     Sie sind in der Küche der Region Hamburg.
     *     Ausgänge: nord west
     * @return eine lange Beschreibung dieser Region.
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind " + aktuellerRaum.gibBeschreibungRaum() + gibBeschreibung() + ".\n" + gibAusgaengeAlsStringRaum();
    }

    /**
     * Liefere eine Zeichenkette, die die Ausgänge dieser Region
     * beschreibt, beispielsweise
     * "Ausgänge: north west".
     * @return eine Beschreibung der Ausgänge dieser Region.
     */
    private String gibAusgaengeAlsStringRaum()
    {
        String ergebnis = "Ausgänge:" + aktuellerRaum.gibAusgaengeAlsString();
        if(amBahnhof())
        {
        ergebnis += gibAusgaengeAlsString();
        }
        return ergebnis;
    }
    
    /**
     * Liefere eine Zeichenkette, die die Ausgänge dieser Region
     * beschreibt, beispielsweise
     * "Ausgänge: north west".
     * @return eine Beschreibung der Ausgänge dieser Region.
     */
    private String gibAusgaengeAlsString()
    {
        String ergebnis = "\nOder fahren sie mit dem Zug nach:";
        Set<String> keys = ausgaenge.keySet();
        for(String ausgang : keys)
            ergebnis += " " + ausgang;
        return ergebnis;
    }

    /**
     * Liefere die Region, die wir erreichen, wenn wir aus dieser Region
     * in die angegebene Richtung gehen. Liefere 'null', wenn in
     * dieser Richtung kein Ausgang ist.
     * @param richtung die Richtung, in die gegangen werden soll.
     * @return die Region in der angegebenen Richtung.
     */
    public Region gibAusgang(String richtung) 
    {
        return ausgaenge.get(richtung);
    }
    
    public boolean amBahnhof()
    {
        if (aktuellerRaum == bahnhofCheck){
            return true;
        }
        else{
        return false;
        }
    }
}
