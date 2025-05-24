import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;

/**
 * Die Spielumgebung besteht aus Regionen die
 * miteinander verbunden sind. In Dieser Klasse
 * wird aus einer .json datei die ganze 
 * Spielumgebung generiert.
 *
 * @Michal Kos
 * @24.05.2025
 */
public class Spielumgebung
{
     private HashMap<String, Region> regionen = new HashMap<>();
    
    public Spielumgebung(String dateipfad) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(new FileReader(dateipfad));
        JSONArray regionArray = (JSONArray) root.get("regionen");

        // 1. Regionen + Räume anlegen
        for (Object o : regionArray) {
            JSONObject regionJSON = (JSONObject) o;
            String regionName = (String) regionJSON.get("name");

            // Räume einlesen
            JSONArray raeumeArray = (JSONArray) regionJSON.get("raeume");
            HashMap<String, Raum> raeume = new HashMap<>();
            for (Object ro : raeumeArray) {
                JSONObject raumJSON = (JSONObject) ro;
                String raumName = (String) raumJSON.get("name");
                String kategorieStr = (String) raumJSON.get("kategorie");
                Raumkategorie kategorie = Raumkategorie.valueOf(kategorieStr);   
                
                Raum raum;
                if (kategorie == Raumkategorie.BAHNHOF || kategorie == Raumkategorie.AUTOBAHN) {
                    raum = new TravelRaum(kategorie);
                } 
                else if (kategorie == Raumkategorie.FELD || kategorie == Raumkategorie.OFFSHORE) {
                    raum = new BauRaum(kategorie);
                } 
                else {
                    raum = new Raum(kategorie);
                } 
                
                raeume.put(raumName, raum);
            }
            Region region = new Region(regionName, raeume);
            regionen.put(regionName, region);
        }
        for (Object o : regionArray) {
            JSONObject regionJSON = (JSONObject) o;
            String regionName = (String) regionJSON.get("name");
            Region region = regionen.get(regionName);

            // Region-Ausgänge setzen
            JSONObject regionAusgaengeJSON = (JSONObject) regionJSON.get("ausgaenge");
            if (regionAusgaengeJSON != null) {
                for (Object key : regionAusgaengeJSON.keySet()) {
                    String richtung = (String) key;
                    String zielRegionName = (String) regionAusgaengeJSON.get(richtung);
                    Region zielRegion = regionen.get(zielRegionName);
                    region.setzeAusgangRegion(richtung, zielRegion);
                }
            }

            // Raum-Ausgänge setzen
            JSONArray raeumeArray = (JSONArray) regionJSON.get("raeume");
            for (Object ro : raeumeArray) {
                JSONObject raumJSON = (JSONObject) ro;
                String raumName = (String) raumJSON.get("name");
                Raum raum = region.getRaum(raumName);

                JSONObject raumAusgaengeJSON = (JSONObject) raumJSON.get("ausgaenge");
                if (raumAusgaengeJSON != null) {
                    for (Object key : raumAusgaengeJSON.keySet()) {
                        String richtung = (String) key;
                        String zielRaumName = (String) raumAusgaengeJSON.get(richtung);
                        Raum zielRaum = region.getRaum(zielRaumName);
                        raum.setzeAusgangRaum(richtung, zielRaum);
                    }
                }
            }
        }

        

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        return 1;
    }
}
