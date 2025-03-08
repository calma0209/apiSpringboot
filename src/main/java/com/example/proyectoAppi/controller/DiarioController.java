package com.example.proyectoAppi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAppi.model.Diario;
import com.example.proyectoAppi.service.DiarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/diario")
@RequiredArgsConstructor
public class DiarioController {

    private final DiarioService diarioS;

    @PostMapping("/crear/{idUsuario}")
    @Operation(summary = "Crear un registro en el diario", description = "Agrega una entrada al diario de un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Diario> crearRegistro(@PathVariable Integer idUsuario, @RequestBody Diario registro) {
        try {
            Diario nuevoRegistro = diarioS.crearRegistro(idUsuario, registro);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al crear el registro");
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los registros del diario", description = "Devuelve una lista de todos los registros del diario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de registros obtenida correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay registros en el diario")
    })
    public ResponseEntity<List<Diario>> obtenerTodosLosRegistros() {
        List<Diario> registros = diarioS.obtenerTodosLosRegistros();
        return registros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(registros);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener registros del diario por usuario", description = "Devuelve los registros del diario asociados a un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros para este usuario")
    })
    public ResponseEntity<List<Diario>> obtenerDiarioPorUsuario(@PathVariable Integer idUsuario) {
        List<Diario> registros = diarioS.obtenerDiarioPorUsuario(idUsuario);
        return registros.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(registros);
    }

    @DeleteMapping("/{idRegistro}")
    @Operation(summary = "Eliminar un registro del diario", description = "Elimina un registro específico del diario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Integer idRegistro) {
        if (!diarioS.obtenerRegistroById(idRegistro).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro con ID " + idRegistro + " no encontrado");
        }
        diarioS.eliminarRegistro(idRegistro);
        return ResponseEntity.noContent().build();
    }
}
