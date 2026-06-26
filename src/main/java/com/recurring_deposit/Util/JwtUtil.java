package com.recurring_deposit.Util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /*
     * CREATE SIGNING KEY
     */
    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(
                secretKey.getBytes(
                        StandardCharsets.UTF_8));
    }

    /*
     * GENERATE TOKEN
     */
    public String generateToken(
            String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(
                        new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtExpiration))
                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    /*
     * EXTRACT ALL CLAIMS
     */
    private Claims extractAllClaims(
            String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /*
     * EXTRACT USERNAME
     */
    public String extractUsername(
            String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    /*
     * EXTRACT EXPIRATION
     */
    public Date extractExpiration(
            String token) {

        return extractAllClaims(token)
                .getExpiration();
    }

    /*
     * CHECK TOKEN EXPIRY
     */
    public boolean isTokenExpired(
            String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    /*
     * VALIDATE TOKEN
     */
    public boolean validateToken(
            String token,
            String username) {

        try {

            String extractedUsername =
                    extractUsername(token);

            return extractedUsername.equals(username)
                    && !isTokenExpired(token);

        } catch (Exception e) {

            return false;
        }
    }

    /*
     * BASIC TOKEN VALIDATION
     */
    public boolean validateToken(
            String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(
                            getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}