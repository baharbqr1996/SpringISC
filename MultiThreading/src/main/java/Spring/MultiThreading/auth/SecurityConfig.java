package Spring.MultiThreading.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(csrf -> csrf.disable()) // غیرفعال‌سازی CSRF برای توسعه؛ در تولید فعال باشد.
    	.authorizeHttpRequests(requests -> 
		requests.requestMatchers("/rest/auth/**").permitAll()
        .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults()); // استفاده از httpBasic با تنظیمات پیش‌فرض
        return http.build();
    }
}

