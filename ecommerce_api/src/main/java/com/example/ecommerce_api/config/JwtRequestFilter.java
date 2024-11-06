package com.example.ecommerce_api.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) {
            String jwtToken = request.getHeader("Authorization").substring(7);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    /*UserDetailsDto userDetailsDto = jwtTokenService.extractUserDetail(jwtToken);
                    Collection<? extends GrantedAuthority> authorityList=userDetailsDto.getAuthorityList().stream()
                            .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());*/
//                    User user=new User(userDetailsDto.getUsername(),null,userDetailsDto.isEnabled(),userDetailsDto.getInfo());
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            "Admin", null, null);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }catch (ExpiredJwtException eje) {
                    throw new UserInformException("JWT Token Expired");
                }catch(SignatureException se){
                    throw new UserInformException("JWT token not valid");
                }
                catch(ClassCastException cce){
                    throw new UserInformException("Some thing went wrong");
                }catch (RuntimeException e) {
                    e.printStackTrace();
                    throw new UserInformException("Some thing went wrong");
                }
            }
        }
        chain.doFilter(request, response);
    }

    /*private void makeExpiredJwtResponse(HttpServletResponse response,String message) throws IOException {
        //System.out.println("ExpiredJwtException exception ");
        System.out.println(message);
        response.setHeader("Content-Type", "application/json");
        response.addHeader("token-expired", "true");
        response.setStatus(401);
        response.getOutputStream().write(objectMapper.writeValueAsString(new HashMap<String, Object>() {{
            put("http-code", HttpStatus.UNAUTHORIZED.value());
            put("message", message);
            put("status", false);
        }}).getBytes());
    }*/
}
