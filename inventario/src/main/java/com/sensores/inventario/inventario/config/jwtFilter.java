package com.sensores.inventario.inventario.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sensores.inventario.inventario.service.securityService.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//logica de nuestro filtro de autentificacion

@Component
@RequiredArgsConstructor
public class jwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    /**
     * Filtra las peticiones y autentica al usuario con el token en el header
     * "Authorization" si este comienza con "Bearer ".
     *
     * Si el token es valido, este se autentica y se coloca en el contexto de
     * seguridad. Si no, se regresa la respuesta con un 401.
     *
     * @param request  La peticion que se esta procesando.
     * @param response La respuesta que se esta generando.
     * @param filterChain El siguiente filtro en la cadena de filtros.
     * @throws ServletException Si ocurre un error durante el manejo de la
     *         peticion.
     * @throws IOException Si ocurre un error durante el manejo de la peticion.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        /// si en header es nulo o no comienza con bearer
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            // regresa la respuesta en este caso un 401
            filterChain.doFilter(request, response);
            return;
        }
        // obtiene la cabecera del token, quitando el substring "Bearer"
        jwt = authHeader.substring(7);
        // llama al servicio para que obtenga el username de token que hemos obtenido
        username = jwtService.getUserName(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
