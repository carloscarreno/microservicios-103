package com.aironmoutain.identidades.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtService {

   private static final Logger log = LoggerFactory.getLogger(JwtService.class);
 
   @Value("${jwt.secret.key}") 
   private String secretKey;      // Para firmar el token
   
   @Value("${jwt.time.expiration}")
   private String timeExpiration; //tiempo de expiracion del token

  
   // Este metodo crea un token
   public String generateToken(String username){
      return Jwts.builder()
                 .setSubject(username)
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                 .claim("rol", "USER")      // atributos personalizados
                 .claim("unit", "training")
                 .claim("country", "pe")
                 .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                 .compact();
   }

   public Key getSignatureKey(){
    // byte[] keyBytes = Decoders.BASE64.decode(secretKey); 
    // return Keys.hmacShaKeyFor(keyBytes);
    return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
   }

   // Validar el token de acceso
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
            return true;
        } catch (JwtException e) {
            log.error("Token invalido".concat(e.getMessage()));
            return false;
        }
    }

   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

}


// Generadores en linea
// https://www.vondy.com/random-key-generator--ZzGGMYgS
  // https://www.xconvert.com/unit-converter/days-to-milliseconds