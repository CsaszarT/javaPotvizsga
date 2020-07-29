/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Modell.Hegyek;
import java.util.List;
import javax.persistence.EntityManager;
import org.json.JSONArray;

/**
 *
 * @author User
 */
public class HegyekService {
    
    public boolean ujHegy(String szelessegiKoordinata, String hosszusagiKoordinata, int magassag, int megmaszasNehezsegiFoka, EntityManager em){
        
        // Modell osztályban lévő statikus fvg meghívása, ami booleanel tér vissza, ezért eleve egy if feltételeként hívjuk meg.
        if(Hegyek.ujHegy(szelessegiKoordinata, hosszusagiKoordinata, magassag, megmaszasNehezsegiFoka, em)){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    //get összes hegy
    public JSONArray hegyek(EntityManager em){
        List<Hegyek> hegyek = Hegyek.getOsszesHegy(em);
        //json objectekké konvertáljuk:
        //üres JSON tömb
        JSONArray osszesHegy = new JSONArray();
        for(Hegyek ahp : hegyek){//ahp = aktuális hegy példány
            osszesHegy.put(ahp.toJson()); // hegy objectből JSON objectet csinál, a JSON tömbhöz adja
        }
        return osszesHegy;
    }
    
}
