import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Diese Klasse modelliert R�ume im Neualand.
 * 
 * Ein "Raum" repr�sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen R�umen �ber Ausg�nge verbunden.
 * F�r jeden existierenden Ausgang h�lt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Michael K�lling, David J. Barnes, Michal Kos und Cedric Wilke
 * @version 21.05.2025
 */

class Raum 
{
    private String beschreibung;
    private boolean bebaubar;
    private boolean bebaut;
    private HashMap<String, Raum> ausgaenge;        // die Ausg�nge dieses Raums

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausg�nge.
     * @param beschreibung enth�lt eine Beschreibung in der Form
     *        "in einer K�che" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung, boolean bebaubar, boolean bebaut) 
    {
        this.beschreibung = beschreibung;
        this.bebaubar = bebaubar;
        this.bebaut = bebaut;
        ausgaenge = new HashMap<String, Raum>();
    }

    /**
     * Definiere einen Ausgang f�r diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der �ber diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieses Raums (die dem Konstruktor
     * �bergeben wurde).
     */
    public String gibBeschreibungRaum()
    {
        return beschreibung;
    }

    /**
     * @return die Information dieses Raums, ob er bebaut ist oder nicht (wurde im Konstruktor
     * �bergeben).
     */
    public boolean gibBebaut()
    {
        return bebaut;
    }
    
    /**
     * @return die Information dieses Raums, ob er bebaubar ist oder nicht (wurde im Konstruktor
     * �bergeben).
     */
    public boolean gibBebaubar()
    {
        return bebaubar;
    }
    
    /**
     * �ndere den Zustand des Feldes.
     */
    public void setzeBebaut(boolean bebaut)
    {
        this.bebaut = bebaut;
    }
    
    /**
     * Liefere eine Zeichenkette, die die Ausg�nge dieses Raums
     * beschreibt, beispielsweise
     * "Ausg�nge: north west".
     * @return eine Beschreibung der Ausg�nge dieses Raumes.
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

