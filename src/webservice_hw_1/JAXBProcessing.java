package webservice_hw_1;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import webservice_hw_1.data.Company;
import webservice_hw_1.data.CompanyInfo;

/**
 *
 * @author AMore
 */
public class JAXBProcessing {

    // DENNA KAN DU FORTSÄTTA MED :P
    private String filepath;
    private CompanyInfo listOfCompanies;
    JAXBContext jaxbc;

    public JAXBProcessing(String filepath) throws JAXBException {
        this.filepath = filepath;
        jaxbc = JAXBContext.newInstance("webservice_hw_1.data");
        importXml();
    }

    private void importXml() throws JAXBException {
        Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
        CompanyInfo companyInfo = (CompanyInfo) unmarshaller.unmarshal(new File(filepath));
        listOfCompanies = companyInfo;
    }

    public List<Company> filter(List<String> orgNumbers) {
        List<Company> companies = new ArrayList<>();
        for (Company company : listOfCompanies.getCompany()) {
            for (String orgNo : orgNumbers) {
                if (company.getOrgNo().equals(orgNo))
                    companies.add(company);
            }
        }
        
        return companies;
    }

//    public void exportXml(String targetPath) throws JAXBException {
//        File file = new File(targetPath);
//        Marshaller marshaller = jaxbc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(new JAXBElement<>(new QName("http://www.mc-boden.se/id2208/schema"), 
//                            CompanyInfo.class, listOfCompanies), System.out);
//    }
//
//    public static void main(String args[]) {
//        try {
//            JAXBProcessing processor = new JAXBProcessing(
//                    "/Users/johanand/NetBeansProjects/ID2208/src/xml_documents/CompanyInfo.xml");
//            processor.importXml();
//            processor.exportXml("companyInfoOut.xml");
//        } catch (JAXBException ex) {
//            Logger.getLogger(JAXBProcessing.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
}
