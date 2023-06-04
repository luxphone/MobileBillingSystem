package com.woniu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "just a friend";
    private static final long EXPIRATION_TIME = 86400000;
    private static final long RENEWAL_THRESHOLD = 3600000;

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // 验证JWT是否过期
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public static String renewToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        Date expiration = claims.getExpiration();
        Date now = new Date();
        long remainingTime = expiration.getTime() - now.getTime();

        if (remainingTime < RENEWAL_THRESHOLD) {
            Date newExpiration = new Date(now.getTime() + EXPIRATION_TIME);
            claims.setExpiration(newExpiration);
            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        }
        return token;
    }
}
