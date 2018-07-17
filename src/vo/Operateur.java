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
public class Operateur {

    private String numeroRC;
    private String centreRC;

    public String getNumeroRC() {
        return numeroRC;
    }

    public void setNumeroRC(String numeroRC) {
        this.numeroRC = numeroRC;
    }

    public String getCentreRC() {
        return centreRC;
    }

    public void setCentreRC(String centreRC) {
        this.centreRC = centreRC;
    }

    @Override
    public String toString() {
        return "numeroRC=" + numeroRC + ", centreRC=" + centreRC;
    }

}
