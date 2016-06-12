/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 16:21:02 
 */
package com.turingcat.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
@Path("/")
public class MyService {

    final String XMLNS_NAMESPACE = "http://yjmyzz.cnblogs.com/rest/service";
    final String ROOT_NODE = "root";

    @GET
    @Path("/json/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public JAXBElement<String> getHelloWorldJSON() {
        JAXBElement<String> result = new JAXBElement<String>(new QName("",
                ROOT_NODE), String.class, sayHelloWorld());
        return result;
    }
    
    private String sayHelloWorld() {
             return "Hello JAX-RS!";
    }

}

