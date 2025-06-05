/**
 * Repr�sentiert eine konkrete Instanz eines Gegenstands (Items) im Spiel.
 * Jedes Item geh�rt zu einem bestimmten ItemTyp, von dem es seine
 * grundlegenden Eigenschaften wie Name und Beschreibung bezieht.
 * Diese Klasse dient dazu, individuelle Gegenst�nde zu erstellen, die im Inventar
 * eines Spielers oder in einem Raum gelagert werden k�nnen.
 * 
 * @author  Michal Kos
 * @version 04.06.2025
 */
public class Item {
    private ItemTyp typ; // Der Typ dieses Item

    /**
     * Erzeugt eine neues Item-Objekt eines bestimmten Typs.
     * Die Eigenschaften des Items (Name, Beschreibung) werden vom �bergebenen 
     * ItemTyp �bertragen
     * @param typ Der ItemTyp f�r dieses Item
     */
    public Item(ItemTyp typ) {
        this.typ = typ;
    }

    /**
     * Gibt den ItemTyp dieses Items zur�ck.
     *
     * @return ItemTyp dieses Items.
     */
    public ItemTyp getTyp() {
        return typ;
    }

    /**
     * Gibt den Anzeigenamen dieses Items zur�ck.
     *
     * @return Name des Items (z.B. "Apfel").
     */
    public String getName() {
        return typ.getAnzeigename();
    }

    /**
     * Gibt die Standardbeschreibung dieses Items zur�ck.
     *
     * @return Die Beschreibung des Items.
     */
    public String getBeschreibung() {
        return typ.getBeschreibung();
    }
}