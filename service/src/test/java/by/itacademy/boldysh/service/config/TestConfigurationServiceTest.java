package by.itacademy.boldysh.service.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.itacademy.boldysh.service.util")
@Import( ServiceConfig.class)
public class TestConfigurationServiceTest {
}
