/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;

/**
 *
 * @author YOUNES
 */
public class StringUtil {

    public static int isNotEmpty(String content) {
        return validateUsingCondition(!isEmpty(content));
    }

    public static boolean isEmpty(String content) {
        return content == null || content.isEmpty();
    }

    public static int validateUsingCondition(boolean condition) {
        return condition ? 1 : -1;
    }

    public static String transformListToString(List<String> list) {
        String res = "";
        if (list != null) {
            for (String element : list) {
                res += element + "\n";
            }
        }
        return res;
    }

}
