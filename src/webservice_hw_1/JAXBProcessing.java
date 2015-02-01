package webservice_hw_1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import webservice_hw_1.data.Company;
import webservice_hw_1.data.CompanyInfo;

/**
 * Parses CompanyInfo.xml with JAXB
 * @author AMore
 */
public class JAXBProcessing {

    private String filepath;
    private CompanyInfo listOfCompanies;
    JAXBContext jaxbc;

    
    public JAXBProcessing(String filepath) throws JAXBException {
        this.filepath = filepath;
        // data package
        jaxbc = JAXBContext.newInstance("webservice_hw_1.data");
        importXml();
    }

    // unmarshall xml file to java object
    private void importXml() throws JAXBException {
        Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
        CompanyInfo companyInfo = (CompanyInfo) unmarshaller.unmarshal(new File(filepath));
        listOfCompanies = companyInfo;
    }

    /**
     * Filters the relevant companies
     * @param orgNumbers relevant org numbers
     * @return filtered list
     */
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

}
