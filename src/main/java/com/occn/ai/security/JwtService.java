package com.occn.ai.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Token 有效期
    @Value("${occn.token.expiration}")
    private Long EXPIRATION_TIME; // 分钟

    @Value("${occn.token.refresh_expiration}")
    private Long REFRESH_EXPIRATION_TIME; // 分钟

    // 私钥
    @Value("${occn.token.secret}")
    private String SECRET_KEY;

    public String extractUserAccount(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String account) {
        return generateToken(new HashMap<>(), account, EXPIRATION_TIME);
    }

    public String generateRefreshToken(String account) {
        return generateToken(new HashMap<>(), account, REFRESH_EXPIRATION_TIME);
    }

    /**
     * 签发Token
     * @param extractClaims Map<String, Object>
     * @param account 以用户账号为jwt标识
     * @return Token
     */
    public String generateToken(Map<String, Object> extractClaims, String account, long expirationTime) {
        long time = System.currentTimeMillis();
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(account)
                .setIssuedAt(new Date(time))
                .setExpiration(new Date(time + (expirationTime * 60000)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String account = extractUserAccount(token);
        return (account.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate = extractExpiration(token);
        return expirationDate != null && expirationDate.before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 獲取令牌中所有的聲明
     * @return 令牌中所有的聲明
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
