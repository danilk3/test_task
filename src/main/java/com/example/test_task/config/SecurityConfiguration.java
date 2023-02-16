package com.example.test_task.config;

import com.example.test_task.security.jwt.JwtConfigurer;
import com.example.test_task.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
				.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
//                .authorizeHttpRequests(
//                        (auth) -> auth
//                                .requestMatchers("/api/auth/**")
//                                .permitAll()
//                                .requestMatchers("/api/quote/**")
//                                .permitAll()
//                                .requestMatchers("/api/quote/user/**")
//                                .authenticated())
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }
}
