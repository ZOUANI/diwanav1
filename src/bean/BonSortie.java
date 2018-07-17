/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author YOUNES
 */
@Entity
public class BonSortie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private BonSortieHeader bonSortieHeader;
    @ManyToOne
    private Operateur operateur;
    @ManyToOne
    private Dum dum;
    @ManyToOne
    private Ds ds;
    @ManyToOne
    private LotDedouanement lotDedouanement;
    @ManyToOne
    private LieuChargement lieuChargement;
    @ManyToOne
    private TypeContenant typeContenant;
    private String nombreContenant;
    @ManyToOne
    private MarqueMarchandise marqueMarchandise;
    private String immatriculationVehicule;
    private BigDecimal poidNet;
    private BigDecimal poidBrute;
    private BigDecimal tare;
    @OneToMany(mappedBy = "bonSortie")
    private List<BonSortieEquipement> bonSortieEquipements;

    public List<BonSortieEquipement> getBonSortieEquipements() {
        return bonSortieEquipements;
    }

    public void setBonSortieEquipements(List<BonSortieEquipement> bonSortieEquipements) {
        this.bonSortieEquipements = bonSortieEquipements;
    }

    public BonSortieHeader getBonSortieHeader() {
        return bonSortieHeader;
    }

    public void setBonSortieHeader(BonSortieHeader bonSortieHeader) {
        this.bonSortieHeader = bonSortieHeader;
    }

    public Operateur getOperateur() {
        return operateur;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }

    public Dum getDum() {
        return dum;
    }

    public void setDum(Dum dum) {
        this.dum = dum;
    }

    public Ds getDs() {
        return ds;
    }

    public void setDs(Ds ds) {
        this.ds = ds;
    }

    public LotDedouanement getLotDedouanement() {
        return lotDedouanement;
    }

    public void setLotDedouanement(LotDedouanement lotDedouanement) {
        this.lotDedouanement = lotDedouanement;
    }

    public LieuChargement getLieuChargement() {
        return lieuChargement;
    }

    public void setLieuChargement(LieuChargement lieuChargement) {
        this.lieuChargement = lieuChargement;
    }

    public TypeContenant getTypeContenant() {
        return typeContenant;
    }

    public void setTypeContenant(TypeContenant typeContenant) {
        this.typeContenant = typeContenant;
    }

    public String getNombreContenant() {
        return nombreContenant;
    }

    public void setNombreContenant(String nombreContenant) {
        this.nombreContenant = nombreContenant;
    }

    public MarqueMarchandise getMarqueMarchandise() {
        return marqueMarchandise;
    }

    public void setMarqueMarchandise(MarqueMarchandise marqueMarchandise) {
        this.marqueMarchandise = marqueMarchandise;
    }

    public String getImmatriculationVehicule() {
        return immatriculationVehicule;
    }

    public void setImmatriculationVehicule(String immatriculationVehicule) {
        this.immatriculationVehicule = immatriculationVehicule;
    }

    public BigDecimal getPoidNet() {
        return poidNet;
    }

    public void setPoidNet(BigDecimal poidNet) {
        this.poidNet = poidNet;
    }

    public BigDecimal getPoidBrute() {
        return poidBrute;
    }

    public void setPoidBrute(BigDecimal poidBrute) {
        this.poidBrute = poidBrute;
    }

    public BigDecimal getTare() {
        return tare;
    }

    public void setTare(BigDecimal tare) {
        this.tare = tare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonSortie)) {
            return false;
        }
        BonSortie other = (BonSortie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.BonSortie[ id=" + id + " ]";
    }

}
