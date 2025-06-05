
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
     * Konstruktor f�r Objekte der Klasse Spieler
     */
    public Spieler(int startGeld)
    {
        spielerInventar = new Inventar();
        spielerInventar.addItemAnzahl("M�nze", startGeld);
    }

    /**
     * Gib Geld des Spielers zur�ck.
     */
    public int gibGeld()
    {
        return spielerInventar.gibAnzahlItems("M�nze");
    }
    
    /**
     * Getter f�r das Spieler Inventar
     *
     * @return das Inventar
     */
    public Inventar gibSpielerInventar() { 
        return spielerInventar;
    }
    
    /**
     * �ndere Geld des Spielers.
     */
    public void aendereGeld(int aenderung)
    {
        if(aenderung>0){
            spielerInventar.addItemAnzahl("M�nze", aenderung);
        }
        else if (aenderung<0){
            spielerInventar.removeItemAnzahl("M�nze", -aenderung);
        }
    }
}
