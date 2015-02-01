package webservice_hw_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author AMore & Johan
 */
public class DOMCompanyParser {

    public DocumentBuilder builder = null;
    public List<String> companyNameList = new ArrayList<>();
    
    
    
    public List<String> getCompanyNames(List<String> searchedOrgNo) {

        //Get Builder Factory
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        // Be sure it validates
        builderFactory.setValidating(true);
        // We are going to parse it with a namespace
        builderFactory.setNamespaceAware(true);
        //to ignore white spaces between elements
        builderFactory.setIgnoringElementContentWhitespace(true);
        //specifies schema language for validation
        builderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create a xmlDoc XML
        Document xmlDoc = builder.newDocument();

        // Parse Corresponding xml files
        Document companyInfoDocument = parseXMLFile(
            "/Users/johanand/NetBeansProjects/ID2208/src/xml_documents/CompanyInfo.xml");

        //Get all nodes for corresponding xml files
        NodeList companyInfoList = companyInfoDocument.getElementsByTagName("companyInfo");

        // Search for name in company xml
        String companyName = "Not found";

        Element companysElement = (Element) companyInfoList.item(0);
        NodeList companyList = companysElement.getElementsByTagName("company");

        for (int i = 0; i < companyList.getLength(); i++) {
            Element companyElement = (Element) companyList.item(i);

            for (int j = 0; j < searchedOrgNo.size(); j++) {
                if (companyElement.getElementsByTagName("orgNo").item(0).getTextContent().equals(searchedOrgNo.get(j))) {
                    companyNameList.add(companyElement.getElementsByTagName("orgNo").item(0).getTextContent() );
                }
            }

        }

        return companyNameList;
    }


    private Document parseXMLFile(String filePath) {
        Document doc = null;
        try {
            doc = builder.parse(new File(filePath));
        } catch (SAXException ex) {
            Logger.getLogger(DOMProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOMProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
}
