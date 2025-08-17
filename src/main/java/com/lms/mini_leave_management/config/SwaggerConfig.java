package com.lms.mini_leave_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI leaveManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini Leave Management System API")
                        .description("APIs for managing employee leaves")
                        .version("1.0"));
    }
}
