/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author YOUNES
 */
public class JaxbUtil<T> {

    private Class<T> entityClass;

    public JaxbUtil(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T unmarshallAndValiadte(String xmlPath, String xsdPath) throws JAXBException, SAXException {
        MyValidationEventHandler.clearXsdErrors();
        JAXBContext context = JAXBContext.newInstance(entityClass);
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(xsdPath));
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new MyValidationEventHandler());
        T myObject = (T) unmarshaller.unmarshal(new File(xmlPath));
        return myObject;
    }

}
