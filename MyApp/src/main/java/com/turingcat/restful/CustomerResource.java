/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 15:55:07 
 */
package com.turingcat.restful;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

@Path("/customers")
public interface CustomerResource {

	@POST
	@Consumes("application/xml")
	public Response createCustomer(InputStream is);

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public StreamingOutput getCustomer(@PathParam("id") int id);

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateCustomer(@PathParam("id") int id, InputStream is) ;
}