package com.rest.app.handlres;

import com.rest.app.constants.Constants;
import com.rest.app.constants.ErrorConstants;
import com.rest.app.domain.entities.Product;
import com.rest.app.exceptions.RequestException;
import com.rest.app.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    @Autowired
    private IProductService productService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.findById(request.pathVariable("id")), Product.class)
                .switchIfEmpty(Mono
                        .error(new RequestException(Constants.NOT_FOUND, ErrorConstants
                                .notFound(Constants.PRODUCT, request.pathVariable("id")))));
    }
}
