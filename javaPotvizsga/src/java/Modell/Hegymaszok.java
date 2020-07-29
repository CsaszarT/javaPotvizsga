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
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;


@Entity
@Table(name = "hegymaszok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hegymaszok.findAll", query = "SELECT h FROM Hegymaszok h")
    , @NamedQuery(name = "Hegymaszok.findByIdHegymaszo", query = "SELECT h FROM Hegymaszok h WHERE h.idHegymaszo = :idHegymaszo")
    , @NamedQuery(name = "Hegymaszok.findByNev", query = "SELECT h FROM Hegymaszok h WHERE h.nev = :nev")
    , @NamedQuery(name = "Hegymaszok.findByKor", query = "SELECT h FROM Hegymaszok h WHERE h.kor = :kor")
    , @NamedQuery(name = "Hegymaszok.findByOrszag", query = "SELECT h FROM Hegymaszok h WHERE h.orszag = :orszag")
    , @NamedQuery(name = "Hegymaszok.findByKepessegiSzint", query = "SELECT h FROM Hegymaszok h WHERE h.kepessegiSzint = :kepessegiSzint")})
public class Hegymaszok implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hegymaszo")
    private Integer idHegymaszo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nev")
    private String nev;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kor")
    private int kor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "orszag")
    private String orszag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kepessegi_szint")
    private int kepessegiSzint;

    public Hegymaszok() {
    }

    public Hegymaszok(Integer idHegymaszo) {
        this.idHegymaszo = idHegymaszo;
    }

    public Hegymaszok(Integer idHegymaszo, String nev, int kor, String orszag, int kepessegiSzint) {
        this.idHegymaszo = idHegymaszo;
        this.nev = nev;
        this.kor = kor;
        this.orszag = orszag;
        this.kepessegiSzint = kepessegiSzint;
    }

    public Integer getIdHegymaszo() {
        return idHegymaszo;
    }

    public void setIdHegymaszo(Integer idHegymaszo) {
        this.idHegymaszo = idHegymaszo;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public int getKepessegiSzint() {
        return kepessegiSzint;
    }

    public void setKepessegiSzint(int kepessegiSzint) {
        this.kepessegiSzint = kepessegiSzint;
    }

    
    
    
    
    
    //összes hegymaszo kilistázása
    
    public JSONObject toJson(){
        JSONObject l = new JSONObject();
        l.put("id", this.idHegymaszo);
        l.put("nev", this.nev);
        l.put("kor", this.kor);
        l.put("orszag", this.orszag);
        l.put("kepessegiSzint", this.kepessegiSzint);
        return l;
    }
    
    public static List<Hegymaszok> getOsszesFelnottHegymaszoByNevsor (EntityManager em){
        //üres lista amit returnolunk majd.
        List<Hegymaszok> hegymaszok = new ArrayList();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getOsszesFelnottHegymaszoByNevsor");
        // MIndig object tömböket tartalamzó listát ad vissza  a select
        List<Object[]> lista = spq.getResultList(); // execute + a visszatérési adatok lekérése
        for(Object[] elem2 : lista){
            //kor feltétel
            
            //minden rekord elsődleges kulcsát kell kinyerni
            int id = Integer.parseInt(elem2[0].toString());
            // elsődleges kulcs alapján történő példányosítás
            Hegymaszok m = em.find(Hegymaszok.class, id);
            //példány hozzáadása a returnölendő listához
            hegymaszok.add(m);
        }
        return hegymaszok;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHegymaszo != null ? idHegymaszo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hegymaszok)) {
            return false;
        }
        Hegymaszok other = (Hegymaszok) object;
        if ((this.idHegymaszo == null && other.idHegymaszo != null) || (this.idHegymaszo != null && !this.idHegymaszo.equals(other.idHegymaszo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Hegymaszok[ idHegymaszo=" + idHegymaszo + " ]";
    }
    
}
