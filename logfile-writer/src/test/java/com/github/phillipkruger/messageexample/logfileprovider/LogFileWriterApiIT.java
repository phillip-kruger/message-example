package com.github.phillipkruger.messageexample.logfileprovider;

import java.net.URI;
import java.util.UUID;
import javax.ws.rs.core.UriBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testing endpoints
 * @author phillip.kruger@gmail.com
 */
public class LogFileWriterApiIT {
    private final RestCaller restCaller = new RestCaller();
    
    @Test
    public void sayHello(){
        
        String message = "testing_log_file_writer_" + UUID.randomUUID().toString();
        URI uri = UriBuilder.fromUri("http://localhost:8081/logfile-writer/api/SEVERE/").build();
        int status = restCaller.postRequest(uri, message).getStatus();
        Assert.assertEquals(204, status);
    }
}
