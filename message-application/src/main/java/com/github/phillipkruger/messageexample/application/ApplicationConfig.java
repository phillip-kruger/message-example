package com.github.phillipkruger.messageexample.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS 2.0
 * 
 * Demonstrate how to activate JAX-RS. 
 * All REST Endpoints available under /api
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@ApplicationPath("/api")
public class ApplicationConfig extends Application {
}
