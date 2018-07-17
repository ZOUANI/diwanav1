/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbtest2;

import java.io.File;
import java.io.IOException;

import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 *
 * @author YOUNES
 */
public class JaxbTest2 {

    public static void main(String[] args) throws SAXException, IOException {

        try {

            File file = new File("e:/employee.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeData.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            EmployeeData employees = (EmployeeData) jaxbUnmarshaller.unmarshal(file);

            System.out.println(employees.getId());
            System.out.println("EMPLOYESS:");
            List<Employee> list = employees.getEmployees();
            for (Employee employee : list) {
                System.out.println(employee);
            }

//            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = sf.newSchema(new File("e:/employee.xsd"));
//            
//            
//            JAXBSource source = new JAXBSource(jaxbContext, employees);
//            Validator validator = schema.newValidator();
//            validator.setErrorHandler(new MyErrorHandler());
//            validator.validate(source);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
