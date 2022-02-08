package csf.d6.weather;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{
    
    public void addCorsMapping(CorsRegistry reg) {
        reg.addMapping("/api/**");
    }
}
