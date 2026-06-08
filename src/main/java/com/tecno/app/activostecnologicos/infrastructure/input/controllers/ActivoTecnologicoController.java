package com.tecno.app.activostecnologicos.infrastructure.input.controllers;

import com.tecno.app.activostecnologicos.application.ports.in.IActualizarActivoPort;
import com.tecno.app.activostecnologicos.application.ports.in.ICrearActivoPort;
import com.tecno.app.activostecnologicos.application.ports.in.IObtenerActivoPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearActivoCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import com.tecno.app.activostecnologicos.infrastructure.input.dto.ActivoFiltrosBusqueda;
import com.tecno.app.activostecnologicos.infrastructure.input.dto.ActualizarActivoRequest;
import com.tecno.app.activostecnologicos.infrastructure.input.dto.CrearActivoRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/activoTecnologico")
@AllArgsConstructor
class ActivoTecnologicoController {

    private final ICrearActivoPort crearActivoPort;
    private final IObtenerActivoPort obtenerActivoPort;
    private final IActualizarActivoPort actualizarActivoPort;



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ActivoResponse> crearActivoTecnologico(@Valid @RequestBody CrearActivoRequest request){

        CrearActivoCommand command = new CrearActivoCommand(request.numSerie(),request.marca(),request.modelo(),request.costo(),request.categoriaId());

        return ResponseEntity.ok(crearActivoPort.ejecutar(command));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ActivoResponse> obtenerActivoTecnologicoById(@PathVariable UUID id){

        return ResponseEntity.ok(obtenerActivoPort.ejecutar(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Page<ActivoResponse>> buscarActivoTecnologico(@Valid ActivoFiltrosBusqueda filtros){
        return ResponseEntity.ok(obtenerActivoPort.ejecutar(filtros.toQuery()));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ActivoResponse> actualizarActivoTecnologico(@PathVariable UUID id, @Valid @RequestBody ActualizarActivoRequest request){

        return ResponseEntity.ok(actualizarActivoPort.ejecutar(request.toCommand(id)));
    }


}
