package com.project.prjx.Security;

import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@NoArgsConstructor
@Component
public class JwtUtils {

    private final String jwtSigningKey = "xw3WtPzKxE0jUN2vHYuS4FfApTz0QeD2jK6Ibz7lZxqPUL9b9TkBblRgtq8z9zMk";

    private Claims extractAllClaims(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes());
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    public boolean hasClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    public String extractName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractTime(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    public String generateToken(BaseUserDto userDetails, Map<String, Object> claims) {
        return createToken(claims, userDetails);
    }

    public String generateToken(BaseUserDto userDetails) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", userDetails.getEmail().email());
        return createToken(map, userDetails);
    }

    private String createToken(Map<String, Object> claims, BaseUserDto userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", List.of(userDetails.getRole()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return extractTime(token).before(new Date());
    }

    public boolean isTokenValid(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes());
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
