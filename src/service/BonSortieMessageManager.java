/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import util.PropretyUtil;

/**
 *
 * @author YOUNES
 */
public class BonSortieMessageManager {

    private static String  putErrorMessage(int res, String code, List<String> errors) {
        if (res < 0) {
            errors.add(PropretyUtil.getItem(code));
      //      System.out.println("ha code "+code+" o ha message ==> "+PropretyUtil.getItem(code));
            return PropretyUtil.getItem(code);
        }
        return null;
    }

    public static String putErrorMessage(Object[] res, String prefix, List<String> errors) {
        return putErrorMessage(new Integer(res[0] + ""), prefix + "" + res[1], errors);
    }

}
