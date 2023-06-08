package com.gym.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .contact(contact())
                .title("Students DEMO Project")
                .description("project GYM BOOK for remote management of fitness room clients. ")
                .version("1.0-SNAPSHOT")
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name("MIT Licence")
                .url("https://opensource.org/licenses/mit-license.php");
    }

    private Contact contact() {
        return new Contact()
                .email("pablitoGym@gym.book")
                .name("Pavel Darafeyeu")
                .url("https://gym.com/academy");
    }
}
