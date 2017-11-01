package com.github.phillipkruger.messageexample.providers;

import com.github.phillipkruger.messageexample.application.Message;
import com.github.phillipkruger.microprofileextentions.cdifilter.Filter;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import lombok.extern.java.Log;

@Log
@RequestScoped
public class SystemProvider {
    
    public void receiveMessage(@Observes @Filter(forClass = SystemProvider.class) Message message){
        log.log(Level.INFO, "SystemProvider {0}", message.getBody());
    }
}