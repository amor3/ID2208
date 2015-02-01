package webservice_hw_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import webservice_hw_1.data.Company;

/**
 * Parses EmploymentRecord with SAX and calls JAXB parser to get company info
 * 
 * @author AMore & Johan
 */
public class SAXProcessing {

    private List<String> orgNoList;

    public SAXProcessing() {
        orgNoList = new ArrayList<>();
    }

    public List<Company> getCompanyInfo(String ssn) {
        
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

                boolean ssn = false;
                boolean orgNo = false;
                boolean from = false;
                boolean to = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("ssn")) {
                        ssn = true;
                    }
                    if (qName.equalsIgnoreCase("orgNo")) {
                        orgNo = true;
                    }
                    if (qName.equalsIgnoreCase("from")) {
                        from = true;
                    }
                    if (qName.equalsIgnoreCase("to")) {
                        to = true;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) 
                        throws SAXException {
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {

                    if (ssn) {
                        ssn = false;
                    }
                    if (orgNo) {
                        String orgNr = new String(ch, start, length);
                        orgNoList.add(orgNr);
                        orgNo = false;
                    }
                    if (from) {
                        from = false;
                    }
                    if (to) {
                        to = false;
                    }
                }

            };

            saxParser.parse(new File("/Users/johanand/NetBeansProjects/ID2208/src/" + 
                    "xml_documents/EmploymentRecord.xml"), handler);

            // The dom parser analyzes  the employment office to return all the codes of the 
            //companies where the guy has worked
            DOMCompanyParser domCompanyParser = new DOMCompanyParser();

            JAXBProcessing jaxbProcessing = new JAXBProcessing("/Users/johanand/NetBeansProjects/ID2208/src/xml_documents/companyInfo.xml");
        
            List<Company> companies = jaxbProcessing.filter(orgNoList);
            
            return companies;
        } catch (ParserConfigurationException | SAXException | IOException | 
                JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
