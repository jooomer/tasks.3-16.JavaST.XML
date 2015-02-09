/*
 * XsdSchemaSaxValidator.java
 * Copyright (c) 2007 by Dr. Herong Yang. All rights reserved.
 */
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import javax.xml.validation.Validator;
import java.io.*;

class XsdSchemaSaxValidator {
	
  public static void mainValidator(String... a) {
    if (a.length<2) {
      System.out.println("Usage:");
      System.out.println("java XsdSchemaValidator schema_file_name "
        + "xml_file_name");
    } else {
      String schemaName = a[0];
      String xmlName = a[1];
      Schema schema = loadSchema(schemaName);
      validateXml(schema, xmlName);
    }
  }
  
  public static void validateXml(Schema schema, String xmlName) {
    try {
      // creating a Validator instance
      Validator validator = schema.newValidator();
      System.out.println();
      System.out.println("Validator Class: "
        + validator.getClass().getName());

      // preparing the XML file as a SAX source
      SAXSource source = new SAXSource(
        new InputSource(new java.io.FileInputStream(xmlName)));

      // validating the SAX source against the schema
      validator.validate(source);
      System.out.println();
      System.out.println("Validation passed.");
      System.out.println();

    } catch (Exception e) {
      // catching all validation exceptions
      System.out.println();
      System.out.println(e.toString());
    }
  }
  
  public static Schema loadSchema(String name) {
    Schema schema = null;
    try {
      String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
      SchemaFactory factory = SchemaFactory.newInstance(language);
      schema = factory.newSchema(new File(name));
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return schema;
  }
}