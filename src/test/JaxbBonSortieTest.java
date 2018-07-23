/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import validator.BonSortieMessageManager1L;
import validator.BonSortieValidator;
import util.StringUtil;

/**
 *
 * @author YOUNES
 */
public class JaxbBonSortieTest {

    private static List<String> res = new ArrayList();
    private static BonSortieValidator bonSortieValidator = new BonSortieValidator();

    static {
        res.add("1-BS_20180531090553_41101020180000988-TypeDS");
        res.add("2-BS_20180531090553_41101020180000988-LieuChargement");
        res.add("3-BS_20180531090553_41101020180000988-ReferenceLotDedouanement");
        res.add("4-BS_20180531090553_41101020180000988-TypeContenant");
        res.add("5-BS_20180531090553_41101020180000988-NombreContenant");
        res.add("6-BS_20180531090553_41101020180000988-MarqueMarchandise");
        res.add("7-BS_20180531090553_41101020180000988-PoidsNet");
        res.add("8-BS_20180531090553_41101020180000988-Tare");
        res.add("9-BS_20180531090553_41101020180000988-PoidsBrut");
        res.add("BS_20180620094000_41101020180000987");// 10 create or edit test
        res.add("BS_20180531094000_41101020180000988");// 11 remove test
        res.add("ACK_BS_20180531091403_41101020180000987");// 12 ack test
    }

    public static void main(String[] args) throws SAXException, IOException, JAXBException {
        Object[] resValidate = bonSortieValidator.validate("src/xml/", res.get(9) + ".xml",
                "BonSortieEdit.xsd", BonSortieValidator.TYPE_FILE.EDIT);

        System.out.println("eror de type :: ==> " + resValidate[0] + "::\n\n");
        System.out.println("xsd ==> " + (StringUtil.transformListToString(BonSortieMessageManager1L.getErrorsXsd())));
        System.out.println("L ==> " + resValidate[1]);
    }

}
