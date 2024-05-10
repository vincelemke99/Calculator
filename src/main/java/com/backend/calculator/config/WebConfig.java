package com.backend.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration indicates that this class contains one or more @Bean methods and may be processed by the
// Spring container to generate bean definitions and service requests for those beans at runtime.
@Configuration
public class WebConfig {

    // @Bean denotes a method that returns an object that should be registered as a bean in the Spring application context.
    // Here, the bean defined is a WebMvcConfigurer which includes CORS configuration.
    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            // Method to configure Cross-Origin Resource Sharing (CORS) settings.
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allows configuring CORS for all paths in the application.
                        .allowedOrigins("http://localhost:63343") // Specifies the allowed origins from which the server will accept requests.
                        .allowedMethods(HttpMethod.GET.name(),  // Allows GET requests.
                                HttpMethod.POST.name(), // Allows POST requests.
                                HttpMethod.DELETE.name()) // Allows DELETE requests.
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE,  // Allows "Content-Type" header.
                                HttpHeaders.AUTHORIZATION); // Allows "Authorization" header.
            }
        };
    }
}
