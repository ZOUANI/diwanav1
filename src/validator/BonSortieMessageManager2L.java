/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.StringUtil;

/**
 *
 * @author YOUNES
 */
public class BonSortieMessageManager2L {

    private static Map<String, String> errorMessages2L = new HashMap();
    private static List<String> errors2L = new ArrayList();
    private static String prefix = "e.bs.v2l.";
    private static String typeActionNotValidMessage = "Merci de choisir soit Create, Edit ou Remove";

    public static String putErrorMessageXmlFileNameAndFileContentNotValidMessage(int res) {
        return putErrorMessage(new Object[]{res, "xmlFileNameAndFileContentNotValidMessage"});
    }

    public static String getErrors2LAsString() {
        return StringUtil.transformListToString(errors2L);
    }

    public static List<String> getErrors2L() {
        return errors2L;
    }

    public static void clearErrors2L() {
        errors2L.clear();
    }

    public static String getTypeActionNotValidMessage() {
        return typeActionNotValidMessage;
    }

    public static String putErrorMessage(Object[] res) {
        return BonSortieMessageManager.putErrorMessage(res, prefix, errors2L);
    }

    public static Map<String, String> getErrorMessages2L() {
        return errorMessages2L;
    }

}
