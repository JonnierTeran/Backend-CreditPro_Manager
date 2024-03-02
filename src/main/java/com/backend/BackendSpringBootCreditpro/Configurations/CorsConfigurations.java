package com.backend.BackendSpringBootCreditpro.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Clase de configuracion que sera invocada en el contenedor de springboot
@Configuration
public class CorsConfigurations {

    /**
     * @Beans // Metodo que sera invocado y ejecutado en el contenedor de springbbot
     *        al inicializar la webapp
     * @Descripcion: Metodo para configuracion de cors, origines, permisos, y
     *               metodos permitidos
     * 
     * @Note: Acepta Peticiones desde 
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //Configuracion para controlador /Administrator
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }

}
