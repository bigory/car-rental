package by.itacademy.boldysh.web.config;


import by.itacademy.boldysh.service.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("by.itacademy.boldysh.web")
@Import(value = {InternationalizationConfig.class, ThymeleafConfig.class, ServiceConfig.class,SecurityConfig.class})
public class WebConfiguration {

}
