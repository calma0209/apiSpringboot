package com.example.proyectoAppi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAppi.model.usuario;
import com.example.proyectoAppi.repository.usuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class usuarioService {

    private final usuarioRepository usuarioR;

    public usuario crearUsuario(usuario user) {
        return usuarioR.save(user);
    }

    public List<usuario> getAllUsuarios() {
        return usuarioR.findAll();
    }

    public Optional<usuario> getUsuarioById(Integer id) {
        return usuarioR.findById(id);
    }

    public Optional<usuario> findByEmail(String email) {
        return usuarioR.findByEmail(email);
    }

    public usuario updateUsuario(Integer id, usuario newUsuario) {
        return usuarioR.findById(id)
                .map(user -> {
                    user.setNombre_usuario(newUsuario.getNombre_usuario());
                    user.setEmail(newUsuario.getEmail());
                    user.setContraseña(newUsuario.getContraseña());
                    user.setRol(newUsuario.getRol());
                    return usuarioR.save(user);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    public void deleteUsuario(Integer id) {
        usuarioR.deleteById(id);

    }
}