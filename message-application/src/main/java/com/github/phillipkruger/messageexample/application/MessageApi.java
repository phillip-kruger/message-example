package com.github.phillipkruger.messageexample.application;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.java.Log;

/**
 * JAX-RS 2.0, JAX-WS 2.2, JAXB 2.2, Bean validation 1.1
 * 
 * The API for outside consumption. 
 * Just wrapping the actual implementation with REST.
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Path("/message")
//@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) @Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class MessageApi {
    
    @Inject 
    private MessageService messageService;
    
    @POST
    @Path("/{message}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void sendMessage(@NotNull @PathParam("message") String message) {
        messageService.sendMessage(message);
    }
    
}