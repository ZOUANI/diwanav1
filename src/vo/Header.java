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
public class Header {

    private String numeroMessage;
    private String dateMessage;
    private String fonctionMessage;

    public String getNumeroMessage() {
        return numeroMessage;
    }

    public void setNumeroMessage(String numeroMessage) {
        this.numeroMessage = numeroMessage;
    }

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(String dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getFonctionMessage() {
        return fonctionMessage;
    }

    public void setFonctionMessage(String fonctionMessage) {
        this.fonctionMessage = fonctionMessage;
    }

    @Override
    public String toString() {
        return "numeroMessage=" + numeroMessage + ", dateMessage=" + dateMessage + ", fonctionMessage=" + fonctionMessage ;
    }

    
}
