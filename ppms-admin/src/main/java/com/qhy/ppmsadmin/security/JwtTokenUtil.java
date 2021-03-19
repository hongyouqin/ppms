package com.qhy.ppmsadmin.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.qhy.ppmsadmin.common.config.CustomConfig;

import org.antlr.v4.parse.ANTLRParser.exceptionGroup_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtToken生成的工具类 JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：{"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781} signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    public JwtTokenUtil(String secret, String tokenHead, Long expiration) {
        this.secret = secret;
        this.tokenHead = tokenHead;
        this.expiration = expiration;
    }

    private String secret;
    private Long expiration;
    private String tokenHead;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, email);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            // LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        if (oldToken == null || oldToken.isEmpty()) {
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if (token == null || token.isEmpty()) {
            return null;
        }
        // token校验不通过
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        // 如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        // 如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }

        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
        // // 如果token在30分钟之内刚刷新过，返回原token
        // if (tokenRefreshJustBefore(token, 30 * 60)) {
        // return token;
        // } else {
        // claims.put(CLAIM_KEY_CREATED, new Date());
        // return generateToken(claims);
        // }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * 
     * @param token 原token
     * @param time  指定时间（秒）
     */
    // private boolean tokenRefreshJustBefore(String token, int time) {
    // Claims claims = getClaimsFromToken(token);
    // Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
    // Date refreshDate = new Date();
    // // 刷新时间在创建时间的指定时间内
    // if (refreshDate.after(created) &&
    // refreshDate.before(DateUtil.offsetSecond(created, time))) {
    // return true;
    // }
    // return false;
    // }
}
