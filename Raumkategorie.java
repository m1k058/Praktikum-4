
/**
 * Repraesentationen fuer alle kategorien der Räume,
 * zusammen mit einer Zeichenkette und einem bau limit.
 * 
 * @author  Michal Kos
 * @version 23.05.2025
 */
public enum Raumkategorie
{
    // Ein Wert fuer jedes Raumkategorie
    MARKTPLATZ("am Marktplatz", 0, 0),

    FELD("auf einem Feld", 1, 2),

    BAHNHOF("am Bahnhof", 0, 0),

    BAUAMT("im Bauamt Gebäude", 0, 0),

    STRAND("am Strand", 0, 0),

    OFFSHORE("am Offshore Baugebiet", 0, 4),
    
    BUNDESAMT("im Bundesamt", 0, 0),

    AUTOBAHN("auf der Autobahn", 0, 0);

    // Das Raumkategorie Beschreibung als Zeichenkette.
    private final String kategorie;
    private final int solar;
    private final int wind;

    /**
     * Initialisieren mit der entsprechenden Raumkategorie.
     * @param kategorie des Raums als Zeichenkette und anzahl der plazierbaren
     * Solar und Windanlagen.
     */
    Raumkategorie(String kategorie, int solar, int wind)
    {
        this.kategorie = kategorie;
        this.solar = solar;
        this.wind = wind;
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

    /**
     * Gibt die erlaubte anzahl von Solaranlagen für den Raum.
     *
     * @return    max Anzahl Solaranlage
     */
    public int maxSolar()
    {
        return solar;
    }
    
    /**
     * Gibt die erlaubte anzahl von Windanlagen für den Raum.
     *
     * @return    max Anzahl Windanlage
     */
    public int maxWind()
    {
        return wind;
    }

}