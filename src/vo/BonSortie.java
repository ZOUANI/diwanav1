/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YOUNES
 */
@XmlRootElement
public class BonSortie {

    private Bon bon;
    private Dum dum;
    private Header header;
    private Operateur operateur;
    private Accuse accuse;

    @XmlElement
    public Bon getBon() {
        if (bon == null) {
            bon = new Bon();
        }
        return bon;
    }

    @XmlElement
    public Accuse getAccuse() {
        if (accuse == null) {
            accuse = new Accuse();
        }
        return accuse;
    }

    public void setAccuse(Accuse accuse) {
        this.accuse = accuse;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    @XmlElement
    public Dum getDum() {
        if (dum == null) {
            dum = new Dum();
        }
        return dum;
    }

    public void setDum(Dum dum) {
        this.dum = dum;
    }

    @XmlElement
    public Header getHeader() {
        if (header == null) {
            header = new Header();
        }
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlElement
    public Operateur getOperateur() {
        if (operateur == null) {
            operateur = new Operateur();
        }
        return operateur;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }

    @Override
    public String toString() {
        return "BonSortie{\n" + "bonVo=" + bon + "\n, dumVo=" + dum + 
                "\n, headerVo=" + header + "\n, operateurVo=" + operateur +
                ", accuse="+accuse+"\n}";
    }

}
