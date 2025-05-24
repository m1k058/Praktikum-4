import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Das ist eine Region. Sie besteht aus mehreren Raeumen. Es gibt immer einen Raum in der Region von
 * dem man in eine andere Region reisen kann.
 *
 * @author Michal Kos
 * @version 0.1
 */
public class Region
{
    private String beschreibung;
    private HashMap<String, Region> ausgaenge;        // die Ausgaenge dieser Region
    private Set<Raum> raeume;

    /**
     * Erzeuge eine Region mit einer Beschreibung. Eine Region
     * hat anfangs keine Ausgaenge und Raeume.
     *  
     * @param beschreibung enthaelt eine Beschreibung in der Form
     *        "in Hamburg" oder "an der Küste".
     */
    public Region(String beschreibung, Set<Raum> raeume) 
    {
        this.beschreibung = beschreibung;        
        this.raeume = raeume;
        ausgaenge = new HashMap<String, Region>();
    }
    
    /**
     * Definiere einen Ausgang für diese Region.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Region, der über diesen Ausgang erreicht wird
     */
    public void setzeAusgangRegion(String richtung, Region nachbar)
    {
        ausgaenge.put(richtung, nachbar);
    }
    
    /**
     * Liefere eine Zeichenkette, die die Ausgänge dieser Region
     * beschreibt, beispielsweise
     * "Reise nach: north west".
     * @return eine Beschreibung der Ausgänge dieses Raumes.
     */
    public String gibAusgaengeAlsString()
    {
        String ergebnis = "";
        Set<String> keys = ausgaenge.keySet();
        for(String ausgang : keys)
            ergebnis += " " + ausgang;
        return ergebnis;
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
            System.out.println("Wohin mÃƒÂ¶chten Sie gehen?");
            return false;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen, den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null) {
            System.out.println("Dort ist keine Tuer!");
            return false;
        }
        else {
            aktuellerRaum = naechsterRaum;
            System.out.println(gibLangeBeschreibung());
            return true;
        }
    }
    
    /**
     * Definiere einen Ausgang fuer diese Region.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Region, der ueber diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Region nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieser Region (die dem Konstruktor
     * uebergeben wurde).
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }
    
    /**
     * @return die kurze Beschreibung dieser Region (die dem Konstruktor
     * uebergeben wurde).
     */
    public Raum gibAktuellerRaum()
    {
        return aktuellerRaum;
    }

    /**
     * Liefere eine lange Beschreibung diese Region, in der Form:
     *     Sie sind in der Kueche der Region Hamburg.
     *     Ausgaenge: nord links
     * @return eine lange Beschreibung dieser Region.
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind " + aktuellerRaum.gibBeschreibungRaum() + gibBeschreibung() + ".\n" + gibAusgaengeAlsStringRaum();
    }

    /**
     * Liefere eine Zeichenkette, die die Ausgaenge dieser Region
     * beschreibt, beispielsweise
     * "Ausgaenge: oben links".
     * @return eine Beschreibung der Ausgaenge dieser Region.
     */
    private String gibAusgaengeAlsStringRaum()
    {
        String ergebnis = "Ausgaenge:" + aktuellerRaum.gibAusgaengeAlsString();
        if(amBahnhof())
        {
        ergebnis += gibAusgaengeAlsString();
        }
        return ergebnis;
    }
    
    /**
     * Liefere eine Zeichenkette, die die Ausgaenge dieser Region
     * beschreibt, beispielsweise
     * "Ausgaenge: oben links".
     * @return eine Beschreibung der Ausgaenge dieser Region.
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
        if (feldRaeume.contains(aktuellerRaum)){
            return true;
        }
        else{
        return false;
        }
    }
}
