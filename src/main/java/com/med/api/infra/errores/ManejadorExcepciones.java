package com.med.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity manejadorError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorDTO::new).toList(); //errores es una lista de objetos DatosErrorDTO que se crean a partir de los errores de validacion
        return ResponseEntity.badRequest().body(errores);
    }
    //este record mapea los errores de validacion y los convierte en un objeto que se puede devolver en el body de la respuesta
    private record DatosErrorDTO(String error, String mensaje) {
        public DatosErrorDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
