package webservice_hw_1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import webservice_hw_1.data.Company;

/**
 *
 * @author AMore & Johan
 */
public class DOMProcessing {

    //Get a DocumentBuilder to be able to make the xml document
    public static DocumentBuilder builder = null;

    public DOMProcessing() {
    }

    public void process() throws JAXBException {

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

        //specifies the XML schema document to be used for validation. 
        //builderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", "http://www.mc-boden.se/id2208/schema/ApplicantProfile.xsd");
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create a xmlDoc XML
        Document xmlDoc = builder.newDocument();

        // Create root element for applicantProfile
        Element rootElement = xmlDoc.createElement("applicantProfile");
        xmlDoc.appendChild(rootElement);

        // Parse Corresponding xml files
        Document shortCVDocument = parseXMLFile("/Users/AMore/NetBeansProjects/WebService_hw_1/src/xml_documents/ShortCV.xml");
        Document transcriptDocument = parseXMLFile("/Users/AMore/NetBeansProjects/WebService_hw_1/src/xml_documents/Transcript.xml");
        Document employmentRecDocument = parseXMLFile("/Users/AMore/NetBeansProjects/WebService_hw_1/src/xml_documents//EmploymentRecord.xml");
        Document companyInfoDocument = parseXMLFile("/Users/AMore/NetBeansProjects/WebService_hw_1/src/xml_documents/CompanyInfo.xml");

        //Get all nodes for corresponding xml files
        NodeList shortCVList = shortCVDocument.getElementsByTagName("shortCV");
        NodeList transcriptList = transcriptDocument.getElementsByTagName("transcript");
        NodeList employmentRecList = employmentRecDocument.getElementsByTagName("employmentRecords");
        NodeList companyInfoList = companyInfoDocument.getElementsByTagName("companyInfo");

        String ssn = "not set";

        // Personal Info Creation
        for (int i = 0; i < shortCVList.getLength(); i++) {
            Element personalInfoElement = xmlDoc.createElement("personalInfo");
            rootElement.appendChild(personalInfoElement);

            Element shortCVListElement = (Element) shortCVList.item(i);

            String name = shortCVListElement.getElementsByTagName("name").item(0).getTextContent();
            Element nameElement = xmlDoc.createElement("name");
            nameElement.appendChild(xmlDoc.createTextNode(name));
            personalInfoElement.appendChild(nameElement);

            ssn = shortCVListElement.getElementsByTagName("ssn").item(0).getTextContent();
            Element ssnElement = xmlDoc.createElement("ssn");
            ssnElement.appendChild(xmlDoc.createTextNode(ssn));
            personalInfoElement.appendChild(ssnElement);

            String personalLetter = shortCVListElement.getElementsByTagName("personalLetter").item(0).getTextContent();
            Element personalLetterElement = xmlDoc.createElement("personalLetter");
            personalLetterElement.appendChild(xmlDoc.createTextNode(personalLetter));
            personalInfoElement.appendChild(personalLetterElement);
        }

        // Study Record Creation
        for (int i = 0; i < transcriptList.getLength(); i++) {
            Element studyRecordElement = xmlDoc.createElement("studyRecord");
            rootElement.appendChild(studyRecordElement);

            Element transcriptElement = (Element) transcriptList.item(i);

            String university = transcriptElement.getElementsByTagName("university").item(0).getTextContent();
            Element universityElement = xmlDoc.createElement("universityName");
            universityElement.appendChild(xmlDoc.createTextNode(university));
            studyRecordElement.appendChild(universityElement);

            String degree = transcriptElement.getElementsByTagName("degree").item(0).getTextContent();
            Element degreeElement = xmlDoc.createElement("degree");
            degreeElement.appendChild(xmlDoc.createTextNode(degree));
            studyRecordElement.appendChild(degreeElement);

            String year = transcriptElement.getElementsByTagName("graduationYear").item(0).getTextContent();
            Element yearElement = xmlDoc.createElement("year");
            yearElement.appendChild(xmlDoc.createTextNode(year));
            studyRecordElement.appendChild(yearElement);

            /*
             // Calculate the GPA
             NodeList courseList = transcriptElement.getElementsByTagName("course");
             float gpa = 0;
             for (int j = 0; j < courseList.getLength(); j++) {
             Element courseElement = (Element) courseList.item(j);

             gpa += Integer.valueOf(courseElement.getElementsByTagName("grade").item(0).getTextContent());
             }
            

             float gpaAverage = gpa / courseList.getLength();

             Element gpaElement = xmlDoc.createElement("GPA");
             gpaElement.appendChild(xmlDoc.createTextNode(String.valueOf(gpaAverage)));
             studyRecordElement.appendChild(gpaElement);
             */
        }

        // Employment Record Creation
        Element employmentRecordElement = xmlDoc.createElement("employmentRecord");
        rootElement.appendChild(employmentRecordElement);

        Element employmentsElement = (Element) employmentRecList.item(0);

        // Get all employment objects in list
        NodeList employmentList = employmentsElement.getElementsByTagName("employment");

        // USes SAX and JAXB here
        SAXProcessing saxProcessor = new SAXProcessing();
        List<Company> companies = saxProcessor.getCompanyInfo(ssn);

        for (int j = 0; j < employmentList.getLength(); j++) {
            Element employmentElement = (Element) employmentList.item(j);

            Element employElement = xmlDoc.createElement("employment");
            employmentRecordElement.appendChild(employElement);

            String orgNo = employmentElement.getElementsByTagName("orgNo").item(0).getTextContent();
            String from = employmentElement.getElementsByTagName("from").item(0).getTextContent();
            String to = employmentElement.getElementsByTagName("to").item(0).getTextContent();

            Company company = getCompanyByOrgNo(orgNo, companies);

            Element orgNoElement = xmlDoc.createElement("companyId");
            orgNoElement.appendChild(xmlDoc.createTextNode(orgNo));
            employElement.appendChild(orgNoElement);

            Element companyNameElement = xmlDoc.createElement("companyName");
            companyNameElement.appendChild(xmlDoc.createTextNode(company.getName()));
            employElement.appendChild(companyNameElement);

            Element phoneElement = xmlDoc.createElement("telephoneNumber");
            phoneElement.appendChild(xmlDoc.createTextNode(company.getPhoneNumber()));
            employElement.appendChild(phoneElement);

            Element fromElement = xmlDoc.createElement("fromDate");
            fromElement.appendChild(xmlDoc.createTextNode(from));
            employElement.appendChild(fromElement);

            Element toElement = xmlDoc.createElement("toDate");
            toElement.appendChild(xmlDoc.createTextNode(to));
            employElement.appendChild(toElement);
        }

        // Generates temporary course data
        // Study Record Creation
        for (int i = 0; i < transcriptList.getLength(); i++) {
            Element tempCoursesElement = xmlDoc.createElement("tempCourses");
            rootElement.appendChild(tempCoursesElement);

            Element transcriptElement = (Element) transcriptList.item(i);

            NodeList courseList = transcriptElement.getElementsByTagName("course");

            for (int j = 0; j < courseList.getLength(); j++) {
                Element courseElement = (Element) courseList.item(j);

                Element tempCourseElement = xmlDoc.createElement("tempCourse");
                
                String id = courseElement.getElementsByTagName("ID").item(0).getTextContent();
                String name = courseElement.getElementsByTagName("name").item(0).getTextContent();
                String grade = courseElement.getElementsByTagName("grade").item(0).getTextContent();

                tempCourseElement.setAttribute("id", id);
                tempCourseElement.setAttribute("name", name);
                tempCourseElement.setAttribute("grade", grade);
                
                tempCoursesElement.appendChild(tempCourseElement);
            }

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
            StreamResult result = new StreamResult(new File("/Users/AMore/Desktop/output.xml"));
            //fill the XML xmlDoc file using the stream with the DOM tree
            transformer.transform(source, result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Document parseXMLFile(String filePath) {
        Document doc = null;
        try {
            doc = builder.parse(new File(filePath));
        } catch (SAXException | IOException ex) {
            Logger.getLogger(DOMProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }

    private Company getCompanyByOrgNo(String orgNo, List<Company> companies) {
        for (Company c : companies) {
            if (c.getOrgNo().equals(orgNo)) {
                return c;
            }
        }

        return null;
    }

}
