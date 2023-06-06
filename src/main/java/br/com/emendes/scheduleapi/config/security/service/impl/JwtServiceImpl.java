package br.com.emendes.scheduleapi.config.security.service.impl;

import br.com.emendes.scheduleapi.config.security.service.JwtService;
import br.com.emendes.scheduleapi.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Implementação de {@link JwtService}
 */
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

  @Value("${schedule-api.jwt.expiration}")
  private String expiration;

  @Value("${schedule-api.jwt.secret}")
  private String secret;

  @Override
  public String generateToken(User user) {
    return Jwts.builder()
        .setIssuer("Schedule API")
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key getKey() {
    byte[] secretBytes = secret.getBytes();
    return Keys.hmacShaKeyFor(secretBytes);
  }

}
