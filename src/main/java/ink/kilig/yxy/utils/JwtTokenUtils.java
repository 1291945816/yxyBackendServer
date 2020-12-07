package ink.kilig.yxy.utils;

import ink.kilig.yxy.domain.YxyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/20 21:26
 * @description:
 */
@Component
public class JwtTokenUtils implements Serializable {
    private String base64_secret;//密钥
    private Long expiration_time; //过期时间
    private String header;

    public String getHeader() {
        return header;
    }


    public JwtTokenUtils(@Value("${jwt.base64-secret}") String base64_secret,
                         @Value("${jwt.token-validity-in-seconds}") Long expiration_time,
                         @Value(("${jwt.header}")) String header) {
        this.base64_secret = base64_secret;
        this.expiration_time = expiration_time*1000;
        this.header = header;
    }
    //用于生成token
    private String generateToken(Map<String,Object> claims){
        Date expirationDate=new Date((new Date()).getTime()+expiration_time);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, base64_secret).compact();
    }

    private Claims getClaimsFromToken(String token){
        Claims claims;
        claims=Jwts.parser()
                .setSigningKey(base64_secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    public String generateToken(YxyUser yxyUser){
        Map<String,Object> cliams=new HashMap<>();
        cliams.put("sub",yxyUser.getYxyUserName());
        cliams.put("created",new Date());
        return generateToken(cliams);
    }
    public String getUsernameFromToken(String token){
        String username;
        Claims claims=getClaimsFromToken(token);
        if (claims == null) return null;
        username=claims.getSubject();
        return username;
    }

    public Boolean isTokenExpired(String token){
        Claims claims=getClaimsFromToken(token);
        if(claims ==null) return false;
        Date expiration=claims.getExpiration();
        return expiration.before(new Date());
    }
    public String refreshToken(String token){
        String refreshToken;
        Claims claims=getClaimsFromToken(token);
        if (claims == null) return null;
        claims.put("created",new Date());
        refreshToken=generateToken(claims);
        return refreshToken;
    }
    /**
     * 用于校验Token是否过期
     * @param Token
     *
     * @return
     */
    public Boolean validateToken(String Token,YxyUser yxyUser){
        String username=getUsernameFromToken(Token);
        if (username == null) return false;
        return (username.equals(yxyUser.getYxyUserName()))&& !isTokenExpired(Token);

    }

}
