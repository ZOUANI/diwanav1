/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import util.MyValidationEventHandler;
import util.StringUtil;
import vo.BonSortie;

/**
 *
 * @author YOUNES
 */
public class BonSortieValidator {

    private JaxbBonSortie JaxbBonSortie = new JaxbBonSortie();
    private List<String> typeDeclarationDS = Arrays.asList("01", "03", "04", "05", "06", "07");
    private List<String> codeLieuChargement = Arrays.asList("UN", "LOCODE");
    private List<String> codeTypeContenant = Arrays.asList("000", "001", "010", "020", "030", "040", "050", "060");

    public Object[] validate(String absolutePath, String xmlPath, String xsdPath, int typeValidate) throws JAXBException, SAXException {
        if (typeValidate == 1) {
            return validateCreate(absolutePath, xmlPath, xsdPath);
        } else if (typeValidate == 2) {
            return validateEdit(absolutePath, xmlPath, xsdPath);
        } else if (typeValidate == 3) {
            return validateRemove(absolutePath, xmlPath, xsdPath);
        } else if (typeValidate == 4) {
            return validateAck(absolutePath, xmlPath, xsdPath);
        } else {
            return new Object[]{-5, BonSortieMessageManager.getTypeActionNotValidMessage()};
        }
    }

    private Object[] validateCreate(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, true, true);
    }

    private Object[] validateEdit(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, true, true);
    }

    private Object[] validateAck(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, false, true);
    }

    private Object[] validateRemove(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, false, false);
    }

    private Object[] validateAbstract(String absoluteXmlPath, String absoluteXsd, String xmlPath,
            String xsdPath, boolean activateRefDumAndDate, boolean activateSecondLevel)
            throws JAXBException, SAXException {
        Object[] resValidateXmlFileName = splitXmlFileName(xmlPath);
        if (new Integer(resValidateXmlFileName[0] + "") < 0) {
            return new Object[]{-1, BonSortieMessageManager.getXmlFileNameNotValidMessage()};
        }
        BonSortie bonSortie = new JaxbBonSortie().unmarshallAndValiadte(absoluteXmlPath + xmlPath, absoluteXsd + xsdPath);
        if (!MyValidationEventHandler.getXsdErrors().isEmpty()) {
            return new Object[]{-2, MyValidationEventHandler.getXsdErrors()};
        } else if (!validateRefDumAndDate(resValidateXmlFileName, bonSortie, activateRefDumAndDate)) {
            return new Object[]{-3, BonSortieMessageManager.getXmlFileNameAndFileContentNotValidMessage()};
        }
        validateSecondLevel(bonSortie, activateSecondLevel, (new Integer(resValidateXmlFileName[0] + "") == 1));
        if (!BonSortieMessageManager.getErrors().isEmpty()) {
            return new Object[]{-4, BonSortieMessageManager.getErrors()};
        } else {
            return new Object[]{1, bonSortie};
        }
    }

    private boolean validateRefDumAndDate(Object[] resValidateXmlFileName, BonSortie bonSortie, boolean isActive) {
        if (!isActive) {
            return true;
        }
        String referenceDum = (resValidateXmlFileName[2] + "").split("\\.")[0];
        String rowDate = (resValidateXmlFileName[1] + "");
        boolean resRefDum = bonSortie.getDum().getReference().equals(referenceDum);
        boolean resDate = compareRowDateWithFormattedDate(rowDate, bonSortie.getHeader().getDateMessage());
        return resRefDum && resDate;
    }

    public boolean compareRowDateWithFormattedDate(String rowDate, String formattedDate) {
        String[] resFormated = formattedDate.split("T");
        String yMd = resFormated[0];
        String hms = resFormated[1];
        yMd = yMd.replace("-", "");
        hms = hms.replace(":", "");
        return (yMd + hms).equals(rowDate);
    }

    /*
    1 ==> ACK_BS_
    2 ==> BS_
     */
    private Object[] splitXmlFileName(String bonSortieXmlFileName) {
        if (StringUtil.isEmpty(bonSortieXmlFileName)) {
            return new Object[]{-1, null, null};
        }
        String[] mySplit = bonSortieXmlFileName.split("_");
        if (bonSortieXmlFileName.startsWith("ACK_BS_") && mySplit != null && mySplit.length == 4 && mySplit[2].length() == 14) {
            return new Object[]{1, mySplit[2], mySplit[3]};
        } else if (bonSortieXmlFileName.startsWith("BS_") && mySplit != null && mySplit.length == 3 && mySplit[3].length() == 14) {
            return new Object[]{2, mySplit[1], mySplit[2]};
        } else {
            return new Object[]{-2, null, null};
        }
    }

    private void validateSecondLevel(BonSortie bonSortie, boolean isActive, boolean ack) {
        if (!isActive) {
            return;
        }
        BonSortieMessageManager.clearMessages();
        if (ack) {
            BonSortieMessageManager.getErrorMessageReferenceBonSortie(validateReferenceBonSortie(ack, bonSortie));
        } else {
            BonSortieMessageManager.getErrorMessageTypeDs(validateTypeDs(bonSortie));
            BonSortieMessageManager.getErrorMessageLieuChargement(validateLieuChargement(bonSortie));
            BonSortieMessageManager.getErrorMessageReferenceLotDedouanement(validateLotDedouanement(bonSortie));
            BonSortieMessageManager.getErrorMessageTypeContenant(validateTypeContenant(bonSortie));
            BonSortieMessageManager.getErrorMessageNombreContenant(validateNombreContenant(bonSortie));
            BonSortieMessageManager.getErrorMessageMarqueMarchandise(validateMarqueMarchandise(bonSortie));
            BonSortieMessageManager.getErrorMessagePoidsNet(validatePoidNet(bonSortie));
            BonSortieMessageManager.getErrorMessageTare(validateTare(bonSortie));
            BonSortieMessageManager.getErrorMessagePoidsBrut(validatePoidBrute(bonSortie));
        }
    }

    private int validateReferenceBonSortie(boolean ack, BonSortie bonSortie) {
        if (ack && bonSortie.getAccuse().getCode() != null && bonSortie.getAccuse().getCode().equals("OK")) {
            return StringUtil.isNotEmpty(bonSortie.getAccuse().getReferenceBonSortie());
        }
        return 2;
    }

    private int validateTypeDs(BonSortie bonSortie) {
        return isValide(bonSortie, typeDeclarationDS, bonSortie.getBon().getTypeDS());
    }

    private int validateLieuChargement(BonSortie bonSortie) {
        return isValide(bonSortie, codeLieuChargement, bonSortie.getBon().getLieuChargement());
    }

    private int validateLotDedouanement(BonSortie bonSortie) {
        return isValide(bonSortie, null, bonSortie.getBon().getReferenceLot());
    }

    private int validateTypeContenant(BonSortie bonSortie) {
        return isValide(bonSortie, null, bonSortie.getBon().getTypeContenant());
    }

    private int validateNombreContenant(BonSortie bonSortie) {
        int index = codeTypeContenant.indexOf(bonSortie.getBon().getTypeContenant());
        if (index == -1) {
            return StringUtil.isNotEmpty(bonSortie.getBon().getNombreContenant());
        }
        return 2;
    }

    private int validateMarqueMarchandise(BonSortie bonSortie) {
        if (bonSortie.getBon().getReferenceDS() != null) {
            return StringUtil.isNotEmpty(bonSortie.getBon().getMarqueMarchandise());
        }
        return 2;
    }

    private int validatePoidNet(BonSortie bonSortie) {
        String poidBrut = bonSortie.getBon().getPoidsBrut();
        String tare = bonSortie.getBon().getTare();
        String poidsNet = bonSortie.getBon().getPoidsNet();
        int isMandatory = StringUtil.validateUsingCondition((validateNombreContenantAndMlv(bonSortie))
                && (StringUtil.isEmpty(poidBrut) && StringUtil.isEmpty(tare)));
        if (isMandatory > 0) {
            return StringUtil.isNotEmpty(poidsNet);
        }
        return 2;
    }

    private int validateTare(BonSortie bonSortie) {
        return validateTareOrPoidBrute(bonSortie, bonSortie.getBon().getTare());
    }

    private int validatePoidBrute(BonSortie bonSortie) {
        return validateTareOrPoidBrute(bonSortie, bonSortie.getBon().getPoidsBrut());
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

    private int isValide(BonSortie bonSortie, List<String> defaultValue, String elementInList) {
        String referenceDs = bonSortie.getBon().getReferenceDS();
        if (StringUtil.isEmpty(referenceDs)) {
            if (elementInList == null) {
                return 3;
            }
            return checkeElementInList(defaultValue, elementInList);
        } else {
            if (elementInList == null) {
                return -2;
            }
            return checkeElementInList(defaultValue, elementInList);
        }
    }

    private int checkeElementInList(List<String> defaultValue, String element) {
        int indexOfElement = -1;
        if (defaultValue != null) {
            indexOfElement = defaultValue.indexOf(element);
        }
        if (defaultValue == null) {
            return 1;
        } else if (indexOfElement != -1) {
            return 2;
        } else {
            return -1;
        }
    }
}
