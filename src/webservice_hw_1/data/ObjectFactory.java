package webservice_hw_1.data;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import webservice_hw_1.data.Company;
import webservice_hw_1.data.CompanyInfo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johanand
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName qName = 
            new QName("www.w3.org/2001/XMLSchema", "companyInfo");
    
    public ObjectFactory() {
    }
    
    public CompanyInfo getCompanyInfo() {
        return new CompanyInfo();
    }
    
    @XmlElementDecl(namespace = "www.w3.org/2001/XMLSchema", name = "companyInfo")
    public JAXBElement<CompanyInfo> createCompanyInfo(CompanyInfo value) {
        return new JAXBElement<CompanyInfo>(qName, CompanyInfo.class, null, value);
    }
    
}
