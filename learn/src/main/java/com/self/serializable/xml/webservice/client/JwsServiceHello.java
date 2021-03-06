
package com.self.serializable.xml.webservice.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "JwsServiceHello", targetNamespace = "http://server.webservice.xml.serializable.self.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface JwsServiceHello {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getValue", targetNamespace = "http://server.webservice.xml.serializable.self.com/", className = "com.self.serializable.xml.webservice.client.GetValue")
    @ResponseWrapper(localName = "getValueResponse", targetNamespace = "http://server.webservice.xml.serializable.self.com/", className = "com.self.serializable.xml.webservice.client.GetValueResponse")
    @Action(input = "http://server.webservice.xml.serializable.self.com/JwsServiceHello/getValueRequest", output = "http://server.webservice.xml.serializable.self.com/JwsServiceHello/getValueResponse")
    public String getValue(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
