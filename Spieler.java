
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
     * Konstruktor fï¿½r Objekte der Klasse Spieler
     */
    public Spieler(int startGeld)
    {
        spielerInventar = new Inventar();
        spielerInventar.addItemAnzahl("Münze", startGeld);
    }

    /**
     * Gib Geld des Spielers zurï¿½ck.
     */
    public int gibGeld()
    {
        return spielerInventar.gibAnzahlItems("Münze");
    }
    
    /**
     * ï¿½ndere Geld des Spielers.
     */
    public void aendereGeld(int aenderung)
    {
        if(aenderung>0){
            spielerInventar.addItemAnzahl("Münze", aenderung);
        }
        else if (aenderung<0){
            spielerInventar.removeItemAnzahl("Münze", -aenderung);
        }
    }
}
