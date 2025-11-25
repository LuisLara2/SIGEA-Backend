package com.zentry.sigea.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response , 
        FilterChain filterChain
    ) throws ServletException , IOException{

        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = header.substring(7);
            Claims claims = jwtUtil.extractClaims(token);

            String usuarioId = claims.get("usuarioId" , String.class);
            String email = claims.getSubject();
            
            List<String> roles = claims.get("roles" , List.class);

            var authorities = roles.stream()
                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                    .collect(Collectors.toList());

            UsuarioAuthInfo usuarioAuthInfo = new UsuarioAuthInfo(usuarioId, email);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        usuarioAuthInfo,
                        null,
                        authorities
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

}
