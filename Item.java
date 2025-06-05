/**
 * Repräsentiert eine konkrete Instanz eines Gegenstands (Items) im Spiel.
 * Jedes Item gehört zu einem bestimmten ItemTyp, von dem es seine
 * grundlegenden Eigenschaften wie Name und Beschreibung bezieht.
 * Diese Klasse dient dazu, individuelle Gegenstände zu erstellen, die im Inventar
 * eines Spielers oder in einem Raum gelagert werden können.
 * 
 * @author  Michal Kos
 * @version 04.06.2025
 */
public class Item {
    private ItemTyp typ; // Der Typ dieses Item

    /**
     * Erzeugt eine neues Item-Objekt eines bestimmten Typs.
     * Die Eigenschaften des Items (Name, Beschreibung) werden vom übergebenen 
     * ItemTyp übertragen
     * @param typ Der ItemTyp für dieses Item
     */
    public Item(ItemTyp typ) {
        this.typ = typ;
    }

    /**
     * Gibt den ItemTyp dieses Items zurück.
     *
     * @return ItemTyp dieses Items.
     */
    public ItemTyp getTyp() {
        return typ;
    }

    /**
     * Gibt den Anzeigenamen dieses Items zurück.
     *
     * @return Name des Items (z.B. "Apfel").
     */
    public String getName() {
        return typ.getAnzeigename();
    }

    /**
     * Gibt die Standardbeschreibung dieses Items zurück.
     *
     * @return Die Beschreibung des Items.
     */
    public String getBeschreibung() {
        return typ.getBeschreibung();
    }
}