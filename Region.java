import java.util.HashMap;
import java.util.Set;

/**
 * Das ist eine Region. Sie besteht aus mehreren R�umen. Es gibt immer einen Raum in der Region von
 * dem man in eine andere Region reisen kann.
 *
 * @author Michal Kos
 * @version 0.1
 */
public class Region
{
    private String beschreibung;
    private HashMap<String, Region> ausgaenge;        // die Ausg�nge dieser Region
    private Raum aktuellerRaum;
    private Raum bahnhofCheck;
    private Raum feldCheck;

    /**
     * Erzeuge eine Region mit einer Beschreibung. Eine Region
     * hat anfangs keine Ausg�nge.
     * @param beschreibung enth�lt eine Beschreibung in der Form
     *        "in einer K�che" oder "auf einem Sportplatz".
     */
    public Region(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<String, Region>();
        raeumeAnlegen();
    }
    
    /**
     * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum bahnhof, marktplatz, regionkanzlei, autobahn, feld;
      
        // die R�ume erzeugen
        bahnhof = new Raum("am Hauptbahnhof", false, false);
        marktplatz = new Raum("am Marktplatz", false, false);
        regionkanzlei = new Raum("in der Regionkanzlei", false, false);
        autobahn = new Raum("auf der Autobahn", false, false);
        feld = new Raum("auf einem Feld", true, false);
        
        // die Ausg�nge initialisieren
        marktplatz.setzeAusgang("oben", regionkanzlei);
        marktplatz.setzeAusgang("rechts", bahnhof);
        marktplatz.setzeAusgang("unten", autobahn);

        regionkanzlei.setzeAusgang("unten", marktplatz);

        bahnhof.setzeAusgang("links", marktplatz);

        autobahn.setzeAusgang("oben", marktplatz);
        autobahn.setzeAusgang("unten", feld);

        feld.setzeAusgang("oben", autobahn);
        
        bahnhofCheck = bahnhof;     // zum vergleich       
        feldCheck = feld;           // zum vergleich   
        aktuellerRaum = bahnhof;    // das Spiel startet am Bahnhof
    }
    
    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    public boolean wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin m�chten Sie gehen?");
            return false;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen, den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null) {
            System.out.println("Dort ist keine T�r!");
            return false;
        }
        else {
            aktuellerRaum = naechsterRaum;
            System.out.println(gibLangeBeschreibung());
            return true;
        }
    }
    
    /**
     * Definiere einen Ausgang f�r diese Region.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Region, der �ber diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Region nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieser Region (die dem Konstruktor
     * �bergeben wurde).
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }
    
    /**
     * @return die kurze Beschreibung dieser Region (die dem Konstruktor
     * �bergeben wurde).
     */
    public Raum gibAktuellerRaum()
    {
        return aktuellerRaum;
    }

    /**
     * Liefere eine lange Beschreibung diese Region, in der Form:
     *     Sie sind in der K�che der Region Hamburg.
     *     Ausg�nge: nord west
     * @return eine lange Beschreibung dieser Region.
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind " + aktuellerRaum.gibBeschreibungRaum() + gibBeschreibung() + ".\n" + gibAusgaengeAlsStringRaum();
    }

    /**
     * Liefere eine Zeichenkette, die die Ausg�nge dieser Region
     * beschreibt, beispielsweise
     * "Ausg�nge: north west".
     * @return eine Beschreibung der Ausg�nge dieser Region.
     */
    private String gibAusgaengeAlsStringRaum()
    {
        String ergebnis = "Ausg�nge:" + aktuellerRaum.gibAusgaengeAlsString();
        if(amBahnhof())
        {
        ergebnis += gibAusgaengeAlsString();
        }
        return ergebnis;
    }
    
    /**
     * Liefere eine Zeichenkette, die die Ausg�nge dieser Region
     * beschreibt, beispielsweise
     * "Ausg�nge: north west".
     * @return eine Beschreibung der Ausg�nge dieser Region.
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
    
    public boolean amFeld()
    {
        if (aktuellerRaum == feldCheck){
            return true;
        }
        else{
        return false;
        }
    }
}
