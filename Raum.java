import java.util.HashMap;
import java.util.Set;
import java.util.HashMap;

/**
 * Diese Klasse modelliert Räume im Neualand.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Räumen über Ausgänge verbunden.
 * Für jeden existierenden Ausgang hält ein Raum eine Referenz auf 
 * den benachbarten Raum. Jeder Raum ist einer bestimmten Kategorie und
 * hat ensprechend bestimmte nutzbare Methoden
 * 
 * @author  Michal Kos und Cedric Wilke
 * @version 23.05.2025
 */

class Raum 
{
    private Raumkategorie kategorie;
    private HashMap<String, Raum> ausgaenge;        // die Ausgänge dieses Raums

    /**
     * Erzeuge einen Raum  einer Kategorie. Ein Raum
     * hat anfangs keine Ausgänge.
     * @param kategorie des Raumes.
     */
    public Raum(Raumkategorie kategorie) 
    {
        this.kategorie = kategorie;
        ausgaenge = new HashMap<String, Raum>();
    }

    /**
     * Definiere einen Ausgang für diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der über diesen Ausgang erreicht wird
     */
    public void setzeAusgangRaum(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * Liefere eine Zeichenkette, die die Ausgänge dieses Raumes
     * beschreibt, beispielsweise
     * "Ausgänge: north west".
     * @return eine Beschreibung der Ausgänge dieses Raumes.
     */
    public String gibRaumAusgaengeAlsString()
    {
        String ergebnis = "Ausgaenge: ";
        Set<String> keys = ausgaenge.keySet();
        boolean ersterAusgang = true;
        for(String ausgang : keys){            
            if (ersterAusgang) {
            ergebnis += " " + ausgang;
            ersterAusgang = false;
            } else {
            ergebnis += ", " + ausgang;
            }
        }
        return ergebnis;
    }
    
    /**
     * Liefere den Raum, der in die angegebene Richtung liegt.
     * Liefere null, wenn in dieser Richtung kein Ausgang ist.
     * @param richtung Die Richtung des Ausgangs.
     * @return Den Raum in der angegebenen Richtung oder null.
     */
    public Raum gibAusgangRaum(String richtung) {
        return ausgaenge.get(richtung);
    }

    /**
     * Liefere eine Zeichenkette mit der Beschreibung
     * für disesn Raum
     *
     * @return    eine Bschreibung des Raumes
     */
    public String gibBeschreibung()
    {
        return kategorie.gibBeschreibung();
    }

    /**
     * Gibt die kategorie des Raums zurück
     *
     * @return    Raumkategorie kategorie
     */
    public Raumkategorie gibKategorie()
    {
        return kategorie;
    }

}
