package com.grocery.config;


import com.grocery.filter.UserAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectSecuirtyConfig {


    @Autowired
    private UserAuthFilter userAuthFilter;

    @Autowired
    private URLConfig urlConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corscustomize -> corscustomize.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200/"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .csrf(csrfCustomizer -> csrfCustomizer.disable())
                /*  csrfCustomizer.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/auth/**")
                          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))*/
                .addFilterBefore(userAuthFilter, UsernamePasswordAuthenticationFilter.class);
        //.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
        // .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**","/task/**").permitAll())
        // http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
      /*  Map<String, List<String>> urlRoleMappings = urlConfig.getURLS();

        for (Map.Entry<String, List<String>> entry : urlRoleMappings.entrySet()) {
            String roles = entry.getKey();
            List<String> urlPattern = entry.getValue();

            if (roles != null && roles.equalsIgnoreCase("USER")) {
                http.authorizeHttpRequests((requests) -> urlPattern.forEach(url -> requests
                        .requestMatchers(url).hasAnyRole("USER", "ADMIN")));
            }
            else if (roles != null && roles.equalsIgnoreCase("ADMIN")) {
                http.authorizeHttpRequests((requests) -> urlPattern.forEach(url -> requests
                        .requestMatchers(url).hasAnyRole("ADMIN")));
            }
            else if (roles != null && roles.equalsIgnoreCase("PUBLIC")) {
                http.authorizeHttpRequests((requests) -> urlPattern.forEach(url -> requests
                        .requestMatchers(url).permitAll()));
            }
        }*/
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/grocery/user/**", "/grocery/carts/**", "/grocery/orders/**", "/grocery/product/get/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/grocery/product/**").hasRole("ADMIN")
                        .requestMatchers("/grocery/auth/**").permitAll());
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
