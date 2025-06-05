
/**
 * Beschreiben Sie hier die Klasse Spieler.
 * 
 * @author (Cedric) 
 * @version (2.0)
 */
public class Spieler
{
    private Inventar spielerInventar;

    /**
     * Konstruktor für Objekte der Klasse Spieler
     */
    public Spieler(int startGeld)
    {
        spielerInventar = new Inventar();
        spielerInventar.addItemAnzahl("MUENZE", startGeld);
    }

    /**
     * Gib Geld des Spielers zurück.
     */
    public int gibGeld()
    {
        return spielerInventar.gibAnzahlItems("MUENZE");
    }
    
    /**
     * Ändere Geld des Spielers.
     */
    public void aendereGeld(int aenderung)
    {
        if(aenderung>0){
            spielerInventar.addItemAnzahl("MUENZE", aenderung);
        }
        else if (aenderung<0){
            spielerInventar.removeItemAnzahl("MUENZE", aenderung);
        }
    }
}
