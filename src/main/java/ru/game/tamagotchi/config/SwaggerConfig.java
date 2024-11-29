package ru.game.tamagotchi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Tamagotchi")
                .version("1.0")
                .description("Игрушка тамачочи")
                .contact(new Contact()
                        .name("iLyHa1137 & Beges AKA ПАВУК")
                        .url("https://github.com/krnvxxx/tamagotchi")));
    };
}
