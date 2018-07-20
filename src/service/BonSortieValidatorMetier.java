/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import service.BonSortieValidator.TYPE_FILE;
import util.NumberUtil;
import util.StringUtil;
import vo.BonSortie;

/**
 *
 * @author YOUNES
 */
public class BonSortieValidatorMetier {

    public void validateMetier(BonSortie bonSortie, boolean isActive, boolean ack, TYPE_FILE type_file) {
        if (!isActive) {
            return;
        }
        BonSortieMessageManagerMetier.clearErrorsMetier();
        if (ack) {
            //    BonSortieMessageManagerMetier.putErrorMessage(validatePoidsNetTotalContenants(ack, bonSortie));
        } else {
//            BonSortieMessageManager2L.putErrorMessage(validateFonctionMessage(bonSortie, type_file));
//            BonSortieMessageManager2L.putErrorMessage(validateTypeDs(bonSortie));
//            BonSortieMessageManager2L.putErrorMessage(validateLieuChargement(bonSortie));

        }
    }

    private Object[] validateNumeroAndCentreRcExist(BonSortie bonSortie) {
        //ok operateurDao
        return null;
    }

    private Object[] validateOperateurValide(BonSortie bonSortie) {
    //OK ref_lib.services.ipml.ServiceOffert.isOperateurValide
            return null;
    }

    private Object[] validateExistanceDum(BonSortie bonSortie) {
    //??
        return null;
    }

    private Object[] validateDumNonAnnulee(BonSortie bonSortie) {
    //??
        return null;
    }

    private Object[] validateReceptionDumMlv(BonSortie bonSortie) {
    //
        return null;
    }

    private Object[] validateDsExist(BonSortie bonSortie) {
    //
        return null;
    }

    private Object[] validateDsNonAnnulee(BonSortie bonSortie) {
    //
        return null;
    }

    private Object[] validateLotDedouanementExist(BonSortie bonSortie) {
    // idLot c est id ==> read
        return null;
    }

    private Object[] validateLotDedouanementNonAppureParLot(BonSortie bonSortie) {

        return null;
    }

    private Object[] validateCodeTypeContenantExist(BonSortie bonSortie) {
        // code c id ==> read
        return null;
    }

    private Object[] validateNbrTotalContenantsBsSupNbrContenantLot(BonSortie bonSortie) {
        return null;
    }

    private Object[] validateNbrTotalContenantsBsSupNbrContenantDum(BonSortie bonSortie) {
        return null;
    }

    private Object[] validatePoidsNetTotalContenantBsSupPoidsNetDum(BonSortie bonSortie) {
        return null;
    }

    private Object[] validateEquipementDsExistLot(BonSortie bonSortie) {
        return null;
    }

}
