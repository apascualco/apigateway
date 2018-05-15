package com.apascualco.blog.config;

import com.apascualco.blog.security.config.Authorization;
import com.apascualco.blog.security.config.GatewaySecurity;
import com.apascualco.blog.security.config.ResourceApiGateway;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SwaggerConfig.class,
        Authorization.class,
        GatewaySecurity.class,
        ResourceApiGateway.class
})
public class BaseConfig {
}
