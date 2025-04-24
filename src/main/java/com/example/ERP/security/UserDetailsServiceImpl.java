package com.example.ERP.security;

import com.example.ERP.domain.entities.Usuario;
import com.example.ERP.infrastructure.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Cargando usuario por email: {}", email);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
        logger.debug("Usuario encontrado: email={}, username={}, rol={}",
                usuario.getEmail(), usuario.getUsername(), usuario.getRol() != null ? usuario.getRol().getNombre() : "nulo");
        if (usuario.getRol() == null) {
            throw new RuntimeException("El rol del usuario es nulo para el email: " + email);
        }
        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre())));
    }
}