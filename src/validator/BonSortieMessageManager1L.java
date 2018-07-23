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
public class BonSortieMessageManager1L {

    private static Map<String, String> errorMessages1L = new HashMap();
    private static List<String> errors1L = new ArrayList();
    private static String prefix = "e.bs.v1l.";
    private static List<String> errorsXsd = new ArrayList();

    public static String putErrorMessageXmlFileNameNotValidMessage(int res) {
        return putErrorMessage(new Object[]{res, "xmlFileNameNotValidMessage"});
    }
    public static String putTypeActionNotValidMessage(int res) {
        return putErrorMessage(new Object[]{res, "typeActionNotValidMessage"});
    }

    public static String putErrorMessage(Object[] res) {
        return BonSortieMessageManager.putErrorMessage(res, prefix, errors1L);
    }

    public static String getErrors1LAsString() {
        return StringUtil.transformListToString(errors1L);
    }

    public static List<String> getErrors1L() {
        return errors1L;
    }

    public static Map<String, String> getErrorMessages1L() {
        return errorMessages1L;
    }

    public static void clearErrors1L() {
        errors1L.clear();
    }

    public static void clearErrorsXsd() {
        errorsXsd.clear();
    }

    public static List<String> getErrorsXsd() {
        return errorsXsd;
    }

}
