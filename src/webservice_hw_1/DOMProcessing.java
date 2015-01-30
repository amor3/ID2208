package webservice_hw_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author AMore & Johan
 */
public class DOMProcessing {

    public static void main(String... args) {

        //Get Builder Factory
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        // Be sure it validates
        //builderFactory.setValidating(true);

        // We are going to parse it with a namespace
        //builderFactory.setNamespaceAware(true);

        //to ignore white spaces between elements
        //builderFactory.setIgnoringElementContentWhitespace(true);
        //specifies schema language for validation
        //builderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");

        //specifies the XML schema document to be used for validation. 
        //builderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", "ApplicantProfile.xsd");

        //Get a DocumentBuilder to be able to make the xml document
        DocumentBuilder builder = null;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create a xmlDoc XML
        Document xmlDoc = builder.newDocument();

        // Create root element applicantProfile
        Element rootElement = xmlDoc.createElement("applicantProfile");
        xmlDoc.appendChild(rootElement);

 
        
        // Parse ShortCV doc
        Document shortCVDocument = null;
        try {
            shortCVDocument = builder.parse(new File("//Users//AMore//NetBeansProjects//WebService_hw_1//src//xml_documents//ShortCV.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Get all elements for shortCV
        NodeList shortCVList = shortCVDocument.getElementsByTagName("shortCV");
        
  
 

/*
        // Get SSN and save it for later use
        String name = shortCVElement.getElementsByTagName("name").item(0).getTextContent();
        String ssn = shortCVElement.getElementsByTagName("ssn").item(0).getTextContent();
        String personalLetter = shortCVElement.getElementsByTagName("personalLetter").item(0).getTextContent();
*/
        

        for (int i = 0; i < shortCVList.getLength(); i++) {
            Element personalInfoElement = xmlDoc.createElement("personalInfo");
            rootElement.appendChild(personalInfoElement);

            Element shortCVListElement = (Element) shortCVList.item(i);

            
            String name = shortCVListElement.getElementsByTagName("name").item(0).getTextContent();
            
            Element nameElement = xmlDoc.createElement("name");
            nameElement.appendChild(xmlDoc.createTextNode(name));
            personalInfoElement.appendChild(nameElement);
            
      
            String ssn = shortCVListElement.getElementsByTagName("ssn").item(0).getTextContent();
            
            Element ssnElement = xmlDoc.createElement("ssn");
            ssnElement.appendChild(xmlDoc.createTextNode(ssn));
            personalInfoElement.appendChild(ssnElement);

            
            
            String personalLetter = shortCVListElement.getElementsByTagName("personalLetter").item(0).getTextContent();
            
            Element personalLetterElement = xmlDoc.createElement("personalLetter");
            personalLetterElement.appendChild(xmlDoc.createTextNode(personalLetter));
            personalInfoElement.appendChild(personalLetterElement);
        }
    

        
        
        
        
        
        
        // write the content into xml file
        //get Transformer Factory
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        //get transformer to fill XML xmlDoc file
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();

            //generate DOM tree source from the xmlDoc document
            DOMSource source = new DOMSource(xmlDoc);
            //get stream to fill the xmlDoc file
            StreamResult result = new StreamResult(new File("//Users//AMore//Desktop//output.xml"));
            //fill the XML xmlDoc file using the stream with the DOM tree
            transformer.transform(source, result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

/*
 try {

 File fXmlFile = new File("//Users//AMore//NetBeansProjects//WebService_hw_1//src//xml_documents//ShortCV.xml");
 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
 Document doc = dBuilder.parse(fXmlFile);

            
 doc.getDocumentElement().normalize();

 System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

 NodeList nList = doc.getElementsByTagName("shortCV");

 System.out.println("----------------------------");

 for (int temp = 0; temp < nList.getLength(); temp++) {

 Node nNode = nList.item(temp);

 System.out.println("\nCurrent Element: " + nNode.getNodeName());

 if (nNode.getNodeType() == Node.ELEMENT_NODE) {

 Element eElement = (Element) nNode;

 System.out.println(eElement.getElementsByTagName("name").item(0).getTextContent());
 System.out.println(eElement.getElementsByTagName("ssn").item(0).getTextContent());
 System.out.println(eElement.getElementsByTagName("personalLetter").item(0).getTextContent());
 }
 }
 } catch (Exception e) {
 e.printStackTrace();
 }
 }
 */
