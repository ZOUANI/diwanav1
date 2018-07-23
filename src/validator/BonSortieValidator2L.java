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
public class BonSortieValidator2L {

    private List<String> codeTypeContenant = Arrays.asList("000", "001", "010", "020", "030", "040", "050", "060");

    public void validate2L(BonSortie bonSortie, boolean isActive, boolean ack, TYPE_FILE type_file) {
        if (!isActive) {
            return;
        }
        BonSortieMessageManager2L.clearErrors2L();
        if (ack) {
            BonSortieMessageManager2L.putErrorMessage(validateReferenceBonSortie(ack, bonSortie));
        } else {
            BonSortieMessageManager2L.putErrorMessage(validateFonctionMessage(bonSortie, type_file));
            BonSortieMessageManager2L.putErrorMessage(validateTypeDs(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validateLieuChargement(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validateLotDedouanement(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validateTypeContenant(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validateNombreContenant(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validateMarqueMarchandise(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validatePoidNet(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validateTare(bonSortie));
            BonSortieMessageManager2L.putErrorMessage(validatePoidBrute(bonSortie));
        }
    }

    private Object[] validateReferenceBonSortie(boolean ack, BonSortie bonSortie) {
        int res;
        if (ack && bonSortie.getAccuse().getCode() != null && bonSortie.getAccuse().getCode().equals("OK")) {
            res = StringUtil.isNotEmpty(bonSortie.getAccuse().getReferenceBonSortie());
        } else {
            res = 2;
        }
        return new Object[]{res, "referenceBonSortie"};
    }

    private Object[] validateTypeDs(BonSortie bonSortie) {
        return isValide(bonSortie, bonSortie.getBon().getTypeDS(), "typeDs");
    }

    private Object[] validateLieuChargement(BonSortie bonSortie) {
        return isValide(bonSortie, bonSortie.getBon().getLieuChargement(), "lieuChargement");
    }

    private Object[] validateLotDedouanement(BonSortie bonSortie) {
        return isValide(bonSortie, bonSortie.getBon().getReferenceLot(), "lotDedouanement");
    }

    private Object[] validateTypeContenant(BonSortie bonSortie) {
        return isValide(bonSortie, bonSortie.getBon().getTypeContenant(), "typeContenant");
    }

    private Object[] validateNombreContenant(BonSortie bonSortie) {
        int index = codeTypeContenant.indexOf(bonSortie.getBon().getTypeContenant());
        int res;
        if (index == -1) {
            int notEmpty = StringUtil.isNotEmpty(bonSortie.getBon().getNombreContenant());
            if (notEmpty > 0) {
                res = NumberUtil.convertToBigInteger(bonSortie.getBon().getNombreContenant(), 0, false).compareTo(new BigInteger("0")) != 0 ? 1 : -2;
            } else {
                res = notEmpty;
            }
        } else {
            res = 2;
        }
        return new Object[]{res, "nombreContenant"};
    }

    private Object[] validateMarqueMarchandise(BonSortie bonSortie) {
        int res;
        if (bonSortie.getBon().getReferenceDS() != null) {
            res = StringUtil.isNotEmpty(bonSortie.getBon().getMarqueMarchandise());
        } else {
            res = 2;
        }
        return new Object[]{res, "marqueMarchandise"};

    }

    private Object[] validatePoidNet(BonSortie bonSortie) {
        String poidBrut = bonSortie.getBon().getPoidsBrut();
        String tare = bonSortie.getBon().getTare();
        String poidsNet = bonSortie.getBon().getPoidsNet();
        int res;
        int isMandatory = StringUtil.validateUsingCondition((validateNombreContenantAndMlv(bonSortie))
                && (StringUtil.isEmpty(poidBrut) && StringUtil.isEmpty(tare)));
        if (isMandatory > 0) {
            res = StringUtil.isNotEmpty(poidsNet);
        } else {
            res = 2;
        }
        return new Object[]{res, "poidsNet"};
    }

    private Object[] validateTare(BonSortie bonSortie) {
        return new Object[]{validateTareOrPoidBrute(bonSortie, bonSortie.getBon().getTare()), "tare"};
    }

    private Object[] validatePoidBrute(BonSortie bonSortie) {
        return new Object[]{validateTareOrPoidBrute(bonSortie, bonSortie.getBon().getPoidsBrut()), "poidsBrut"};
    }

    private Object[] validateFonctionMessage(BonSortie bonSortie, TYPE_FILE type_file) {
        BigInteger myValue = NumberUtil.convertToBigInteger(bonSortie.getHeader().getFonctionMessage(), 1, true);
        int res = -2;
        if (myValue != null) {
            if (type_file == TYPE_FILE.CREATE) {
                res = (myValue.compareTo(new BigInteger("1")) == 0 ? 1 : -1);
            } else if (type_file == TYPE_FILE.EDIT) {
                res = myValue.compareTo(new BigInteger("2")) == 0 ? 1 : -1;
            } else if (type_file == TYPE_FILE.REMOVE) {
                res = myValue.compareTo(new BigInteger("3")) == 0 ? 1 : -1;
            }
        }
        return new Object[]{res, "fonctionMessage"};
    }

    /**
     * *****************************
     */
    private boolean validateNombreContenantAndMlv(BonSortie bonSortie) {
        String nombreContenant = bonSortie.getBon().getNombreContenant();
        String mlvPesage = bonSortie.getBon().getMlvPesage();
        boolean res = (StringUtil.isNotEmpty(nombreContenant) == 1 || (mlvPesage != null && mlvPesage.equalsIgnoreCase("oui")));
        return res;
    }

    private int validateTareOrPoidBrute(BonSortie bonSortie, String tareOrPoidBrute) {
        String poidsNet = bonSortie.getBon().getPoidsNet();
        int mandatory = StringUtil.validateUsingCondition((validateNombreContenantAndMlv(bonSortie)) && (StringUtil.isEmpty(poidsNet)));
        if (mandatory > 0) {
            return StringUtil.isNotEmpty(tareOrPoidBrute);
        }
        return 2;
    }


    private Object[] isValide(BonSortie bonSortie, String element, String attributeName) {
        String referenceDs = bonSortie.getBon().getReferenceDS();
        return BonSortieValidator.isValideAndMandatoryExist(referenceDs, element, attributeName);
    }

}
