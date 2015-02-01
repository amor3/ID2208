/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_hw_1;

import javax.xml.bind.JAXBException;

/**
 *
 * @author AMore
 */
public class WebService_hw_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException {
        DOMProcessing domProcessor = new DOMProcessing();
        domProcessor.process();
    }
    
}
