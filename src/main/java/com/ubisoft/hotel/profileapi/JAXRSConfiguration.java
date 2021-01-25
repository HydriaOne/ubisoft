package com.ubisoft.hotel.profileapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author fonsito
 */
@OpenAPIDefinition(info = @Info(
        title = "Api Gradle with Payara",
        version = "1.0.0"),
        servers = {
                @Server(url = "http://0.0.0.0:8080",description = "8080")
        }
)
@ApplicationPath("api")
@ApplicationScoped
public class JAXRSConfiguration extends Application {
    
}
