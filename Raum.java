import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Diese Klasse modelliert Räume im Neualand.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Räumen über Ausgänge verbunden.
 * Für jeden existierenden Ausgang hält ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Michael Kölling, David J. Barnes, Michal Kos und Cedric Wilke
 * @version 21.05.2025
 */

class Raum 
{
    private String beschreibung;
    private boolean bebaubar;
    private boolean bebaut;
    private HashMap<String, Raum> ausgaenge;        // die Ausgänge dieses Raums

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausgänge.
     * @param beschreibung enthält eine Beschreibung in der Form
     *        "in einer Küche" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung, boolean bebaubar, boolean bebaut) 
    {
        this.beschreibung = beschreibung;
        this.bebaubar = bebaubar;
        this.bebaut = bebaut;
        ausgaenge = new HashMap<String, Raum>();
    }

    /**
     * Definiere einen Ausgang für diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der über diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieses Raums (die dem Konstruktor
     * übergeben wurde).
     */
    public String gibBeschreibungRaum()
    {
        return beschreibung;
    }

    /**
     * @return die Information dieses Raums, ob er bebaut ist oder nicht (wurde im Konstruktor
     * ï¿½bergeben).
     */
    public boolean gibBebaut()
    {
        return bebaut;
    }
    
    /**
     * @return die Information dieses Raums, ob er bebaubar ist oder nicht (wurde im Konstruktor
     * ï¿½bergeben).
     */
    public boolean gibBebaubar()
    {
        return bebaubar;
    }
    
    /**
     * Ändere den Zustand des Feldes.
     */
    public void setzeBebaut(boolean bebaut)
    {
        this.bebaut = bebaut;
    }
    
    /**
     * Liefere eine Zeichenkette, die die Ausgänge dieses Raums
     * beschreibt, beispielsweise
     * "Ausgänge: north west".
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
     * Liefere den Raum, den wir erreichen, wenn wir aus diesem Raum
     * in die angegebene Richtung gehen. Liefere 'null', wenn in
     * dieser Richtung kein Ausgang ist.
     * @param richtung die Richtung, in die gegangen werden soll.
     * @return den Raum in der angegebenen Richtung.
     */
    public Raum gibAusgang(String richtung) 
    {
        return ausgaenge.get(richtung);
    }
}

