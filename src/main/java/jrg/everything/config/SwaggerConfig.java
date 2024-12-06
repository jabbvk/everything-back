package jrg.everything.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Documentación oficial de la API de Everything")
                    .description("Esta es la descripción de la API de la aplicación Eerything. Está creada con Springdoc OpenAPI 3.0.1")
                    .version("1.0.0"));
    }
}
