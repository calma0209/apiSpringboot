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

import com.example.proyectoAppi.model.Recompensa;
import com.example.proyectoAppi.service.RecompensaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/recompensa")
@RequiredArgsConstructor
public class RecompensaController {

    private final RecompensaService recompensaService;

    @PostMapping
    public void crearRecompensa(@RequestBody Recompensa recompensa) {
        recompensaService.crearRecompensa(recompensa);
    }

    @GetMapping
    public List<Recompensa> obtenerTodasLRecompensas() {
        return recompensaService.obtenerTodasRecompensas();
    }

    @GetMapping("/{id}")
    public Optional<Recompensa> obtenerRecompensaPorId(@PathVariable Integer id) {
        return recompensaService.obtenerRecompensaById(id);
    }

    @PutMapping("/{id}")
    public void actualizarRecompensa(@PathVariable Integer id, @RequestBody Recompensa recompensa) {
        recompensaService.updateRecompensa(id, recompensa);
    }

    @DeleteMapping("/{id}")
    public void borrarRecompensa(@PathVariable Integer id) {
        recompensaService.borrarRecompensa(id);
    }
}
