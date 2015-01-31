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
    }

    public void importXml() throws JAXBException {
        Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
        System.err.println(
                unmarshaller.unmarshal(new File(filepath)).getClass().getName());
        CompanyInfo companyInfo = (CompanyInfo) unmarshaller.unmarshal(new File(filepath));
//        JAXBElement jaxbe = (JAXBElement) unmarshaller.unmarshal(new File(filepath));
//        CompanyInfo companyInfo = (CompanyInfo) jaxbe.getValue();
        listOfCompanies = companyInfo;
        System.err.println(listOfCompanies.getCompany());
    }

//    public CompanyInfo filter() {
//        CompanyInfo companyInfo = new CompanyInfo();
//        for (String companycode : companycodes) {
//            for (CompanyInfoType c : listOfCompanies.getCompanyInfo()) {
//                if (c.getCompanyCode().equalsIgnoreCase(companycode)) {
//                    newlc.getCompanyInfo().add(c);
//                }
//            }
//        }
//        return newlc;
//    }

    public void exportXml(String targetPath) throws JAXBException {
        File file = new File(targetPath);
        Marshaller marshaller = jaxbc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new JAXBElement<>(new QName("http://www.mc-boden.se/id2208/schema"), 
                            CompanyInfo.class, listOfCompanies), System.out);
    }

    public static void main(String args[]) {
        try {
            JAXBProcessing processor = new JAXBProcessing(
                    "/Users/johanand/NetBeansProjects/ID2208/src/xml_documents/CompanyInfo.xml");
            processor.importXml();
            processor.exportXml("companyInfoOut.xml");
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
