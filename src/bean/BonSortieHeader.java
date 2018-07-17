/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author YOUNES
 */
@Entity
public class BonSortieHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal numeroMessage;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateMessage;
    private char fonctionMessage;

    
    public BigDecimal getNumeroMessage() {
        return numeroMessage;
    }

    public void setNumeroMessage(BigDecimal numeroMessage) {
        this.numeroMessage = numeroMessage;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public char getFonctionMessage() {
        return fonctionMessage;
    }

    public void setFonctionMessage(char fonctionMessage) {
        this.fonctionMessage = fonctionMessage;
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
        if (!(object instanceof BonSortieHeader)) {
            return false;
        }
        BonSortieHeader other = (BonSortieHeader) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.BonSortieHeader[ id=" + id + " ]";
    }

}
