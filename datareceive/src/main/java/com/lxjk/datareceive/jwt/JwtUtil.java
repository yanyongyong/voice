package com.lxjk.datareceive.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ziv
 * @Date: 2018/5/17 17:26
 * @Description:
 */
public class JwtUtil {

    static final String SECRET = "ThisIsASecret";

    public static String generateToken(String username) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("username", username);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        /**jwt前面一般都会加Bearer*/
        return "Bearer "+jwt;
    }

    public static void validateToken(String token) {
        try {
            /**parse the token.*/
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        }catch (Exception e){
            throw new IllegalStateException("Invalid Token. "+e.getMessage());
        }
    }
}
