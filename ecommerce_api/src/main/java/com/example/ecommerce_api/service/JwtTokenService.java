package com.example.ecommerce_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ecommerce_api.dto.AuthExtInfoDto;
import com.example.ecommerce_api.entity.Admin;
import com.example.ecommerce_api.entity.GuestInfo;
import com.example.ecommerce_api.entity.Role;
import com.example.ecommerce_api.exception.UserInformException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    private final ObjectMapper objectMapper;

    @Value("${jwt_secret_key}")
    private String SECRET_KEY;

    public JwtTokenService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String generateCustomerToken(GuestInfo guestInfo, List<String> roleList) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authExtInfoDto", new AuthExtInfoDto("customer", null, null));
        claims.put("username", guestInfo.getUsername());
        claims.put("authorityList", roleList);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(guestInfo.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String generateAdminToken(Admin admin, List<String> roleList) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authExtInfoDto", new AuthExtInfoDto("admin", "org1", "organization A"));
        claims.put("username", admin.getUsername());
        claims.put("authorityList", roleList);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(admin.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUserDetail(String jwtToken) {
        if (extractClaim(jwtToken, Claims::getExpiration).before(new Date())) {
            throw new UserInformException("JWT token is expired");
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
            List<String> authorityList = (List<String>) claims.get("authorityList");

            Admin info = objectMapper.convertValue(claims.get("ext_info"), Admin.class);
            /*
             * return new UserDetailsDto((String) claims.get("username"), authorityList,
             * info, (boolean) claims.get("enabled"), (boolean) claims.get("rememberMe"));
             */
            return null;
        } catch (RuntimeException se) {
            throw new UserInformException("JWT token not valid");
        }
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (SignatureException se) {
            throw new SignatureException("JWT token not valid");
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /*
     * public Boolean validateToken(String token, UserDetails userDetails) {
     * final String username = extractUsername(token);
     * return (username.equals(userDetails.getUsername()) &&
     * !isTokenExpired(token));
     * }
     */

}
