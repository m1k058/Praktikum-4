import java.util.HashMap;
import java.util.Set;


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
    private HashMap<String, Raum> raeume; 

    /**
     * Erzeuge eine Region mit einer Beschreibung. Eine Region
     * hat anfangs keine Ausgaenge und Raeume.
     *  
     * @param beschreibung enthaelt eine Beschreibung in der Form
     *        "in Hamburg" oder "an der Küste".
     */
    public Region(String beschreibung, HashMap<String, Raum> raeume) 
    {
        this.beschreibung = beschreibung;        
        this.raeume = raeume;
        ausgaenge = new HashMap<String, Region>();
    }

    /**
     * Gibt eine Referenz auf den Raum mit dem ensprechenden Namen
     * aus.
     *
     * @param  Name des Raumes
     * @return    Referenz auf den Raum
     */
    public Raum getRaum(String raumName) {
        return raeume.get(raumName);
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
    public String gibRegionAusgaengeAlsString()
    {
        String ergebnis = "";
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
     * @return die Region in der angegebenen Richtung oder null.
     */
    public Region gibAusgangRegion(String richtung) {
        return ausgaenge.get(richtung);
    }
    
    /**
     * @return die kurze Beschreibung dieser Region (die dem Konstruktor
     * uebergeben wurde).
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }
}
