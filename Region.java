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
    private Raum aktuellerRaum;
    private Raum bahnhofCheck;
    private Raum feldCheck;
    Set<Raum> feldRaeume = new HashSet<>();

    /**
     * Erzeuge eine Region mit einer Beschreibung. Eine Region
     * hat anfangs keine Ausgaenge. 
     * Die Raeume werden angelegt. Es sind verschiedene Variationen
     * mÃ¶glich. 
     * @param beschreibung enthaelt eine Beschreibung in der Form
     *        "in einer Kueche" oder "auf einem Sportplatz".
     *        Als zweites wird die Variation der Raeume gewaehlt.
     */
    public Region(String beschreibung, int variation) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<String, Region>();
        raeumeAnlegen(variation);
    }
    
    /**
     * Erzeuge alle Raeume und verbinde ihre Ausgaenge miteinander.
     * Es sind verschiede variationen verfuegbar.
     */
    private void raeumeAnlegen(int variation)
    {
        // standart Test Variation
        if(variation == 0){
            Raum bahnhof, marktplatz, bauamt, feld;
      

            // die Raeume erzeugen
            bahnhof = new Raum("am Hauptbahnhof", false, false);
            marktplatz = new Raum("am Marktplatz", false, false);
            bauamt = new Raum("im Bauamt fuer Zukunftsenergie (BZEG)", false, false);
            feld = new Raum("auf einem Feld", true, false);
        
            // die Ausgaenge initialisieren
            marktplatz.setzeAusgang("oben", bauamt);
            marktplatz.setzeAusgang("rechts", bahnhof);
            marktplatz.setzeAusgang("unten", feld);

            bauamt.setzeAusgang("links", marktplatz);

            bahnhof.setzeAusgang("links", marktplatz);
    
            feld.setzeAusgang("oben", marktplatz);
        
            bahnhofCheck = bahnhof;     // zum vergleich         
            aktuellerRaum = bahnhof;  // das Spiel startet am Bahnhof
        }

        else{
            Raum bahnhof, marktplatz, bauamt, feld1, feld2;
      
            // die Raeume erzeugen
            bahnhof = new Raum("am Hauptbahnhof", false, false);
            marktplatz = new Raum("am Marktplatz", false, false);
            bauamt = new Raum("im Bauamt für Zukunftsenergie (BZEG)", false, false);
            feld1 = new Raum("auf einem Feld",true, false);
            feld2 = new Raum("auf einem Feld",true, false);
        
            // die Ausgaenge initialisieren
            marktplatz.setzeAusgang("rechts", bauamt);
            marktplatz.setzeAusgang("links", bahnhof);
            marktplatz.setzeAusgang("oben", feld1);

            bauamt.setzeAusgang("links", marktplatz);

            bahnhof.setzeAusgang("rechts", marktplatz);
            
            feld1.setzeAusgang("oben", feld2);
            feld1.setzeAusgang("unten", marktplatz);
            feld2.setzeAusgang("unten", feld1);
            
            // Raeme in Set schreiben zum vergleich
            feldRaeume.add(feld1); 
            feldRaeume.add(feld2);
            if(variation == 1 || variation == 2)
            {
                Raum  strandMitte, strandOst, strandlinks, strandlinks2, feld3, feld4, offshore;
                
                strandMitte = new Raum("am Strand", false, false);
                strandOst = new Raum("am Strand", false, false);
                strandlinks = new Raum("am Strand", false, false);
                strandlinks2 = new Raum("am Strand", false, false);
                feld3 = new Raum("auf einem Feld", true, false);
                feld4 = new Raum("auf einem Feld", true, false);
                offshore = new Raum("am Rand der Offshore-Bauzone", true, false);
                    
                strandMitte.setzeAusgang("oben", marktplatz);
                strandMitte.setzeAusgang("links", strandlinks);
                strandMitte.setzeAusgang("rechts", strandOst);
                strandOst.setzeAusgang("links", strandMitte);
                strandlinks.setzeAusgang("rechts", strandMitte);
                strandlinks.setzeAusgang("links", strandlinks2);
                strandlinks2.setzeAusgang("rechts", strandlinks);
                
                offshore.setzeAusgang("oben", strandlinks2);
                
                marktplatz.setzeAusgang("unten", strandMitte);
                
                feld3.setzeAusgang("oben", feld4);
                feld4.setzeAusgang("unten", feld3);                
                feld1.setzeAusgang("links", feld3);
                feld2.setzeAusgang("links", feld4);                
                feld3.setzeAusgang("rechts", feld1);                
                feld4.setzeAusgang("rechts", feld2); 
                
                feldRaeume.add(feld3);
                feldRaeume.add(feld4);
                feldRaeume.add(offshore);
                
                if(variation == 2){
                    Raum autobahn, marktplatz1;
                    
                    autobahn = new Raum("auf der Autobahn richtung Windhavn", false, false);
                    marktplatz1 = new Raum("am Marktplatz", false, false);
                    
                    marktplatz.setzeAusgang("links", marktplatz1);
                    marktplatz1.setzeAusgang("oben", autobahn);
                    marktplatz1.setzeAusgang("rechts", marktplatz); 
                    marktplatz1.setzeAusgang("unten", strandlinks); 
                    marktplatz1.setzeAusgang("links", bahnhof); 
                    
                    autobahn.setzeAusgang("unten", marktplatz1);                    
                    bahnhof.setzeAusgang("rechts", marktplatz1);
                    strandlinks.setzeAusgang("unten", offshore);
                    strandlinks.setzeAusgang("oben", marktplatz1);
                    offshore.setzeAusgang("oben", strandlinks);
                    
                    feld1.setzeAusgang("rechts", feld3);
                    feld2.setzeAusgang("rechts", feld4);                
                    feld3.setzeAusgang("links", feld1);                
                    feld4.setzeAusgang("links", feld2);                
                }
                else{
                    strandlinks2.setzeAusgang("unten", offshore);
                }
            }
                      
           bahnhofCheck = bahnhof;     // zum vergleich       
           aktuellerRaum = bahnhof;    // das Spiel startet am Bahnhof
        }
        

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
            System.out.println("Wohin mÃ¶chten Sie gehen?");
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
