/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import util.FileUtil;
import util.StringUtil;
import validator.BonSortieValidator.TYPE_FILE;
import vo.BonSortie;

/**
 *
 * @author YOUNES
 */
public class BonSortieValidator {
    
    public enum TYPE_FILE {
        CREATE, EDIT, REMOVE, ACK
    }
    BonSortieValidator1L bonSortie1LValidator = new BonSortieValidator1L();
    BonSortieValidator2L bonSortie2LValidator = new BonSortieValidator2L();
    
    public void validate(String absolutePath, String xsdPath) throws JAXBException, SAXException, IOException {
        File myFolder = new File(absolutePath);
        File tmpFolder = FileUtil.getTmpFile(myFolder);
        FileUtil.copyFolder(myFolder, tmpFolder);
        String files[] = tmpFolder.list();
        for (String file : files) {
            File myFile = new File(tmpFolder, file);
            Object[] res = validate(absolutePath, myFile.getName(), xsdPath, getTypeFileByBS(bonSortie));
            if (new Integer(res[0] + "") > 0) {
                myFile.delete();
            }
            
        }
    }
    
    public Object[] validate(String absolutePath, String xmlPath, String xsdPath, TYPE_FILE type_file) throws JAXBException, SAXException {
        if (type_file == TYPE_FILE.CREATE) {
            return validateCreate(absolutePath, xmlPath, xsdPath);
        } else if (type_file == TYPE_FILE.EDIT) {
            return validateEdit(absolutePath, xmlPath, xsdPath);
        } else if (type_file == TYPE_FILE.REMOVE) {
            return validateRemove(absolutePath, xmlPath, xsdPath);
        } else if (type_file == TYPE_FILE.ACK) {
            return validateAck(absolutePath, xmlPath, xsdPath);
        } else {
            return new Object[]{-5, BonSortieMessageManager1L.putTypeActionNotValidMessage(-1)};
        }
    }
    
    private Object[] validateAbstract(String absoluteXmlPath, String absoluteXsd, String xmlPath,
            String xsdPath, boolean activateRefDumAndDate, boolean activate1L, boolean activate2L
    ) throws JAXBException, SAXException {
        
        Object[] resValidateXmlFileName = splitXmlFileName(xmlPath);
        if (new Integer(resValidateXmlFileName[0] + "") < 0) {
            return new Object[]{-1, BonSortieMessageManager1L.putErrorMessageXmlFileNameNotValidMessage(-1)};
        }
        boolean ack = (new Integer(resValidateXmlFileName[0] + "") == 1);
        // BonSortie bonSortie = new JaxbBonSortie().unmarshallAndValiadte(absoluteXmlPath + xmlPath, absoluteXsd + xsdPath);
        BonSortie bonSortie = new JaxbBonSortie().unmarshall(absoluteXmlPath + xmlPath);
        TYPE_FILE type_file = getTypeFileByBS(bonSortie);
        if (!BonSortieMessageManager1L.getErrorsXsd().isEmpty()) {
            validate1L(bonSortie, activate1L, ack, type_file);
            return new Object[]{-2, BonSortieMessageManager1L.getErrors1L()};
        } else if (!validateRefDumAndDate(resValidateXmlFileName, bonSortie, activateRefDumAndDate)) {// no message error ==> to be tested
            return new Object[]{-3, BonSortieMessageManager2L.putErrorMessageXmlFileNameAndFileContentNotValidMessage(-1)};
        }
        validate2L(bonSortie, activate2L, ack, type_file);
        if (!BonSortieMessageManager2L.getErrors2L().isEmpty()) {
            return new Object[]{-4, BonSortieMessageManager2L.getErrors2L()};
        } else {
            return new Object[]{1, bonSortie};
        }
    }
    
    private Object[] validateCreate(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, true, true, true);
    }
    
    private Object[] validateEdit(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, true, true, true);
    }
    
    private Object[] validateAck(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, false, true, true);
    }
    
    private Object[] validateRemove(String absolutePath, String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return validateAbstract(absolutePath, absolutePath, xmlPath, xsdPath, true, true, false);
    }
    
    private void validate1L(BonSortie bonSortie, boolean activate1L, boolean ack, TYPE_FILE type_file) {
        bonSortie1LValidator.valiadte1L(bonSortie, activate1L, ack, type_file);
    }
    
    private void validate2L(BonSortie bonSortie, boolean activate1L, boolean ack, TYPE_FILE type_file) {
        bonSortie2LValidator.validate2L(bonSortie, activate1L, ack, type_file);
    }
    
    private Object[] splitXmlFileName(String bonSortieXmlFileName) {
        if (StringUtil.isEmpty(bonSortieXmlFileName)) {
            return new Object[]{-1, null, null};
        }
        String[] mySplit = bonSortieXmlFileName.split("_");
        if (bonSortieXmlFileName.startsWith("ACK_BS_") && mySplit != null && mySplit.length == 4 && mySplit[2].length() == 14) {
            return new Object[]{1, mySplit[2], mySplit[3]};
        } else if (bonSortieXmlFileName.startsWith("BS_") && mySplit != null && mySplit.length == 3 && mySplit[1].length() == 14) {
            return new Object[]{2, mySplit[1], mySplit[2]};
        } else {
            return new Object[]{-2, null, null};
        }
    }
    
    private boolean validateRefDumAndDate(Object[] resValidateXmlFileName, BonSortie bonSortie, boolean isActive) {
        if (!isActive) {
            return true;
        }
        if (resValidateXmlFileName[1] == null || bonSortie.getHeader().getDateMessage() == null) {
            return false;
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
    
    public static Object[] validateExistMandatoryStyle(String attribute, boolean condition, String attributeNameMessage) {
        int exist = StringUtil.isNotEmpty(attribute);
        int res;
        if (exist > 0) {
            res = condition ? 1 : -1;
        } else {
            res = -2;
        }
        return new Object[]{res, attributeNameMessage};
    }
    
    public static Object[] validateExistNotMandatoryStyle(String attribute, boolean condition, String attributeNameMessage) {
        boolean isNull = StringUtil.isEmpty(attribute);
        int res;
        if (isNull) {
            res = 2;
        } else {
            res = (condition) ? 1 : -1;
        }
        return new Object[]{res, attributeNameMessage};
    }
    
    public static Object[] isValideAndMandatoryExist(String mandatory, String element, String attributeName) {
        return validateExistMandatoryStyle(mandatory, element != null, attributeName);
    }
    
    private TYPE_FILE getTypeFileByBS(BonSortie bonSortie) {
        if (bonSortie.getHeader().getFonctionMessage().equals("1")) {
            return TYPE_FILE.CREATE;
        } else if (bonSortie.getHeader().getFonctionMessage().equals("2")) {
            return TYPE_FILE.EDIT;
        } else if (bonSortie.getHeader().getFonctionMessage().equals("3")) {
            return TYPE_FILE.REMOVE;
        } else if (bonSortie.getHeader().getFonctionMessage() == null) {
            return TYPE_FILE.ACK;
        } else {
            return null;
        }
    }
    
}
