import java.util.HashMap;
import java.util.Map;

/**
 * Verwaltet eine Sammlung von Gegenständen (Items) für Entitäten im Spiel,
 * wie Spieler oder Räume. Dieses Inventar zählt die Vorkommen jedes
 * ItemTyp. Es ermöglicht das Hinzufügen, Entfernen, Abfragen und
 * verschieben von Items sowie deren Anzahl.
 *
 * @author  Michal Kos
 * @version 04.06.2025
 */

public class Inventar {
    
    private Map<ItemTyp, Integer> itemAnzahl;

    /**
     * Erzeugt ein neues, leeres Inventar.
     */
    public Inventar() {
        this.itemAnzahl = new HashMap<>();
    }

    /**
     * Fügt einen ItemTyp zum Inventar hinzu oder erhöht dessen Anzahl.
     *
     * @param typ Der hinzuzufügende ItemTyp. Wenn null, geschieht nichts.
     */
    public void addItem(String typName) {
        ItemTyp item = findeItemTyp(typName);
        if (item == null) {
            return;
        }
        if (this.itemAnzahl.containsKey(item)) {
            int aktuelleAnzahl = this.itemAnzahl.get(item);
            this.itemAnzahl.put(item, aktuelleAnzahl + 1);
        } else {
            this.itemAnzahl.put(item, 1);
        }
    }
    
    /**
     * Fügt einen ItemTyp zum Inventar hinzu oder erhöht dessen Anzahl um mehr als 1
     *
     * @param typ Der hinzuzufügende ItemTyp. Wenn null, geschieht nichts
     * anzahl Die Anzahl an Items die hinzugefügt werden soll
     */
    public void addItemAnzahl(String typName, int anzahl) {
        ItemTyp item = findeItemTyp(typName);
        if (item == null) {
            return;
        }
        if (this.itemAnzahl.containsKey(item)) {
            int aktuelleAnzahl = this.itemAnzahl.get(item);
            this.itemAnzahl.put(item, aktuelleAnzahl + anzahl);
        } else {
            this.itemAnzahl.put(item, anzahl);
        }
    }

    /**
     * Entfernt ein Exemplar eines ItemTyps aus dem Inventar
     * Wenn der angegebene ItemTyp mehrfach vorhanden war, wird seine Anzahl dekrementiert
     * Ist es das letzte Exemplar dieses Typs, wird der ItemTyp komplett aus dem Inventar entfernt
     *
     * @param typ Der ItemTyp des zu entfernenden Items
     * @return  true wenn ein Exemplar des Typs erfolgreich entfernt wurde
     *  false wenn der Typ nicht im Inventar vorhanden war
     */
    public boolean removeItem(String typName) {
        ItemTyp item = findeItemTyp(typName);
        if (item == null || !this.itemAnzahl.containsKey(item)) {
            return false;
        }

        int aktuelleAnzahl = this.itemAnzahl.get(item);
        if (aktuelleAnzahl > 1) {
            this.itemAnzahl.put(item, aktuelleAnzahl - 1);
        } else {
            this.itemAnzahl.remove(item);
        }
        return true;
    }
    
    /**
     * Entfernt ein Exemplar eines ItemTyps aus dem Inventar
     * Wenn der angegebene ItemTyp mehrfach vorhanden war, wird seine Anzahl dekrementiert
     * Ist es das letzte Exemplar dieses Typs, wird der ItemTyp komplett aus dem Inventar entfernt
     *
     * @param typ Der ItemTyp des zu entfernenden Items
     * @return  true wenn ein Exemplar des Typs erfolgreich entfernt wurde
     *  false wenn der Typ nicht im Inventar vorhanden war
     */
    public boolean removeItemAnzahl(String typName, int anzahl) {
        ItemTyp item = findeItemTyp(typName);
        if (item == null || !this.itemAnzahl.containsKey(item)) {
            return false;
        }

        int aktuelleAnzahl = this.itemAnzahl.get(item);
        if (aktuelleAnzahl > anzahl) {
            this.itemAnzahl.put(item, aktuelleAnzahl - anzahl);
        } else {
            this.itemAnzahl.remove(item);
        }
        return true;
    }

    /**
     * Gibt die Anzahl der Items eines bestimmten ItemTyps im Inventar zurück
     *
     * @param typ Der ItemTyp, dessen Anzahl ermittelt werden soll
     * @return Die Anzahl der Items dieses Typs. Gibt 0 zurück, wenn nicht im Inventar
     */
     public int gibAnzahlItems(String typName) {
         ItemTyp item = findeItemTyp(typName);
         if (item == null) {
             return 0;
         }

         if (this.itemAnzahl.containsKey(item)) {
             return this.itemAnzahl.get(item);
         } else {
              return 0;
         }
    }
    
    /**
     * Prüft ob das Inventar Leer ist
     * @return true wenn Leer, sonst false
     */
    public boolean istLeer(){
        return itemAnzahl.isEmpty();
    }

    /**
     * Erzeugt eine Zeichenkette des Inventarinhalts (z.B. "- Apfel (2)")
     * @return Eine Zeichenkette, die den Inhalt des Inventars darstellt
     */
    public String gibInventarAlsString() {
        if (itemAnzahl.isEmpty()) {
            return null;
        }

        String inventar = "";
        for (Map.Entry<ItemTyp, Integer> entry : itemAnzahl.entrySet()) {
            ItemTyp typ = entry.getKey();
            int anzahl = entry.getValue();

            inventar += "- " + typ.getAnzeigename();
            if (anzahl > 1) {
                inventar += " (" + anzahl + ")";
            }
            inventar += " \t[" + typ.getBeschreibung() + "]\n";
        }
        return inventar;
    }

    /**
     * Verschiebt ein Item eines bestimmten ItemTyps von einem Quellinventar
     * in ein Zielinventar
     * 
     * @param typ Der ItemTyp des zu verschiebenden Items
     * @param vonInventar Das  Inventar, aus dem das Item entfernt werden soll
     * @param zuInventar Das Inventar, zu dem das Item hinzugefügt werden soll
     * @return true, wenn das Item erfolgreich verschoben wurde, sonst false
     */
    public static boolean bewegeItem(String typName, Inventar vonInventar, Inventar zuInventar) {
         ItemTyp item = findeItemTyp(typName);
        if (item == null || vonInventar == null || zuInventar == null) {
            return false;
        }
        if (vonInventar.removeItem(typName)) {
            zuInventar.addItem(typName);
            return true;
        }
        return false;
    }

    /**
     * Findet einen ItemTyp basierend auf einem übergebenen String
     * Die Suche ignoriert Groß und Kleinschreibung
     * @param name Der Anzeigename des gesuchten  ItemTyps
     * @return Der gefundene ItemTyp oder  null wenn kein passender Typ existiert
     */
    public static ItemTyp findeItemTyp(String name) {
        if (name == null) return null;
        for (ItemTyp typ : ItemTyp.values()) {
            if (typ.getAnzeigename().equalsIgnoreCase(name)) {
                return typ;
            }
        }
        return null;
    }
}