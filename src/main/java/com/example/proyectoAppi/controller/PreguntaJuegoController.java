package com.example.proyectoAppi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyectoAppi.model.PreguntaJuego;
import com.example.proyectoAppi.service.PreguntaJuegoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/preguntas")
@RequiredArgsConstructor
public class PreguntaJuegoController {

    private final PreguntaJuegoService preguntaJuegoService;

    @PostMapping("/juego/{id}")
    public void crearPregunta(@PathVariable Integer id, @RequestBody PreguntaJuego pregunta) {
        preguntaJuegoService.crearPregunta(id, pregunta);
    }

    @GetMapping("/juego/{id}")
    public List<PreguntaJuego> obtenerPreguntasPorJuego(@PathVariable Integer id) {
        return preguntaJuegoService.obtenerPreguntasPorJuego(id);
    }

    @GetMapping("/{id}")
    public Optional<PreguntaJuego> obtenerJuego(@PathVariable Integer id) {
        return preguntaJuegoService.getPreguntaById(id);
    }

    @GetMapping
    public List<PreguntaJuego> obtenerTodasLasPreguntas() {
        return preguntaJuegoService.obtenerTodasLasPreguntas();
    }

    @PutMapping("/{id}")
    public PreguntaJuego actualizarPreguntaJuego(@PathVariable Integer id, @RequestBody PreguntaJuego pregunta) {

        return preguntaJuegoService.updatePregunta(id, pregunta);
    }

    @DeleteMapping("/{id}")
    public void eliminarPregunta(@PathVariable Integer id) {
        preguntaJuegoService.deletePregunta(id);
    }

}
