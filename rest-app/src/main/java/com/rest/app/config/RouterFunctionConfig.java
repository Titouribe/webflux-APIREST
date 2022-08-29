package com.rest.app.config;

import com.rest.app.handlres.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ProductHandler handler){
        return RouterFunctions.route(RequestPredicates.GET("/app/product"), handler::findAll)
                .andRoute(RequestPredicates.GET("/app/product/{id}"), handler::findById);
    }
}
