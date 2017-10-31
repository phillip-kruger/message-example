package com.github.phillipkruger.messageexample.application;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonString;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * EJB 3.2, Bean validation 1.1, Fault Tolerance 1.0
 * 
 * The actual implementation of the service.
 * Plain old Stateless service.
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * @see https://github.com/eclipse/microprofile-config/
 * @see https://github.com/wildfly-extras/wildfly-microprofile-config
 */
@RequestScoped
@Log
public class MessageService {
    
    @Inject
    @ConfigProperty(name = "providers")
    private JsonArray providers;
    
    @Inject
    private Event<Message> broadcaster;
    
    public void sendMessage(String message){
        
        providers.stream().map((provider) -> ((JsonString)provider).getString()).forEachOrdered((name) -> {
            broadcaster.select(new QualifiedLiteral(name)).fire(new Message(message));
        });

    }
    
}