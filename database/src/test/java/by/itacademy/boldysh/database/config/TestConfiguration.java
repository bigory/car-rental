package by.itacademy.boldysh.database.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.itacademy.boldysh.database.util")
@Import(DatabaseConfig.class)
public class TestConfiguration {
}
