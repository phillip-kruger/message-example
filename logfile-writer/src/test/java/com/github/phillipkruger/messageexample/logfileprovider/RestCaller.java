package com.github.phillipkruger.messageexample.logfileprovider;

import java.net.URI;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import lombok.extern.java.Log;

/**
 * Makes a REST Call
 * @author phillip.kruger@gmail.com
 */
@Log
public class RestCaller {
    
    public Response postRequest(URI url, String payload) {
        Client client = ClientBuilder.newClient();
        log.log(Level.INFO, "Testing {0}", url);
        WebTarget target = client.target(url);
        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.post(Entity.text(payload));
        return response;
    }
}
