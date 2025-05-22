
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
     * Konstruktor für Objekte der Klasse Spieler
     */
    public Spieler(int geld, int einkommen, int ansehen, int gebauteAnlagen)
    {
        this.geld = geld;
        this.einkommen = einkommen;
        this.ansehen = ansehen;
        this.gebauteAnlagen = gebauteAnlagen;
    }

    /**
     * Gib Geld des Spielers zurück.
     */
    public int gibGeld()
    {
        return this.geld;
    }
    
    /**
     * Gib Einkommen des Spielers zurück.
     */
    public int gibEinkommen()
    {
        return this.einkommen;
    }
    
    /**
     * Gib Ansehen des Spielers zurück.
     */
    public int gibAnsehen()
    {
        return this.ansehen;
    }
    
    /**
     * Gib Anzahl der gebauten Anlagen des Spielers zurück.
     */
    public int gibGebauteAnlagen()
    {
        return this.gebauteAnlagen;
    }
    
    /**
     * Ändere Geld des Spielers.
     */
    public void aendereGeld(int aenderung)
    {
        geld += aenderung;
    }
    
    /**
     * Ändere Einkommen des Spielers.
     */
    public void aendereEinkommen(int aenderung)
    {
        einkommen += aenderung;
    }
    
    /**
     * Ändere Ansehen des Spielers.
     */
    public void aendereAnsehen(int aenderung)
    {
        ansehen += aenderung;
    }
    
    /**
     * Ändere die Anzahl der gebauten des Spielers.
     */
    public void aendereGebauteAnlagen(int aenderung)
    {
        gebauteAnlagen += aenderung;
    }
    
    public void ausgeben() {
        System.out.println("Münzen: " + geld + "   Einkommen: " + einkommen + "   Ansehen: " + ansehen);
    }
}
