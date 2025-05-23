import java.util.Set;
import java.util.HashMap;

/**
 * Diese Klasse modelliert R�ume im Neualand.
 * 
 * Ein "Raum" repr�sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen R�umen �ber Ausg�nge verbunden.
 * F�r jeden existierenden Ausgang h�lt ein Raum eine Referenz auf 
 * den benachbarten Raum. Jeder Raum ist einer bestimmten Kategorie und
 * hat ensprechend bestimmte nutzbare Methoden
 * 
 * @author  Michal Kos und Cedric Wilke
 * @version 23.05.2025
 */

class Raum 
{
    private Raumkategorie kategorie;
    private HashMap<String, Raum> ausgaenge;        // die Ausg�nge dieses Raums

    /**
     * Erzeuge einen Raum  einer Kategorie. Ein Raum
     * hat anfangs keine Ausg�nge.
     * @param kategorie des Raumes.
     */
    public Raum(Raumkategorie kategorie) 
    {
        this.kategorie = kategorie;
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
     * Liefere eine Zeichenkette, die die Ausg�nge dieses Raumes
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
     * Liefere eine Zeichenkette mit der Beschreibung
     * f�r disesn Raum
     *
     * @return    eine Bschreibung des Raumes
     */
    public String gibBeschreibung()
    {
        return kategorie.gibBeschreibung();
    }

    /**
     * Gibt die kategorie des Raums zur�ck
     *
     * @return    Raumkategorie kategorie
     */
    public Raumkategorie gibKategorie()
    {
        return kategorie;
    }

}
