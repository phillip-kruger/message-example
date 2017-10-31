package com.github.phillipkruger.messageexample.providers;

import com.github.phillipkruger.messageexample.application.Message;
import com.github.phillipkruger.messageexample.application.Qualified;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import lombok.extern.java.Log;

@Log
@RequestScoped
public class LogfileProvider {
    
    public void receiveMessage(@Observes @Qualified("LogfileProvider") Message message){
        log.info(" Logfile Provider: " + message.getBody());
    }
    
}