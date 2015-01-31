package webservice_hw_1;



import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




/**
 *
 * @author AMore & Johan
 */
public class SAXProcessing {

    public static ArrayList<String> orgNoList = new ArrayList<>();

    public static void main(String argv[]) {

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
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //System.out.println("End Element :" + qName);
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {

                    if (ssn) {
                        System.out.println("ssn : " + new String(ch, start, length));
                        ssn = false;
                    }
                    if (orgNo) {
                        String orgNr = new String(ch, start, length);
                        orgNoList.add(orgNr);
                        System.out.println("orgNo : " + orgNo);
                        orgNo = false;
                    }
                    if (from) {
                        System.out.println("from : " + new String(ch, start, length));
                        from = false;
                    }
                    if (to) {
                        System.out.println("to : " + new String(ch, start, length));
                        to = false;
                    }
                }
                
                
            };

            
            
            
            
            
            
            
            
            
            
            
            
            
            
        saxParser.parse(new File("//Users//AMore//NetBeansProjects//WebService_hw_1//src//xml_documents//EmploymentRecord.xml"), handler);

 
        // Tanken var att vi skulle göra en av java processerna så att den använder alla teknikerna tillsammans..
        // Har endast påbörjat detta. kolla gärna på github killens exempel så förstår du :)
        
        // The dom parser analyzes  the employment office to return all the codes of the 
        //companies where the guy has worked
        DOMCompanyParser domCompanyParser = new DOMCompanyParser();
        ArrayList<String> companyNamesList = domCompanyParser.getCompanyNames(orgNoList);
        
        System.out.println("companyNamesList: " + companyNamesList);


        
        /*
        JAXBProcessing jaxbProcessing = new JAXBProcessing("src/xml/companyInfo.xml", companyNamesList);
        
        jaxbProcessing.importXml();
        ListCompaniesType lc = jaxbProcessing.filter();
        jaxbProcessing.exportXml(lc, "src/xml/companyInfoOutput.xml");
*/
        
        
        
        /*
        //xlst reads the transcript to calculate the avg of the guy's grades
        Source xmlInput = new StreamSource(new File("src/xml/transcript.xml"));
        Source xsl = new StreamSource(new File("src/xml/transcriptTransformer.xsl"));
        Result xmlOutput = new StreamResult(new File("src/xml/transcriptOutput.xml"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer(xsl);
        transformer.transform(xmlInput, xmlOutput);




        ////xlst to merge everything
        xsl = new StreamSource(new File("src/xml/XSLTmerger.xsl"));
        transformer = TransformerFactory.newInstance().newTransformer(xsl);
        xmlOutput = new StreamResult(new File("src/xml/finalOutput.xml"));
        transformer.transform(xmlInput, xmlOutput);



        
        
        
            
            
            
            
            
            saxParser.parse(new File("//Users//AMore//NetBeansProjects//WebService_hw_1//src//xml_documents//Transcript.xml"), handler);
*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
