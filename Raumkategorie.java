
/**
 * Repraesentationen fuer alle kategorien der Räume,
 * zusammen mit einer Zeichenkette .
 * 
 * @author  Michal Kos
 * @version 23.05.2025
 */
public enum Raumkategorie
{
    // Ein Wert fuer jedes Raumkategorie
    MARKTPLATZ("am Marktplatz"),

    FELD("auf einem Feld"),

    BAHNHOF("am Bahnhof"),

    BAUAMT("im Bauamt Gebäude"),

    STRAND("am Strand"),

    OFFSHORE("am Offshore Baugebiet"),

    AUTOBAHN("auf der Autobahn");

    // Das Raumkategorie Beschreibung als Zeichenkette.
    private final String kategorie;

    /**
     * Initialisieren mit der entsprechenden Raumkategorie.
     * @param kategorie des Raums als Zeichenkette.
     */
    Raumkategorie(String kategorie)
    {
        this.kategorie = kategorie;
    }

    /**
     * Gibt die Beschreibung der Kategorie aus
     *
     * @return    Beschreibung der Kategorie
     */
    public String gibBeschreibung()
    {
        return kategorie;
    }

}