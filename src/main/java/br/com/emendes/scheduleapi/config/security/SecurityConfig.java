package br.com.emendes.scheduleapi.config.security;

import br.com.emendes.scheduleapi.config.security.filter.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configurações de segurança.
 */
@RequiredArgsConstructor
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

  private final JWTAuthenticationFilter jwtAuthenticationFilter;

  private static final String[] POST_WHITELIST = {"/api/auth", "/api/users"};
  private static final String[] SWAGGER_WHITELIST = {"/swagger-ui.html", "/swagger-ui/**","/webjars/**", "/v3/api-docs/**"};

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
      ServerHttpSecurity http, ReactiveAuthenticationManager authenticationManager) {
    http.csrf().disable()
        .httpBasic().disable()
        .formLogin().disable();

    http.authenticationManager(authenticationManager)
        .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.HTTP_BASIC);

    http.authorizeExchange()
        .pathMatchers(HttpMethod.POST, POST_WHITELIST).permitAll()
        .pathMatchers(HttpMethod.GET, SWAGGER_WHITELIST).permitAll()
        .pathMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
        .anyExchange().authenticated();

    return http.build();
  }


}
