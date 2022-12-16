package org.putra.util;

import io.jsonwebtoken.*;
import org.putra.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${token.jwt-secret}")
    private String secret;

    @Value("${token.jwt-expiration}")
    private Integer jwtExpiration;

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Expired Token");
        } catch (UnsupportedJwtException e) {
            throw new UnauthorizedException("Token Unsupported");
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("Token Malformed");
        } catch (SignatureException e) {
            throw new UnauthorizedException("Signature Unknown");
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedException("Token Invalid: " + e.getMessage());
        }
    }
}
