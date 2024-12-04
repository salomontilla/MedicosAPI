package com.med.api.infra.security;

import com.med.api.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//esta clase se encarga de filtrar las peticiones que llegan al servidor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    //Este método se encarga de filtrar las peticiones que llegan al servidor
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener el token del header
        var authToken = request.getHeader("Authorization");
        if( authToken!= null){
            var token = authToken.replace("Bearer ", "");//se obtiene el token y se reemplaza el Bearer
            var subject = tokenService.getSubject(token); // se obtiene el subject del token
            if ( subject != null){//si el subject no es nulo se busca el usuario en la base de datos
                var usuario = usuarioRepository.findByNombre(subject);
                var autenticacion = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); //se crea un token con el usuario
                SecurityContextHolder.getContext().setAuthentication(autenticacion); //se autentica al usuario
            }
        }
        filterChain.doFilter(request, response);
    }
}
