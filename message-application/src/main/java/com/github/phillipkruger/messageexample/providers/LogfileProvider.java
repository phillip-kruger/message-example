package com.github.phillipkruger.messageexample.providers;

import com.github.phillipkruger.messageexample.application.Message;
import com.github.phillipkruger.microprofileextentions.cdifilter.Filter;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

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
    
    @Inject
    private RestCaller restCaller;
    
    
    public void receiveMessage(@Observes @Filter(forClass = LogfileProvider.class) Message message){
        String url = String.format(URL_PATTERN, logfileScheme,logfileHost,logfilePort);
        //restCaller.post(url, null, message.getBody());
        restCaller.postAsync(url, null, message.getBody());
        
    }
    
    private static final String URL_PATTERN = "%s://%s:%d/logfile-writer/api";
}