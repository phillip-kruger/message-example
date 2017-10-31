package com.github.phillipkruger.message;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testing endpoints
 * @author phillip.kruger@gmail.com
 */
public class MessageApiIT {
    private final RestCaller restCaller = new RestCaller();
    
    @Test
    public void getMessageProvider(){
        URI uri = UriBuilder.fromUri("http://localhost:8080/message-example/api/message/provider").build();
        String provider = restCaller.sendRequest(uri, "GET").readEntity(String.class);
        Assert.assertEquals("twitter", provider);
    }
    
    @Test @Ignore
    public void sendMessages() throws UnsupportedEncodingException{
        sendMessage("Message 1");
        sendMessage("Message 2");
        sendMessage("Message 3");
        sendMessage("Message 4");
        sendMessage("Message 5");
        sendMessage("Message 6");
        sendMessage("Message 7");
        sendMessage("Message 8");
        sendMessage("Message 9");
        sendMessage("Message 10");
        sendMessage("Message 11");
        sendMessage("Message 12");
    }
    
    private void sendMessage(String message) throws UnsupportedEncodingException {
        URI uri = UriBuilder.fromUri("http://localhost:8080/message-example/api/message/").path(message).build();
        restCaller.sendRequest(uri, "POST");
    }
}
