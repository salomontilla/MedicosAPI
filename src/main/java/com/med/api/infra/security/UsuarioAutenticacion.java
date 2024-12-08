package com.med.api.infra.security;

import com.med.api.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//esta clase se encarga de autenticar al usuario
//autenticar es verificar que el usuario existe en la base de datos
@Service
public class UsuarioAutenticacion implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Este método se encarga de autenticar al usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//se recibe el nombre del usuario
        return usuarioRepository.findByNombre(username);//se busca el usuario en la base de datos
    }

}
