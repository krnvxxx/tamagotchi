package ru.game.tamagotchi.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/user/profile").setViewName("profile");
        registry.addViewController("/user/register").setViewName("registration");
        registry.addViewController("/fight").setViewName("fight");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Разрешить все источники
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
