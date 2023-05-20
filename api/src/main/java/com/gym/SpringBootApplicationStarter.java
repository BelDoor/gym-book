package com.gym;

import com.gym.configuration.ConfigurationApp;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.gym")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@Import(
        {ConfigurationApp.class}
)
public class SpringBootApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStarter.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
