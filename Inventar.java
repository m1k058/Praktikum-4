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
    public void addItem(ItemTyp typ) {
        if (typ == null) {
            return;
        }
        if (this.itemAnzahl.containsKey(typ)) {
            int aktuelleAnzahl = this.itemAnzahl.get(typ);
            this.itemAnzahl.put(typ, aktuelleAnzahl + 1);
        } else {
            this.itemAnzahl.put(typ, 1);
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
    public boolean removeItem(ItemTyp typ) {
        if (typ == null || !this.itemAnzahl.containsKey(typ)) {
            return false;
        }

        int aktuelleAnzahl = this.itemAnzahl.get(typ);
        if (aktuelleAnzahl > 1) {
            this.itemAnzahl.put(typ, aktuelleAnzahl - 1);
        } else {
            this.itemAnzahl.remove(typ);
        }
        return true;
    }

    /**
     * Gibt die Anzahl der Items eines bestimmten ItemTyps im Inventar zurück
     *
     * @param typ Der ItemTyp, dessen Anzahl ermittelt werden soll
     * @return Die Anzahl der Items dieses Typs. Gibt 0 zurück, wenn nicht im Inventar
     */
     public int getAnzahlItems(ItemTyp typ) {
         if (typ == null) {
             return 0;
         }

         if (this.itemAnzahl.containsKey(typ)) {
             return this.itemAnzahl.get(typ);
         } else {
              return 0;
         }
    }


    /**
     * Erzeugt eine Zeichenkette des Inventarinhalts (z.B. "- Apfel (2)")
     * @return Eine Zeichenkette, die den Inhalt des Inventars darstellt
     */
    public String getInventarAlsString() {
        if (itemAnzahl.isEmpty()) {
            return "Dein Inventar ist leer.";
        }

        String inventar = "Du hast im Inventar:\n";
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
    public static boolean bewegeItem(ItemTyp typ, Inventar vonInventar, Inventar zuInventar) {
        if (typ == null || vonInventar == null || zuInventar == null) {
            return false;
        }
        if (vonInventar.removeItem(typ)) {
            zuInventar.addItem(typ);
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