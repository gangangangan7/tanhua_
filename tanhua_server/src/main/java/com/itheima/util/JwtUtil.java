package com.itheima.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-05 10:10
 **/
@Component
public class JwtUtil {

    @Value("${tanhua.secret}")
    private String secret;

    /**
     * 生成JWT
     *
     * @return
     */
    public String createJWT(String phone,int userId) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("mobile", phone);
        claims.put("id", userId);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secret);
        return builder.compact();
    }

}

