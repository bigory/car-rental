package by.itacademy.boldysh.service.config;


import by.itacademy.boldysh.database.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("by.itacademy.boldysh.service")
@Import(DatabaseConfig.class)
public class ServiceConfig {
}
