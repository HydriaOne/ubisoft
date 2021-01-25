package com.ubisoft.hotel.profileapi;

import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class HealthController implements HealthCheck {

    @Inject
    @ConfigProperty(name = "context.path")
    private String contextPath;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named(contextPath).up().build();
    }

}
