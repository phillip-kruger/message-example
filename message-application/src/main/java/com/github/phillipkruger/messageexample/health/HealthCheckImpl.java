package com.github.phillipkruger.messageexample.health;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.json.JsonArray;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
/**
 *
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Dependent
@Health
public class HealthCheckImpl implements HealthCheck {

    @Inject
    @ConfigProperty(name = "providers")
    private JsonArray providers;
    
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder().name("MessageService")
            .withData("provider", providers.toString())
            .state(true)
            .build();
    }
    
}
