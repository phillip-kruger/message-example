package com.github.phillipkruger.message;

import java.net.URI;
import java.util.UUID;
import javax.ws.rs.core.UriBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testing endpoints
 * @author phillip.kruger@gmail.com
 */
public class MessageApiIT {
    private final RestCaller restCaller = new RestCaller();
    
    @Test
    public void sayHello(){
        
        String message = "hello_" + UUID.randomUUID().toString();
        URI uri = UriBuilder.fromUri("http://localhost:8080/message-application/api/message/" + message).build();
        int status = restCaller.sendRequest(uri, "POST").getStatus();
        Assert.assertEquals(204, status);
    }
}
