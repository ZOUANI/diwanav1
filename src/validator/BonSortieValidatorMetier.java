/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import validator.BonSortieValidator.TYPE_FILE;
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
        //ok ref_lib.services.ipml.ServiceOffert.findByNumeroRcAndCentreRC
        return null;
    }

    private Object[] validateOperateurValide(BonSortie bonSortie) {
        //OK ref_lib.services.ipml.ServiceOffert.isOperateurValide
        return null;
    }

    private Object[] validateExistanceDum(BonSortie bonSortie) {
        //OK_LITTLE ded_lib.service.ipml.ServiceOffert.recupererDeclarationEnDouane
        return null;
    }

    private Object[] validateDumNonAnnulee(BonSortie bonSortie) {
        //OK_LITTLE ded_lib.service.ipml.ServiceOffert.recupererDeclarationEnDouane + check annulee boolean
        return null;
    }

    private Object[] validateReceptionDumMlv(BonSortie bonSortie) {
        //OK_LITTLE mlv_lib.service.ipml.ServiceOffert.isMainLeveDedouanementOctroyee
        return null;
    }

    private Object[] validateDsExist(BonSortie bonSortie) {
        //NO ??_lib.service.ipml.ServiceOffert.getDs ==> refDs+ typeDs
        return null;
    }

    private Object[] validateDsNonAnnulee(BonSortie bonSortie) {
        //NO ??_lib.service.ipml.ServiceOffert.getDs + non annule
        return null;
    }

    private Object[] validateLotDedouanementExist(BonSortie bonSortie) {
        // idLot c est id ==> read
        //OK med_lib.service.ipml.ServiceLocal.findLotByNumeroAndIdDs
        return null;
    }

    /*
    La DUM doit apurer le lot de dédouanement 
    ???? apurementDUM
    */
    private Object[] validateLotDedouanementNonAppureParLot(BonSortie bonSortie) {
        //????
        return null;
    }

    private Object[] validateCodeTypeContenantExist(BonSortie bonSortie) {
        // OKKK code c id ==> read
        // Ref_Lib ==> typeDsDao and add findByCodeTypeDS apres findAll
        return null;
    }

    /*
    Si la référence de la DS est fournie, le nombre de contenants des bons de sortie 
    attachés à la DUM ne doit pas dépasser le nombre de contenants du lot (y compris
    celui en cours de traitement). prob : no Bs in dum as i expected
     */
    private Object[] validateNbrTotalContenantsBsSupNbrContenantLot(BonSortie bonSortie) {
        // NOOO load lot first
        //sigma (dum.list_bs.nombreContenant) >= lot.nombreContenantLot+lotEncours.nombreContenantLot ???
        return null;
    }

    /*
    Si la référence de la DS n’est pas fournie, le nombre de contenants des bons de 
    sortie attachés à la DUM ne doit pas dépasser le nombre de contenants déclaré dans la
    DUM (y compris celui en cours de traitement.
    prob : no Bs in dum as i expected + dum n'a pas un champ nombreContenant
     */
    private Object[] validateNbrTotalContenantsBsSupNbrContenantDum(BonSortie bonSortie) {
        //NO !!! load lot first
        //sigma (dum.list_bs.nombreContenant)>= dum.????
        return null;
    }

    /*
    Si la DS est fournie, le poids net total des contenants des bons de sortie attachés à
    la DUM ne doit pas dépasser le poids net  du lot (y compris celui en cours de  traitement)
    prob : 
     */
    private Object[] validatePoidsNetTotalContenantBsSupPoidsNetLot(BonSortie bonSortie) {
        //NO 
        //sigma (dum.list_bs.poidsNet)>= lot.poidsLot????
        return null;
    }

    /*
    Si la référence de la DS n’est pas fournie, le poids net total des bons de sortie attachés
    à la DUM ne doit pas dépasser le poids net total déclaré dans la DUM (y compris celui en cours de traitement)
    prob : 
     */
    private Object[] validatePoidsNetTotalBsSupPoidsNetDum(BonSortie bonSortie) {
        //NO 
        //sigma (dum.list_bs.poidsNet)>= lot.poidsLot????
        return null;
    }

    /*
    L’équipement, défini par son type et sa référence, doit exister au niveau du lot 
    prob : in lot i cant find equipement or list or even a class called equiepementLot
     */
    private Object[] validateEquipementDsExistLot(BonSortie bonSortie) {
        return null;
    }

}
