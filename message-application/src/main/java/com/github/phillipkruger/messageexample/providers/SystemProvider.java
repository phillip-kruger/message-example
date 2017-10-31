package com.github.phillipkruger.messageexample.providers;

import com.github.phillipkruger.messageexample.application.Message;
import com.github.phillipkruger.messageexample.application.Qualified;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import lombok.extern.java.Log;

@Log
@RequestScoped
public class SystemProvider {
    
    public void receiveMessage(@Observes @Qualified("SystemProvider") Message message){
        log.info(" System Provider: " + message.getBody());
    }
    
}