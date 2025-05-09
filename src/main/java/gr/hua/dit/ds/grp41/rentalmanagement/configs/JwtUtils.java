package gr.hua.dit.ds.grp41.rentalmanagement.configs;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger log=LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @SuppressWarnings("deprecation")
    public String generateToken(Authentication auth) {
        UserDetails userMain=(UserDetails) auth.getPrincipal();
        
        return Jwts.builder().setSubject(userMain.getUsername())
               .claim("authorities", userMain.getAuthorities())
               .setIssuedAt(new Date())
               .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
               .signWith(SignatureAlgorithm.HS256, getSignInKey())
               .compact();

    }

    private Key getSignInKey() {
        byte[] keyBytes=Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromJwtToken(String token) {
        @SuppressWarnings("deprecation")
        Claims claims=Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @SuppressWarnings("deprecation")
    public boolean validateJwtToken(String authToken) throws SignatureException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);

            return true;
        } catch (MalformedJwtException e) {
            log.error("token {}", authToken);
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claim string is empty: {}", e.getMessage());
        }

        return false;
    }
}
