package com.github.phillipkruger.messageexample.providers;

import com.github.phillipkruger.messageexample.application.Message;
import com.github.phillipkruger.messageexample.application.Qualified;
import java.time.temporal.ChronoUnit;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

@Log
@RequestScoped
public class LogfileProvider {
    
    @Inject
    @ConfigProperty(name = "logfile.port",defaultValue = "8081")
    private int logfilePort;
    
    @Inject
    @ConfigProperty(name = "logfile.host",defaultValue = "localhost")
    private String logfileHost;
    
    @Inject
    @ConfigProperty(name = "logfile.scheme",defaultValue = "http")
    private String logfileScheme;
    
    @Retry(delay = 10,delayUnit = ChronoUnit.SECONDS, maxRetries = 10)
    @CircuitBreaker(requestVolumeThreshold=2, failureRatio=0.5)
    public void receiveMessage(@ObservesAsync @Qualified("LogfileProvider") Message message){
        String url = String.format(URL_PATTERN, logfileScheme,logfileHost,logfilePort);
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        target = target.path(message.getBody());
 
        Invocation.Builder builder = target.request();
        Response response = builder.post(null);
        
        log.info(" Logfile Provider: " + url + " [" + response.getStatus() + "]");
    }
    
    private static final String URL_PATTERN = "%s://%s:%d/logfile-writer/api";
}