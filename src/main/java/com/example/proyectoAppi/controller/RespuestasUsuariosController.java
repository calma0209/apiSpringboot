package com.example.proyectoAppi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAppi.model.RespuestasUsuarios;
import com.example.proyectoAppi.service.RespuestasUsuariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/respuestas")
@RequiredArgsConstructor
public class RespuestasUsuariosController {

    private final RespuestasUsuariosService respuestasUsuariosService;

    @GetMapping
    @Operation(summary = "Obtener todas las respuestas", description = "Devuelve todas las respuestas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de respuestas obtenida correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay respuestas registradas")
    })
    public ResponseEntity<List<RespuestasUsuarios>> obtenerTodasRespuestasUsuarios() {
        List<RespuestasUsuarios> respuestas = respuestasUsuariosService.obtenerTodasLasRespuestas();
        return respuestas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(respuestas);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener respuestas de un usuario", description = "Devuelve todas las respuestas registradas por un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuestas obtenidas correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron respuestas para este usuario")
    })
    public ResponseEntity<List<RespuestasUsuarios>> obtenerRespuestasPorUsuario(@PathVariable Integer idUsuario) {
        List<RespuestasUsuarios> respuestas = respuestasUsuariosService.obtenerRespuestasPorUsuario(idUsuario);
        return respuestas.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(respuestas);
    }

    @GetMapping("/{idRespuesta}")
    @Operation(summary = "Obtener respuesta por ID", description = "Devuelve una respuesta específica según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró la respuesta")
    })
    public ResponseEntity<RespuestasUsuarios> obtenerRespuestasPorId(@PathVariable Integer idRespuesta) {
        return respuestasUsuariosService.obtenerRespuestaById(idRespuesta)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Respuesta con ID " + idRespuesta + " no encontrada"));
    }

    @PostMapping("/usuario/{idUsuario}/pregunta/{idPregunta}/opcion/{idOpcion}")
    @Operation(summary = "Registrar respuesta de usuario", description = "Guarda la respuesta de un usuario a una pregunta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes")
    })
    public ResponseEntity<RespuestasUsuarios> crearRespuesta(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idPregunta,
            @PathVariable Integer idOpcion,
            @RequestBody RespuestasUsuarios respuesta) {
        try {
            RespuestasUsuarios nuevaRespuesta = respuestasUsuariosService.crearRespuesta(idUsuario, idPregunta,
                    idOpcion, respuesta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRespuesta);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al registrar la respuesta");
        }
    }
}
