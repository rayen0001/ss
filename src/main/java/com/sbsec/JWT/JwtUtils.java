package com.sbsec.JWT;

import com.sbsec.Services.UserDetailsImp;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String generateJwtToken(UserDetailsImp useprincipale) {
        return generateTokenFromUserName(useprincipale.getUsername());
    }

    private String generateTokenFromUserName(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+jwtExpiration))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    public String getusernameFromJwtToken(String token) {
        return  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        }catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
