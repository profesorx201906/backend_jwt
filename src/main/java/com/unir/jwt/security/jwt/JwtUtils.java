package com.unir.jwt.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.unir.jwt.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${unir.app.jwtSecret}")
  private String jwtSecret;

  @Value("${unir.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject((userPrincipal.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Firma Invalida para JWT : {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Token JWT Invalido : {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("El token JWT ha expirado : {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("El token JWT no es soportado : {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("La cadena JWT esta vacia : {}", e.getMessage());
    }

    return false;
  }
}
