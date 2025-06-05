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
    // Speichert die Anzahl jedes ItemTyps
    private Map<ItemTyp, Integer> itemAnzahl;

    public Inventar() {
        this.itemAnzahl = new HashMap<>();
    }

    /**
     * Fügt ein Item zum Inventar hinzu.
     * Ist der ItemTyp bereits vorhanden, wird seine Anzahl erhöht.
     * @param item Das hinzuzufügende Item.
     */
    public void addItem(Item item) {
        if (item == null){
            return;
        }
        ItemTyp typ = item.getTyp();
        
         if (this.itemAnzahl.containsKey(typ)) {
            int aktuelleAnzahl = this.itemAnzahl.get(typ);
            this.itemAnzahl.put(typ, aktuelleAnzahl + 1);
        } else {
            this.itemAnzahl.put(typ, 1);
        }
    }

    /**
     * Entfernt ein Exemplar eines Items anhand seines ItemTyps aus dem Inventar.
     * @param typ Der ItemTyp des zu entfernenden Items.
     * @return true wenn es entfernt werden konnte oder false, wenn der Typ nicht im Inventar war.
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
     * @param Der ItemTyp, dessen Anzahl ermittelt werden soll
     * @return Die Anzahl der Items dieses Typs
     */
    public int gibAnzahlItems(ItemTyp typ) {
        if (typ == null) return 0;
        return itemAnzahl.get(typ);
    }

    /**
     * Erzeugt eine Zeichenkette des Inventarinhalts (z.B. "- Apfel (2)").
     * @return Eine Zeichenkette mit dem Inhalt des Inventars.
     */
    public String gibInventarAlsString() {
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
            inventar += " \t[" + typ.getBeschreibung() + "]" + "\n";
        }
        return inventar;
    }

    /**
     * Verschiebt ein Item eines bestimmten Typs von einem Quellinventar in ein Zielinventar
     * @param ItemTyp des zu verschiebenden Items
     * @param vonInventar Das Inventar, aus dem das Item entfernt werden soll
     * @param zuInventar Das Inventar, zu dem das Item hinzugefügt werden soll
     * @return true, wenn das Item erfolgreich verschoben wurde, sonst false
     */
    public static boolean bewegeItem(ItemTyp typ, Inventar vonInventar, Inventar zuInventar) {
        if (typ == null || vonInventar == null || zuInventar == null) {
            return false;
        }
        Item itemZuBewegen = vonInventar.removeItem(typ); // Gibt eine Item-Instanz zurück
        if (itemZuBewegen != null) {
            zuInventar.addItem(typ); // Fügt die Item-Instanz hinzu
            return true;
        }
        return false;
    }

    /**
     * Findet einen ItemTyp basierend auf einem String (ignoriert Groß-/Kleinschreibung).
     * @param name Der Name des gesuchten ItemTyps.
     * @return Der gefundene ItemTyp oder null, wenn keiner passt.
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