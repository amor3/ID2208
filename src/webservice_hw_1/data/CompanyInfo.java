package webservice_hw_1.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Maps to CompanyInfo.xml
 * @author amore & johanand
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "companyInfo", propOrder = { "company" })
@XmlRootElement(name="companyInfo") 
public class CompanyInfo {
    
    @XmlElement(required = true)
    List<Company> company;

    public List<Company> getCompany() {
        if (company == null)
            company = new ArrayList<>();
        
        return company;
    }
    
}
