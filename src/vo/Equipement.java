/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author YOUNES
 */
public class Equipement {

    private String referenceEquipement;
    private String typeEquipement;

    public String getReferenceEquipement() {
        return referenceEquipement;
    }

    public void setReferenceEquipement(String referenceEquipement) {
        this.referenceEquipement = referenceEquipement;
    }

    public String getTypeEquipement() {
        return typeEquipement;
    }

    public void setTypeEquipement(String typeEquipement) {
        this.typeEquipement = typeEquipement;
    }

    @Override
    public String toString() {
        return "referenceEquipement=" + referenceEquipement + ", typeEquipement=" + typeEquipement;
    }

}
