package com.github.phillipkruger.messageexample.providers;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

/**
 * Making REST call to external providers
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * @see https://www.eclipse.org/community/eclipse_newsletter/2017/september/article4.php
 */
@Log
@Stateless
@Retry(delay = 5,delayUnit = ChronoUnit.SECONDS, maxRetries = 5)
//@CircuitBreaker(requestVolumeThreshold=2, failureRatio=0.5)
public class RestCaller {
    
    @Inject
    private PostQueue postQueue;
    
    private Client client;
    
    @PostConstruct
    public void init(){
        this.client = ClientBuilder.newClient();   
    }
    
    @PreDestroy
    public void destroy(){
        this.client.close();
        this.client = null;
    }
    
    @Fallback(fallbackMethod = "fallbackPost")
    //@Bulkhead(value = 2, waitingTaskQueue = 1)
    public Response post(String url,Entity<?> payload,String... path){
        
        WebTarget target = client.target(url);
        for(String p:path){
            target = target.path(p);
        }
        
        Invocation.Builder builder = target.request();
        Response r = builder.post(payload);
        
        // All is good, let's clear the queue
        postQueue.serveNow();
        
        return r;
    }
    
    @Asynchronous
    @Fallback(fallbackMethod = "fallbackPostAsync")
    public Future<Response> postAsync(String url,Entity<?> payload,String... path){
        return new AsyncResult<>(post(url,payload, path));
    }
    
    public Response fallbackPost(String url,Entity<?> payload,String... path){
        postQueue.push(url, payload, path);
        return Response.serverError().build();
    }
    
    public Future<Response> fallbackPostAsync(String url,Entity<?> payload,String... path){
        return new AsyncResult<>(fallbackPost(url,payload, path));
    }
    
}
