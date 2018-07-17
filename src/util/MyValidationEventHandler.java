/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

/**
 *
 * @author YOUNES
 */
public class MyValidationEventHandler implements ValidationEventHandler {

    private static List<String> xsdErrors = new ArrayList();

    @Override
    public boolean handleEvent(ValidationEvent event) {
//	        System.out.println("SEVERITY:  " + event.getSeverity());
        xsdErrors.add(event.getMessage());
//	        System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
//	        System.out.println("LOCATOR");
//	        System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
//	        System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
//	        System.out.println("    OFFSET:  " + event.getLocator().getOffset());
//	        System.out.println("    OBJECT:  " + event.getLocator().getObject());
//	        System.out.println("    NODE:  " + event.getLocator().getNode());
//	        System.out.println("    URL:  " + event.getLocator().getURL());
        return true;
    }

    public static String getXsdErrors() {
        return StringUtil.transformListToString(xsdErrors);
    }

    public static void clearXsdErrors() {
        xsdErrors.clear();
    }
}
