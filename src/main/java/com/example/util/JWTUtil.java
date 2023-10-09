package com.example.util;

import com.example.dto.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.exp.UnAuthorizedException;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JWTUtil {

    private static final String secretKey = "!maz234^gikey";
    private static final int tokenLiveTime = 1000 * 3600 * 24*7; // 1 week

    private static final long emailTokenLiveTime = tokenLiveTime; // 1 week
    public static String encode(String phone, ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("phone", phone);
        jwtBuilder.claim("role", role.toString());

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("kunuz test portali");
        return jwtBuilder.compact();
    }
    public static JwtDTO decode(String token) {
        try {
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey(secretKey);

            Jws<Claims> jws = jwtParser.parseClaimsJws(token);

            Claims claims = jws.getBody();

            String phone = (String) claims.get("phone");

            String role = (String) claims.get("role");
            ProfileRole profileRole = ProfileRole.valueOf(role);

            return new JwtDTO(phone, profileRole);
        } catch (JwtException e) {
            throw new UnAuthorizedException("Your session expired");
        }
    }


    public static String encodeEmailJwt(UUID profileId) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("id", profileId);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (emailTokenLiveTime)));
        jwtBuilder.setIssuer("100k test portali");
        return jwtBuilder.compact();
    }

    public static JwtDTO decodeEmailJwt(String token) {
        try {
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey(secretKey);
            Jws<Claims> jws = jwtParser.parseClaimsJws(token);
            Claims claims = jws.getBody();
            UUID id = (UUID) claims.get("id");
            return new JwtDTO(id, null);
        } catch (JwtException e) {
            throw new UnAuthorizedException("Your session expired");
        }
    }
}
