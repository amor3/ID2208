package webservice_hw_1.data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * 
 * @author amore & johand4
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Company_QNAME = 
            new QName("http://www.mc-boden.se/id2208/schema", "company");
    
    public ObjectFactory() {
    }
    
    public CompanyInfo getCompanyInfo() {
        return new CompanyInfo();
    }
    
    public Company getCompany() {
        return new Company();
    }
    
    @XmlElementDecl(namespace = "http://www.mc-boden.se/id2208/schema", name = "company")
    public JAXBElement<CompanyInfo> createCompanyInfo(CompanyInfo value) {
        return new JAXBElement<>(_Company_QNAME, CompanyInfo.class, null, value);
    }
    
}
