package io.reflectoring.rentAcar.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(
                                        "/auth/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html"
                                )
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,"/branch-addresses/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/branch-addresses/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/branch-addresses/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/branch-addresses/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.GET,"/branches/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/branches/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/branches/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/branches/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.GET,"/cars/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/cars/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/cars/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/cars/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.GET,"/customers/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/customers/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/customers/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/customers/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.GET,"/insurances/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/insurances/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/insurances/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/insurances/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.GET,"/payments/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/payments/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/payments/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/payments/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.GET,"/rentals/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/rentals/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/rentals/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/rentals/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.GET,"/staff/**").hasAnyAuthority("USER","ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.POST,"/staff/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.PUT,"/staff/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .requestMatchers(HttpMethod.DELETE,"/staff/**").hasAnyAuthority("ADMIN","SUPER_USER")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
