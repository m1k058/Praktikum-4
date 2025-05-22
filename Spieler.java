
/**
 * Beschreiben Sie hier die Klasse Spieler.
 * 
 * @author (Cedric) 
 * @version (1.0)
 */
public class Spieler
{
    private int geld;
    private int einkommen;
    private int ansehen;
    private int gebauteAnlagen;

    /**
     * Konstruktor f�r Objekte der Klasse Spieler
     */
    public Spieler(int geld, int einkommen, int ansehen, int gebauteAnlagen)
    {
        this.geld = geld;
        this.einkommen = einkommen;
        this.ansehen = ansehen;
        this.gebauteAnlagen = gebauteAnlagen;
    }

    /**
     * Gib Geld des Spielers zur�ck.
     */
    public int gibGeld()
    {
        return this.geld;
    }
    
    /**
     * Gib Einkommen des Spielers zur�ck.
     */
    public int gibEinkommen()
    {
        return this.einkommen;
    }
    
    /**
     * Gib Ansehen des Spielers zur�ck.
     */
    public int gibAnsehen()
    {
        return this.ansehen;
    }
    
    /**
     * Gib Anzahl der gebauten Anlagen des Spielers zur�ck.
     */
    public int gibGebauteAnlagen()
    {
        return this.gebauteAnlagen;
    }
    
    /**
     * �ndere Geld des Spielers.
     */
    public void aendereGeld(int aenderung)
    {
        geld += aenderung;
    }
    
    /**
     * �ndere Einkommen des Spielers.
     */
    public void aendereEinkommen(int aenderung)
    {
        einkommen += aenderung;
    }
    
    /**
     * �ndere Ansehen des Spielers.
     */
    public void aendereAnsehen(int aenderung)
    {
        ansehen += aenderung;
    }
    
    /**
     * �ndere die Anzahl der gebauten des Spielers.
     */
    public void aendereGebauteAnlagen(int aenderung)
    {
        gebauteAnlagen += aenderung;
    }
    
    public void ausgeben() {
        System.out.println("M�nzen: " + geld + "   Einkommen: " + einkommen + "   Ansehen: " + ansehen);
    }
}
