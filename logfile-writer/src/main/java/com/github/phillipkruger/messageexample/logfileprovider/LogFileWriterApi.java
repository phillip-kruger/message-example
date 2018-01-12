package com.github.phillipkruger.messageexample.logfileprovider;

import java.util.logging.Level;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import lombok.extern.java.Log;

/**
 * JAX-RS 2.0, Bean validation 1.1
 * 
 * The API for outside consumption.  
 * Print to the log file
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Consumes(MediaType.TEXT_PLAIN)
@Path("/")
public class LogFileWriterApi {
    
    @POST
    @Path("{level}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void log(@NotNull @PathParam("level")String level, String message) {
        Level l = Level.parse(level);
        log.log(l, ">>>>>>>>> {0}", message);
    }
    
}