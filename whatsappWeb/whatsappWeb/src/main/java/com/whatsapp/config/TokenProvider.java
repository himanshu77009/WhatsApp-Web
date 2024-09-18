package com.whatsapp.config;

import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class TokenProvider {

    SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication){
        String jwt = Jwts.builder().setIssuer("XYZ")
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();
        return jwt;
    }

    public String getEmailFromToken(String jwt){
        jwt = jwt.substring(7);
        Claims claim = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claim.get("email"));
        return email;
    }
}
