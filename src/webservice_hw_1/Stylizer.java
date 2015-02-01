/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_hw_1;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author johanand
 */
public class Stylizer {
    
    private Stylizer() {
    }
    
    public static void style(String outputPath) {
        try {
            DocumentBuilderFactory documentBuilderFactory = 
                DocumentBuilderFactory.newInstance();
            File xmlFile = new File("output.xml");
            File styleFile = new File("/Users/johanand/NetBeansProjects/ID2208/src/" + 
                    "style/ApplicantProfile.xsl");
            
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            
            // Transfor output with Transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource streamSource = new StreamSource(styleFile);
            Transformer transformer = transformerFactory.newTransformer(streamSource);
            
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(outputPath));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | SAXException | IOException | 
                TransformerException e) {
            System.err.println("Style error");
            System.err.println(e.getMessage());
        }
    }
    
}