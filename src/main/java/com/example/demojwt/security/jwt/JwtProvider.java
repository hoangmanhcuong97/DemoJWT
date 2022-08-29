package com.example.demojwt.security.jwt;

import com.example.demojwt.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


// Lop nay de tao ra token sau khi dang nhap thanh cong
@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private static final String SECRET_KEY = "123456789";
    //    private static final long EXPIRE_TIME = 86400000000L; // thoi gian ton tai cua token
    private static final int EXPIRE_TIME = 86400; // thoi gian ton tai cua token

    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        //lay ra user hien tai cua phien dang nhap

        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // bat tat ca ngoai le cua token, neu token ko dung
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature --> Message: {}", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT Malformed --> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Invalid JWT ExpiredJwt --> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Invalid JWT UnsupportedJwt --> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid JWT IllegalArgument --> Message: {}", e);
        }
        return false;
    }

    //Sau khi co token thi phai lay username theo token nay
    public String getUserNameFromJwtToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return username;
    }

}
