package com.med.api.controller;

import com.med.api.ModeloDTO.DatosDireccionDTO;
import com.med.api.ModeloDTO.DatosMedicoDTO;
import com.med.api.ModeloDTO.DatosMedicoRespuestaDTO;
import com.med.api.ModeloDTO.MedicoListaDTO;
import com.med.api.model.ActualizarMedicoDTO;
import com.med.api.model.Medico;
import com.med.api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity <DatosMedicoRespuestaDTO> registrarMedico(@RequestBody @Valid DatosMedicoDTO medico, UriComponentsBuilder uriBuilder){
        Medico nuevoMedico = medicoRepository.save(new Medico(medico));
        DatosMedicoRespuestaDTO datosMedico = new DatosMedicoRespuestaDTO(nuevoMedico.getId(), nuevoMedico.getNombre(), nuevoMedico.getApellido(),
                nuevoMedico.getEmail(), nuevoMedico.getTelefono(), nuevoMedico.getEspecialidad().toString(),
                new DatosDireccionDTO(nuevoMedico.getDireccion().getCalle(), nuevoMedico.getDireccion().getBarrio(),
                        nuevoMedico.getDireccion().getCiudad()));
        URI url = uriBuilder.path("/medicos/{id}").buildAndExpand(nuevoMedico.getId()).toUri();
        return ResponseEntity.created(url).body(datosMedico);
    }
    @GetMapping
    public ResponseEntity<Page<MedicoListaDTO>> listarMedicos(Pageable pageable){ //@PageableDefault(size = 10) para dar valores por defecto
        //return medicoRepository.findAll(pageable).map(MedicoListaDTO::new)
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(MedicoListaDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity <DatosMedicoRespuestaDTO> actualizarMedico(@RequestBody @Valid  ActualizarMedicoDTO medico){
        Medico nuevoMedico = medicoRepository.getReferenceById(medico.id());
        nuevoMedico.actualizarDatos(medico);
        return ResponseEntity.ok(new DatosMedicoRespuestaDTO(nuevoMedico.getId(), nuevoMedico.getNombre(), nuevoMedico.getApellido(),
                nuevoMedico.getEmail(), nuevoMedico.getTelefono(), nuevoMedico.getEspecialidad().toString(),
                    new DatosDireccionDTO(nuevoMedico.getDireccion().getCalle(), nuevoMedico.getDireccion().getBarrio(),
                            nuevoMedico.getDireccion().getCiudad())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    /*
    DELETE A NIVEL BD
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity <DatosMedicoRespuestaDTO> listarDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosMedicoRespuestaDTO(medico.getId(), medico.getNombre(), medico.getApellido(),
                medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccionDTO(medico.getDireccion().getCalle(), medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad())));
    }
}
