/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.BonSortieMessageManager1L;
import service.BonSortieMessageManager2L;
import service.BonSortieMessageManagerMetier;

/**
 *
 * @author YOUNES
 */
public class PropretyUtil {

    private static Properties prop = new Properties();
    private static InputStream input = null;
    private static OutputStream output = null;

    static {
        init();
    }

    public static String getItem(String key) {
        return prop.getProperty(key);
    }

    public static void setItem(String key, String value) {
        prop.setProperty(key, value);
    }

    public static void main(String[] args) {
        setItems(BonSortieMessageManagerMetier.getErrorMessagesMetier());
    }
    public static void setItems(Map<String, String> map) {
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                setItem("e.bs.metier."+key, value);
            }
            prop.store(output, null);
        } catch (IOException ex) {
            Logger.getLogger(PropretyUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void init() {
        try {
            input = new FileInputStream("src/xml/config.properties");
            output = new FileOutputStream("src/xml/config.properties");
            prop.load(input);
        } catch (Exception ex) {
            Logger.getLogger(PropretyUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
