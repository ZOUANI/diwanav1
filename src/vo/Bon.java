/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author YOUNES
 */
public class Bon {

    private String referenceBon;
    private String typeContenant;
    private String nombreContenant;
    private String marqueMarchandise;
    private String immatrculationsVehicules;
    private String poidsNet;
    private String typeDS;
    private String referenceDS;
    private String referenceLot;
    private String lieuChargement;
    private String poidsBrut;
    private String mlvPesage;
    private String tare;
    private List<Equipement> equipements= new ArrayList();

    public String getMlvPesage() {
        return mlvPesage;
    }

    public void setMlvPesage(String mlvPesage) {
        this.mlvPesage = mlvPesage;
    }

    public String getReferenceBon() {
        return referenceBon;
    }

    public void setReferenceBon(String referenceBon) {
        this.referenceBon = referenceBon;
    }
    @XmlElement(name = "equipement")
    public List<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }

    public String getTypeDS() {
        return typeDS;
    }

    public void setTypeDS(String typeDS) {
        this.typeDS = typeDS;
    }

    public String getReferenceDS() {
        return referenceDS;
    }

    public void setReferenceDS(String referenceDS) {
        this.referenceDS = referenceDS;
    }

    public String getReferenceLot() {
        return referenceLot;
    }

    public void setReferenceLot(String referenceLot) {
        this.referenceLot = referenceLot;
    }

    public String getLieuChargement() {
        return lieuChargement;
    }

    public void setLieuChargement(String lieuChargement) {
        this.lieuChargement = lieuChargement;
    }

    public String getPoidsBrut() {
        return poidsBrut;
    }

    public void setPoidsBrut(String poidsBrut) {
        this.poidsBrut = poidsBrut;
    }

    public String getTare() {
        return tare;
    }

    public void setTare(String tare) {
        this.tare = tare;
    }

    public String getTypeContenant() {
        return typeContenant;
    }

    public void setTypeContenant(String typeContenant) {
        this.typeContenant = typeContenant;
    }

    public String getNombreContenant() {
        return nombreContenant;
    }

    public void setNombreContenant(String nombreContenant) {
        this.nombreContenant = nombreContenant;
    }

    public String getMarqueMarchandise() {
        return marqueMarchandise;
    }

    public void setMarqueMarchandise(String marqueMarchandise) {
        this.marqueMarchandise = marqueMarchandise;
    }

    public String getImmatrculationsVehicules() {
        return immatrculationsVehicules;
    }

    public void setImmatrculationsVehicules(String immatrculationsVehicules) {
        this.immatrculationsVehicules = immatrculationsVehicules;
    }

    public String getPoidsNet() {
        return poidsNet;
    }

    public void setPoidsNet(String poidsNet) {
        this.poidsNet = poidsNet;
    }

    @Override
    public String toString() {
        return "Bon{" + "referenceBon=" + referenceBon + ", typeContenant=" + typeContenant + ", nombreContenant=" + nombreContenant + ", marqueMarchandise=" + marqueMarchandise + ", immatrculationsVehicules=" + immatrculationsVehicules + ", poidsNet=" + poidsNet + ", typeDS=" + typeDS + "\n, referenceDS=" + referenceDS + ", referenceLot=" + referenceLot + ", lieuChargement=" + lieuChargement + ", poidsBrut=" + poidsBrut + ", mlvPesage=" + mlvPesage + ", tare=" + tare + "\n, equipements=" + equipements + '}';
    }

   

}
