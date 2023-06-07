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

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
      ServerHttpSecurity http, ReactiveAuthenticationManager authenticationManager) {
    http.csrf().disable()
        .httpBasic().disable();

    http.authenticationManager(authenticationManager)
        .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.HTTP_BASIC);

    http.authorizeExchange()
        .pathMatchers(HttpMethod.POST, POST_WHITELIST).permitAll()
        .anyExchange().authenticated();

    return http.build();
  }


}
