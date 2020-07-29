/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Modell.Hegymaszok;
import java.util.List;
import javax.persistence.EntityManager;
import org.json.JSONArray;

/**
 *
 * @author User
 */
public class HegymaszokService {
    public JSONArray hegymaszok(EntityManager em){
        List<Hegymaszok> hegymaszok = Hegymaszok.getOsszesFelnottHegymaszoByNevsor(em);
        //json objectekké konvertáljuk:
        //üres JSON tömb
        JSONArray osszesHegymaszok = new JSONArray();
        for(Hegymaszok amp : hegymaszok){//amp = aktuális hegy példány
            osszesHegymaszok.put(amp.toJson()); // hegy objectből JSON objectet csinál, a JSON tömbhöz adja
        }
        return osszesHegymaszok;
    }
}
