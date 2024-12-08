package com.med.api.controller;

import com.med.api.model.usuario.DatosUsuarioDTO;
import com.med.api.infra.security.DatosJWTokenDTO;
import com.med.api.infra.security.TokenService;
import com.med.api.model.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//este controlador se encarga de autenticar al usuario
@RestController
@RequestMapping("/login")
public class AutenticationContrller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    //Este método se encarga de autenticar al usuario
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosUsuarioDTO datosUsuario) {
        Authentication token = new UsernamePasswordAuthenticationToken(datosUsuario.nombre(), datosUsuario.clave());//se crea un token con el nombre y la clave del usuario
        var usuarioAutenticado = authenticationManager.authenticate(token);//se autentica al usuario
        String JWToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());//se genera el token
        return ResponseEntity.ok(new DatosJWTokenDTO(JWToken));//se devuelve el token en la respuesta
    }
}
