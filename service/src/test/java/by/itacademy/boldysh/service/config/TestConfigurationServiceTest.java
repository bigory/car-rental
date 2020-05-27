package by.itacademy.boldysh.service.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.itacademy.boldysh.service.util")
@Import(ServiceConfig.class)
public class TestConfigurationServiceTest {

    @Bean
    public CacheManager cacheManager() {
        return new NoOpCacheManager();
    }
}
