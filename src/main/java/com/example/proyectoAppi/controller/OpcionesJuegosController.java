package com.example.proyectoAppi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyectoAppi.model.OpcionesJuegos;
import com.example.proyectoAppi.service.OpcionesJuegosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/opciones")
@RequiredArgsConstructor
public class OpcionesJuegosController {

    private final OpcionesJuegosService opcionesJuegosService;

    @PostMapping("/pregunta/{idPregunta}")
    public void crearOpcionSinEmocion(@PathVariable Integer idPregunta, @RequestBody OpcionesJuegos opcion) {
        opcionesJuegosService.crearOpcionSinEmocion(idPregunta, opcion);
    }

    @PostMapping("/pregunta/{idPregunta}/emocion/{idEmocion}")
    public void crearOpcionConEmocion(@PathVariable Integer idPregunta, @PathVariable Integer idEmocion,
            @RequestBody OpcionesJuegos opcion) {
        opcionesJuegosService.crearOpcionConEmocion(idPregunta, idEmocion, opcion);
    }

    @GetMapping
    public List<OpcionesJuegos> obtenerTodasLasOpciones() {
        return opcionesJuegosService.obtenerTodasLasOpciones();
    }

    @GetMapping("/pregunta/{idPregunta}")
    public List<OpcionesJuegos> obtenerOpcionesPorPregunta(@PathVariable Integer idPregunta) {
        return opcionesJuegosService.obtenerOpcionesPorPregunta(idPregunta);
    }

    @PutMapping("/{idOpcion}")
    public OpcionesJuegos actualizarOpcion(@PathVariable Integer idOpcion, @RequestBody OpcionesJuegos opcion) {
        return opcionesJuegosService.updateOpciones(idOpcion, opcion);
    }

    @DeleteMapping("/{id}")
    public void eliminarOpcion(@PathVariable Integer id) {
        opcionesJuegosService.deleteOpcion(id);
    }
}
