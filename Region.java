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
        if(variation == 0){
            Raum bahnhof, marktplatz, regionkanzlei, autobahn, feld;
      
            // die Räume erzeugen
            bahnhof = new Raum("am Hauptbahnhof");
            marktplatz = new Raum("am Marktplatz");
            regionkanzlei = new Raum("in der Regionkanzlei");
            autobahn = new Raum("auf der Autobahn");
            feld = new Raum("auf einem Feld");
        
            // die Ausgänge initialisieren
            marktplatz.setzeAusgang("north", regionkanzlei);
            marktplatz.setzeAusgang("east", bahnhof);
            marktplatz.setzeAusgang("south", autobahn);

            regionkanzlei.setzeAusgang("west", marktplatz);

            bahnhof.setzeAusgang("west", marktplatz);

            autobahn.setzeAusgang("north", marktplatz);
            autobahn.setzeAusgang("south", feld);
    
            feld.setzeAusgang("north", autobahn);
        
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
