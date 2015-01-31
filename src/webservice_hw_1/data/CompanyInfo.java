/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_hw_1.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amore & johanand
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "companyInfo", namespace = "http://www.mc-boden.se/id2208/schema")
public class CompanyInfo {
    
    @XmlElement(required = true)
    List<Company> companies;

    public List<Company> getCompanies() {
        if (companies == null)
            companies = new ArrayList<>();
        
        return companies;
    }
    
}
