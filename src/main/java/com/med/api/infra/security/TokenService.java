package com.med.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.med.api.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

//Esta clase se encarga de generar el token y de gestionarlo
@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()//se crea el token
                    .withIssuer("Voll Med")//se añade el emisor del token
                    .withSubject(usuario.getNombre()) //se añade el subject del token
                    .withClaim("id", usuario.getId()) //se añade el id del usuario
                    .withExpiresAt(generarFechaExpiracion()) //se añade la fecha de expiración del token
                    .sign(algorithm);//se firma el token con el algoritmo HMAC256
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

    public Instant generarFechaExpiracion() {
        return Instant.now().plus(1, ChronoUnit.HOURS);
    }
    //Obtiene el subject del token, esto lo hace usando el JWT require y el algoritmo HMAC256
    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.require(algorithm)
                    .withIssuer("Voll Med")//esto sirve para verificar que el token fue creado por el servidor
                    .build().verify(token).getSubject();// se obtiene el subject del token

        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

}
