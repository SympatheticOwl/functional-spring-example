package org.ldamler.example.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private final List<String> tags = List.of();

    @Bean
    public OpenAPI openApi() {
        var securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer")
                .in(SecurityScheme.In.HEADER);

        return new OpenAPI().info(new Info().title("Example Project")
                        .description("Spring example starter project"))
                .components(new Components().addSecuritySchemes("JWT", securityScheme))
                .tags(buildTags());
    }

    private List<Tag> buildTags() {
        return tags.stream().map(tag -> new Tag().name(tag)).toList();
    }

}
