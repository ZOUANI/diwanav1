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
public class Accuse {

    private String referenceBonSortie;
    private String code;
    private String description;

    public String getReferenceBonSortie() {
        return referenceBonSortie;
    }

    public void setReferenceBonSortie(String referenceBonSortie) {
        this.referenceBonSortie = referenceBonSortie;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "referenceBonSortie=" + referenceBonSortie + ", code=" + code + ", description=" + description;
    }

}
