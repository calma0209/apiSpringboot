package com.example.proyectoAppi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyectoAppi.model.usuario;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Integer> {

}
