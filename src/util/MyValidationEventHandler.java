/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import validator.BonSortieMessageManager1L;

/**
 *
 * @author YOUNES
 */
public class MyValidationEventHandler implements ValidationEventHandler {

    @Override
    public boolean handleEvent(ValidationEvent event) {
//	        System.out.println("SEVERITY:  " + event.getSeverity());
                BonSortieMessageManager1L.getErrorsXsd().add(event.getMessage());
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

}
