package com.grupmoney.core_emprestimo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Emprestimo API")
                        .description("Simula emprestimos")
                        .version("v0.0.1")
                        .license(new License().name("Linkedin Stephanie Oliveira").url("https://www.linkedin.com/in/stephanie-ingrid-oliveira/")))
                .externalDocs(new ExternalDocumentation()
                        .description("Código Fonte GitHUb")
                        .url("https://github.com/Stephanie-Ingrid/Emprestimo-Application"));
    }
}