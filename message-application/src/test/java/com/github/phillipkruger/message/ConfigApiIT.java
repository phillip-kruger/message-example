package com.github.phillipkruger.message;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testing endpoints
 * @author phillip.kruger@gmail.com
 */
public class ConfigApiIT {
    private final RestCaller restCaller = new RestCaller();
    
    @Test
    public void changeConfigValue(){
        URI uri = UriBuilder.fromUri("http://localhost:8080/message-application/api/config/key/append").build();
        int status = restCaller.putRequest(uri, "memory").getStatus();
        Assert.assertEquals(202, status);
    }
}
