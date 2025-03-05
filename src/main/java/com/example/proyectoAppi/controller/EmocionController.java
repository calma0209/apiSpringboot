package com.example.proyectoAppi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyectoAppi.model.Emocion;
import com.example.proyectoAppi.service.EmocionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/emociones")
@RequiredArgsConstructor
public class EmocionController {

    private final EmocionService emocionS;

    @PostMapping
    public void crearEmocion(@RequestBody Emocion emotion) {
        emocionS.crearEmocion(emotion);
    }

    @GetMapping
    public List<Emocion> obtenerEmocions() {
        return emocionS.getAllEmociones();
    }

    @GetMapping("/{id}")
    public Optional<Emocion> obtenerUsuario(@PathVariable Integer id) {
        return emocionS.getEmocionById(id);
    }

    @PutMapping("/{id}")
    public Emocion actualizarEmocion(@PathVariable Integer id, @RequestBody Emocion emotion) {
        return emocionS.updateEmocion(id, emotion);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        emocionS.deleteEmocion(id);
    }
}
