package com.example.proyectoAppi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyectoAppi.model.Juego;
import com.example.proyectoAppi.service.JuegoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/juegos")
@RequiredArgsConstructor
public class JuegoController {

    private final JuegoService juegoService;

    @PostMapping
    public ResponseEntity<Juego> crearJuego(@RequestBody Juego juego) {
        Juego nuevoJuego = juegoService.crearJuego(juego);
        return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Juego> obtenderTodosLosJuegos() {
        return juegoService.getAllJuegos();
    }

    @GetMapping("/{id}")
    public Optional<Juego> obtenerJuego(@PathVariable Integer id) {
        return juegoService.getJuegoById(id);
    }

    @PutMapping("/{id}")
    public Juego actualizarJuego(@PathVariable Integer id, @RequestBody Juego juego) {
        return juegoService.updateJuego(id, juego);
    }

    @DeleteMapping("/{id}")
    public void eliminarJuego(@PathVariable Integer id) {
        juegoService.deleteJuego(id);
    }

}
