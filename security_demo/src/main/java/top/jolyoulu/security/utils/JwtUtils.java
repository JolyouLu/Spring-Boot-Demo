package top.jolyoulu.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/17 0:24
 * @Version 1.0
 */
public class JwtUtils {

    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return token;
    }

    public static void main(String[] args) {
        //测试jwt工具类的使用
        Map<String, Object> claims = new HashMap<>();
        claims.put("token", 123);
        String jwt = JwtUtils.createToken(claims);
        System.out.println(jwt);
        Claims token = JwtUtils.parseToken(jwt);
        System.out.println(token.getSubject());
    }
}
