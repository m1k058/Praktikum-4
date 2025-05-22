
/**
 * Repraesentationen fuer alle kategorien der Räume,
 * zusammen mit einer Zeichenkette in einer bestimmten Sprachen.
 * 
 * @author  Michal Kos
 * @version 22.05.2025
 */
public enum Raumkategorie
{
    // Ein Wert fuer jedes Raumkategorie
    MARKTPLATZ("Marktplatz"),FELD("Feld"), BAHNHOF("Bahnhof"), BAUAMT("Bauamt"), STRAND("Strand"), OFFSHORE("Offshore Baugebiet");
    
    // Das Raumkategorie als Zeichenkette.
    private String kategorie;
    
    /**
     * Initialisieren mit der entsprechenden Raumkategorie.
     * @param kategorie des Raums als Zeichenkette.
     */
    Raumkategorie(String kategorie)
    {
        this.kategorie = kategorie;
    }
    
    /**
     * @return die Raumkategorie als Zeichenkette.
     */
    public String toString()
    {
        return kategorie;
    }
}
