/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.StringUtil;

/**
 *
 * @author YOUNES
 */
public class BonSortieMessageManagerMetier {

    private static Map<String, String> errorMessagesMetier = new HashMap();
    private static List<String> errorsMetier = new ArrayList();
    private static String prefix = "e.bs.metier.";


    public static Map<String, String> getErrorMessagesMetier() {
        return errorMessagesMetier;
    }

    public static List<String> getErrorsMetier() {
        return errorsMetier;
    }

    public static String putErrorMessage(Object[] res) {
        return BonSortieMessageManager.putErrorMessage(res, prefix, errorsMetier);
    }

    public static String getErrorsMetierAsString() {
        return StringUtil.transformListToString(errorsMetier);
    }

    public static void clearErrorsMetier() {
        errorsMetier.clear();
    }

}
