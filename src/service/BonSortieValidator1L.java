/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import vo.Equipement;
import java.util.Arrays;
import java.util.List;
import util.DateUtil;
import util.NumberUtil;
import util.StringUtil;
import vo.BonSortie;

/**
 *
 * @author YOUNES
 */
public class BonSortieValidator1L {

    private List<String> codeFonctionMessage = Arrays.asList("1", "2", "3");
    private List<String> typeDeclarationDS = Arrays.asList("01", "03", "04", "05", "06", "07");
    // private List<String> codeLieuChargement = Arrays.asList("UN", "LOCODE");????? c est logique que deux code de lieu de cahrgement??

    public void valiadte1L(BonSortie bonSortie, boolean isActive, boolean ack, BonSortieValidator.TYPE_FILE type_file) {
        if (!isActive) {
            return;
        }
        BonSortieMessageManager1L.clearErrors1L();
        BonSortieMessageManager1L.putErrorMessage(validateNumeroMessage(bonSortie));
        BonSortieMessageManager1L.putErrorMessage(validateDateMessage(bonSortie));
        if (ack) {
            BonSortieMessageManager1L.putErrorMessage(validateCodeAccuse(bonSortie));
            BonSortieMessageManager1L.putErrorMessage(validateDescriptionAccuse(bonSortie));
            BonSortieMessageManager1L.putErrorMessage(validateReferenceBonSortie(bonSortie));
        } else {
            BonSortieMessageManager1L.putErrorMessage(validateFonctionMessage(bonSortie));
            BonSortieMessageManager1L.putErrorMessage(validateNumeroRc(bonSortie));
            BonSortieMessageManager1L.putErrorMessage(validateCentreRc(bonSortie));
            BonSortieMessageManager1L.putErrorMessage(validateReferenceDum(bonSortie));
            if (type_file == BonSortieValidator.TYPE_FILE.REMOVE || type_file == BonSortieValidator.TYPE_FILE.EDIT) {
                BonSortieMessageManager1L.putErrorMessage(validateReferenceBonSortie(bonSortie));
            } else if (type_file == BonSortieValidator.TYPE_FILE.CREATE || type_file == BonSortieValidator.TYPE_FILE.EDIT) {
                BonSortieMessageManager1L.putErrorMessage(validateTypeDs(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateReferenceDs(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateLotDedouanement(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateLieuChargement(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateTypeContenant(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateNombreContenant(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateImmatrculationsVehicules(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateMarqueMarchandise(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validatePoidsNet(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateTare(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validatePoidsBrut(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateReferenceEquipement(bonSortie));
                BonSortieMessageManager1L.putErrorMessage(validateCodeTypeEquipement(bonSortie));
            }
        }
    }

    private Object[] validateNumeroMessage(BonSortie bonSortie) {
        String numeroMessage = bonSortie.getHeader().getNumeroMessage();
        return validateExistMandatoryStyle(numeroMessage,
                NumberUtil.convertToLongAsBool(bonSortie.getHeader().getNumeroMessage(), 19, false),
                "numeroMessage");
    }

    private Object[] validateDateMessage(BonSortie bonSortie) {
        String dateMessage = bonSortie.getHeader().getDateMessage();
        return validateExistMandatoryStyle(dateMessage,
                DateUtil.parse(bonSortie.getHeader().getDateMessage().replace("T", " ")) != null,
                "dateMessage");
    }

    private Object[] validateFonctionMessage(BonSortie bonSortie) {
        String fonctionMessage = bonSortie.getHeader().getFonctionMessage();
        return validateExistMandatoryStyle(fonctionMessage, codeFonctionMessage.indexOf(bonSortie.getHeader().getFonctionMessage()) != -1, "fonctionMessage");
    }

    private Object[] validateNumeroRc(BonSortie bonSortie) {
        return new Object[]{NumberUtil.convertToLongAsInt(bonSortie.getOperateur().getNumeroRC()), "numeroRc"};
    }

    private Object[] validateCentreRc(BonSortie bonSortie) {
        return new Object[]{NumberUtil.convertToLongAsInt(bonSortie.getOperateur().getCentreRC()), "centreRc"};
    }

    private Object[] validateReferenceDum(BonSortie bonSortie) {
        return new Object[]{NumberUtil.convertToLongAsInt(bonSortie.getDum().getReference(), 17, true), "referenceDum"};
    }

    private Object[] validateTypeDs(BonSortie bonSortie) {
        String typeDs = bonSortie.getBon().getTypeDS();
        return validateExistNotMandatoryStyle(typeDs, typeDeclarationDS.indexOf(typeDs) != -1, "typeDs");
    }

    private Object[] validateReferenceDs(BonSortie bonSortie) {
        String referenceDS = (bonSortie.getBon().getReferenceDS());
        return validateExistNotMandatoryStyle(referenceDS, NumberUtil.convertToLongAsBool(referenceDS, 17, true), "referenceDS");
    }

    private Object[] validateLotDedouanement(BonSortie bonSortie) {
        String lotDedouanement = bonSortie.getBon().getReferenceLot();
        return validateExistNotMandatoryStyle(lotDedouanement, (lotDedouanement.length() <= 10), "lotDedouanement");
    }

// UN LOCODE ????
    private Object[] validateLieuChargement(BonSortie bonSortie) {
        String lieuChargement = bonSortie.getBon().getLieuChargement();
        return validateExistNotMandatoryStyle(lieuChargement, (lieuChargement.length() <= 10), "lieuChargement");

    }

    private Object[] validateTypeContenant(BonSortie bonSortie) {
        String typeContenant = bonSortie.getBon().getTypeContenant();
        return validateExistNotMandatoryStyle(typeContenant, (typeContenant.length() <= 30), "typeContenant");
    }

    private Object[] validateNombreContenant(BonSortie bonSortie) {
        String nombreContenant = bonSortie.getBon().getNombreContenant();
        return validateExistNotMandatoryStyle(nombreContenant, (NumberUtil.convertToLongAsBool(nombreContenant, 10, false)), "nombreContenant");
    }

    private Object[] validateMarqueMarchandise(BonSortie bonSortie) {
        String marqueMarchandise = bonSortie.getBon().getMarqueMarchandise();
        return validateExistNotMandatoryStyle(marqueMarchandise, marqueMarchandise.length() <= 30, "marqueMarchandise");
    }

    private Object[] validateImmatrculationsVehicules(BonSortie bonSortie) {
        String immatrculationsVehicules = bonSortie.getBon().getImmatrculationsVehicules();
        return validateExistNotMandatoryStyle(immatrculationsVehicules, immatrculationsVehicules.length() <= 30, "immatrculationsVehicules");
    }

    private Object[] validatePoidsNet(BonSortie bonSortie) {
        String poidsNet = bonSortie.getBon().getPoidsNet();
        return validateExistNotMandatoryStyle(poidsNet, (NumberUtil.convertToDecimalAsBool(poidsNet)), "poidsNet");
    }

    private Object[] validateTare(BonSortie bonSortie) {
        String tare = bonSortie.getBon().getTare();
        return validateExistNotMandatoryStyle(tare, (NumberUtil.convertToDecimalAsBool(tare)), "tare");
    }

    private Object[] validatePoidsBrut(BonSortie bonSortie) {
        String poidsBrut = bonSortie.getBon().getPoidsBrut();
        return validateExistNotMandatoryStyle(poidsBrut, (NumberUtil.convertToDecimalAsBool(poidsBrut)), "poidsBrut");
    }

    private Object[] validateReferenceEquipement(BonSortie bonSortie) {
        return new Object[]{(validateEquipements(bonSortie) != -1 ? 1 : -1), "referenceEquipement"};
    }

    private Object[] validateCodeTypeEquipement(BonSortie bonSortie) {
        return new Object[]{validateEquipements(bonSortie) != -2 ? 1 : -1, "codeTypeEquipement"};
    }

    private Object[] validateCodeAccuse(BonSortie bonSortie) {
        String codeAccuse = bonSortie.getAccuse().getCode();
        int lenght = codeAccuse.length();
        return validateExistMandatoryStyle(codeAccuse, (lenght == 2 || lenght == 3), "codeAccuse");
    }

    private Object[] validateDescriptionAccuse(BonSortie bonSortie) {
        String descriptionAccuse = bonSortie.getAccuse().getDescription();
        return validateExistMandatoryStyle(descriptionAccuse, (descriptionAccuse.length() <= 256), "descriptionAccuse");
    }

    private Object[] validateReferenceBonSortie(BonSortie bonSortie) {
        String bonReference = bonSortie.getBon().getReferenceBon();
        return validateExistMandatoryStyle(bonReference, NumberUtil.convertToBigInteger(bonReference, 22, true) != null,
                "referenceBonSortie");
    }

    private int validateEquipements(BonSortie bonSortie) {
        List<Equipement> equipements = bonSortie.getBon().getEquipements();
        if (equipements.isEmpty()) {
            return 1;
        }
        for (Equipement equipement : equipements) {
            int validation = validateEquipement(equipement);
            if (validation < 0) {
                return validation;
            }
        }
        return 2;
    }

    private int validateEquipement(Equipement equipement) {
        String ref = equipement.getReferenceEquipement();
        String type = equipement.getTypeEquipement();
        if (ref == null || ref.length() > 30) {
            return -1;
        }
        if (type == null || type.length() > 30) {
            return -2;
        }
        return 1;
    }

    public static Object[] validateExistMandatoryStyle(String attribute, boolean condition, String attributeNameMessage) {
        return BonSortieValidator.validateExistMandatoryStyle(attribute, condition, attributeNameMessage);
    }

    public static Object[] validateExistNotMandatoryStyle(String attribute, boolean condition, String attributeNameMessage) {
        return BonSortieValidator.validateExistNotMandatoryStyle(attribute, condition, attributeNameMessage);
    }

}
