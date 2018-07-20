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
    // is mlv avec pesage ==> serviceConsomme de eci_lib
    //*********************************
    @ManyToOne
    private BonSortieHeader bonSortieHeader;// n'existe pas mais dateHeureBs=dateMessage + pas besoin de stock le num de message?
    @ManyToOne
    private Accuse accuse;// n'existe pas
    @ManyToOne
    private TypeContenant typeContenant;// ?????? lot dedouan -> lignelot -> typeContenant??

    //**************************************
    @ManyToOne
    private Dum dum;// ?? ListBonSortie -> idDeclarationEnDouane
    private String marqueMarchandise;//c est marque ???
    private int numeroLot;//??? to be deduced from idLot
    @ManyToOne
    private Operateur operateur;//ok
    @ManyToOne
    private Ds ds;//ok Declaration Dossier mais idDossierDeclaration simplement
    @ManyToOne
    private LieuChargement lieuChargement;//ok
    private String nombreContenant; //ok
    private String immatriculationVehicule;//ok
    private BigDecimal poidNet;//ok
    private BigDecimal poidBrute;//ok
    private BigDecimal tare;//ok
    @OneToMany(mappedBy = "bonSortie")
    private List<BonSortieEquipement> bonSortieEquipements;//ok equipementBS 

    public List<BonSortieEquipement> getBonSortieEquipements() {
        return bonSortieEquipements;
    }

    public Accuse getAccuse() {
        return accuse;
    }

    public void setAccuse(Accuse accuse) {
        this.accuse = accuse;
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
