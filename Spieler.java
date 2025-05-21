
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

    /**
     * Konstruktor für Objekte der Klasse Spieler
     */
    public Spieler(int geld, int einkommen, int ansehen)
    {
        this.geld = geld;
        this.einkommen = einkommen;
        this.ansehen = ansehen;
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
    
    public void ausgeben() {
        System.out.println("Münzen: " + geld + "   Einkommen: " + einkommen + "   Ansehen: " + ansehen);
    }
}
