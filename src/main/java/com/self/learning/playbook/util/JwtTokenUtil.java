package com.self.learning.playbook.util;


import com.self.learning.playbook.constant.Constant;
import com.self.learning.playbook.dto.internal.ValidateToken;
import com.self.learning.playbook.entity.User;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {


    public static String generateToken(User user, String secretKey, long expirationTime) {
        Date now = new Date(System.currentTimeMillis());
        Date expireDate = new Date(now.getTime() + expirationTime);
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("name", user.getFirstName());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static ValidateToken validateToken(String token, String SecretKey, HttpServletRequest httpServletRequest) {
        ValidateToken tokenDto = new ValidateToken();
        tokenDto.setStatus(false);
        try {
            Claims claims = Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody();
            tokenDto.setEmail((String) claims.get("email"));
            tokenDto.setStatus(true);
        } catch (SignatureException ex) {
            tokenDto.setMessage("Invalid Signature");
            httpServletRequest.setAttribute(Constant.SERVLET_MESSAGE, tokenDto.getMessage());
        } catch (MalformedJwtException ex) {
            tokenDto.setMessage("Invalid JWT token");
            httpServletRequest.setAttribute(Constant.SERVLET_MESSAGE, tokenDto.getMessage());
        } catch (ExpiredJwtException ex) {
            tokenDto.setMessage("Token Expired");
            httpServletRequest.setAttribute(Constant.SERVLET_MESSAGE, tokenDto.getMessage());
        } catch (UnsupportedJwtException ex) {
            tokenDto.setMessage("Unsupported token");
            httpServletRequest.setAttribute(Constant.SERVLET_MESSAGE, tokenDto.getMessage());
        } catch (IllegalArgumentException ex) {
            tokenDto.setMessage("Token not found");
            httpServletRequest.setAttribute(Constant.SERVLET_MESSAGE, tokenDto.getMessage());
        }
        return tokenDto;
    }

}
