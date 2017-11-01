package com.github.phillipkruger.messageexample.providers;

import java.util.LinkedList;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple Queue for post objects
 * @author Phillip Kruger (phillip.kruger@phillip.kruger.com)
 */
@Singleton
public class PostQueue {
 
    private LinkedList<RestPost> postQueue = new LinkedList<>();
    
    @Inject
    private RestCaller restCaller;
    
    @Asynchronous
    public void push(String url,Entity<?> payload,String... path){
        postQueue.push(new RestPost(url, payload, path));
    }
    
    public boolean isEmpty(){
        return postQueue.isEmpty();
    }

    @Asynchronous
    public synchronized void serveNow() {
        while (!postQueue.isEmpty()) {
            RestPost post = postQueue.pop();
            restCaller.post(post.getUrl(), post.getPayload(), post.getPath());
        }
    
    }
    
    @Data @AllArgsConstructor @NoArgsConstructor
    class RestPost {
        private String url;
        private Entity<?> payload;
        private String[] path;
    }
}
