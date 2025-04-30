package com.example.ERP.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Log de la URI y método para depuración
        logger.debug("Procesando solicitud: {} {}");

        // Ignorar las rutas de autenticación y solicitudes OPTIONS
        if (request.getRequestURI().startsWith("/api/auth") || request.getMethod().equals("OPTIONS")) {
            logger.debug("Ignorando ruta de autenticación o solicitud OPTIONS");
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            logger.debug("Header 'Authorization' no encontrado o no comienza con 'Bearer '. Header: {}");
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        logger.debug("Token extraído: {}");
        try {
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);
                if (email != null && role != null) {
                    logger.debug("Token válido. Email: {}, Rol: {}");
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            email, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.debug("Contexto de seguridad establecido para: {}");
                } else {
                    logger.warn("Email o rol no extraídos del token. Email: {}, Rol: {}");
                }
            } else {
                logger.warn("Token no válido: {}");
            }
        } catch (Exception e) {
            logger.error("Error al procesar el token JWT: {}");
        }
        chain.doFilter(request, response);
    }
}