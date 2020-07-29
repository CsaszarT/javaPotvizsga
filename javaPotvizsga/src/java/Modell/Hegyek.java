package Modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

@Entity
@Table(name = "hegyek")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hegyek.findAll", query = "SELECT h FROM Hegyek h")
    , @NamedQuery(name = "Hegyek.findByIdHegyek", query = "SELECT h FROM Hegyek h WHERE h.idHegyek = :idHegyek")
    , @NamedQuery(name = "Hegyek.findBySzelessegiKoordinata", query = "SELECT h FROM Hegyek h WHERE h.szelessegiKoordinata = :szelessegiKoordinata")
    , @NamedQuery(name = "Hegyek.findByHosszusagiKoordinata", query = "SELECT h FROM Hegyek h WHERE h.hosszusagiKoordinata = :hosszusagiKoordinata")
    , @NamedQuery(name = "Hegyek.findByMagassag", query = "SELECT h FROM Hegyek h WHERE h.magassag = :magassag")
    , @NamedQuery(name = "Hegyek.findByMegmaszasNehezsegiFoka", query = "SELECT h FROM Hegyek h WHERE h.megmaszasNehezsegiFoka = :megmaszasNehezsegiFoka")})
public class Hegyek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hegyek")
    private Integer idHegyek;
    @Size(max = 8)
    @Column(name = "szelessegi_koordinata")
    private String szelessegiKoordinata;
    @Size(max = 8)
    @Column(name = "hosszusagi_koordinata")
    private String hosszusagiKoordinata;
    @Column(name = "magassag")
    private Integer magassag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "megmaszas_nehezsegi_foka")
    private int megmaszasNehezsegiFoka;

    public Hegyek() {
    }

    public Hegyek(Integer idHegyek) {
        this.idHegyek = idHegyek;
    }

    public Hegyek(Integer idHegyek, int megmaszasNehezsegiFoka) {
        this.idHegyek = idHegyek;
        this.megmaszasNehezsegiFoka = megmaszasNehezsegiFoka;
    }

    public Integer getIdHegyek() {
        return idHegyek;
    }

    public void setIdHegyek(Integer idHegyek) {
        this.idHegyek = idHegyek;
    }

    public String getSzelessegiKoordinata() {
        return szelessegiKoordinata;
    }

    public void setSzelessegiKoordinata(String szelessegiKoordinata) {
        this.szelessegiKoordinata = szelessegiKoordinata;
    }

    public String getHosszusagiKoordinata() {
        return hosszusagiKoordinata;
    }

    public void setHosszusagiKoordinata(String hosszusagiKoordinata) {
        this.hosszusagiKoordinata = hosszusagiKoordinata;
    }

    public Integer getMagassag() {
        return magassag;
    }

    public void setMagassag(Integer magassag) {
        this.magassag = magassag;
    }

    public int getMegmaszasNehezsegiFoka() {
        return megmaszasNehezsegiFoka;
    }

    public void setMegmaszasNehezsegiFoka(int megmaszasNehezsegiFoka) {
        this.megmaszasNehezsegiFoka = megmaszasNehezsegiFoka;
    }

    //új hegy felvitele
    public static boolean ujHegy(String ujSzelessegiKoordinata, String ujHosszusagiKoordinata, int ujMagassag, int ujMegmaszasNehezsegiFoka, EntityManager em){
        try{
            // tárolt eljárás meghivatkozása
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ujHegy");
            // a tárolt eljárásban rögzített paraméterek létrehozáse JAVA oldalon
            spq.registerStoredProcedureParameter("szelessegiKoordinataIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("hosszusagiKoordinataIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("magassagIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("megmaszasNehezsegiFokaIN", Integer.class, ParameterMode.IN);
            // Paraméterekhez való érték átadás.
            spq.setParameter("szelessegiKoordinataIN", ujSzelessegiKoordinata);
            spq.setParameter("hosszusagiKoordinataIN", ujHosszusagiKoordinata);
            spq.setParameter("magassagIN", ujMagassag);
            spq.setParameter("megmaszasNehezsegiFokaIN", ujMegmaszasNehezsegiFoka);
            // Lefuttatjuk a tárolt eljárást
            spq.execute();
            return true;
        }    
        catch(Exception ex){
            return false;
        }
    }
    
    
    //összes hegy kilistázása
    
    public JSONObject toJson(){
        JSONObject j = new JSONObject();
        j.put("id", this.idHegyek);
        j.put("szelessegiKoordinata", this.szelessegiKoordinata);
        j.put("hosszusagiKoordinata", this.hosszusagiKoordinata);
        j.put("magassag", this.magassag);
        j.put("megmaszasNehezsegiFoka", this.megmaszasNehezsegiFoka);
        return j;
    }
    
    public static List<Hegyek> getOsszesHegy (EntityManager em){
        //üres lista amit returnolunk majd.
        List<Hegyek> hegyek = new ArrayList();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getOsszesHegy");
        // MIndig object tömböket tartalamzó listát ad vissza  a select
        List<Object[]> lista = spq.getResultList(); // execute + a visszatérési adatok lekérése
        for(Object[] elem : lista){
            //minden rekord elsődleges kulcsát kell kinyerni
            int id = Integer.parseInt(elem[0].toString());
            // elsődleges kulcs alapján történő példányosítás
            Hegyek h = em.find(Hegyek.class, id);
            //példány hozzáadása a returnölendő listához
            hegyek.add(h);
        }
        return hegyek;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHegyek != null ? idHegyek.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hegyek)) {
            return false;
        }
        Hegyek other = (Hegyek) object;
        if ((this.idHegyek == null && other.idHegyek != null) || (this.idHegyek != null && !this.idHegyek.equals(other.idHegyek))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Hegyek[ idHegyek=" + idHegyek + " ]";
    }
    
}
