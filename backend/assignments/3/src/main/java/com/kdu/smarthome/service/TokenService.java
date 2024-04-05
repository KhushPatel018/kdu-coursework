package com.kdu.smarthome.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * Service class for token-related operations.
 */
@Service
@Slf4j
public class TokenService {
    // Symmetric key for encoding/decoding
    private static final String SECRET_KEY = "aaaafdfvgggggggggytffyvhvvgggggffdswqeeeeeeyygvvd2434t66vvv";

    /**
     * Generates a JWT token for the provided user details.
     *
     * @param userDetails The details of the user for whom the token is generated.
     * @return            The generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        log.info(userDetails.toString());
        return Jwts.builder()
                .header()
                .add("type", "JWT")
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 24 hours expiration
                .signWith(getSignInKey())
                .compact();
    }

    /**
     * Validates whether a given token is valid for the provided user details.
     *
     * @param token       The token to be validated.
     * @param userDetails The user details against which the token is validated.
     * @return            True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Extracts a claim from the provided token.
     *
     * @param token          The token from which the claim is extracted.
     * @param claimsResolver The resolver function for extracting the claim.
     * @param <T>            The type of the claim.
     * @return               The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extracts the username from the provided token.
     *
     * @param token The token from which the username is extracted.
     * @return      The extracted username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
