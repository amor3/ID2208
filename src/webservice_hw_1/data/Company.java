package webservice_hw_1.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Maps to company in CompanyInfo.xml
 * @author johanand
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "companyInfoType", 
         propOrder = { "name", "orgNo", "phoneNumber" })
public class Company {
    
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String orgNo;
    @XmlElement(required = true)
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
