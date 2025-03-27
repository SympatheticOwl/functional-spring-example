package org.ldamler.example.web.routes;

import org.ldamler.example.web.handlers.RouteHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RoutesConfiguration {

    @Bean
    @RouterOperation(path = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
            operation = @Operation(
                    operationId = "get", summary = "test endpoint",
                    tags = {"Test"},
                    parameters = {
                            @Parameter(name = "id", in = ParameterIn.QUERY, required = true)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "OK",
                                    content = @Content(schema = @Schema(implementation = Object.class))
                            )
                    },
                    security = {
                            @SecurityRequirement(name = "JWT")
                    }
            )
    )
    public RouterFunction<ServerResponse> callRecords(RouteHandler routeHandler) {
        return route(GET("/"), routeHandler::get);
    }
}
