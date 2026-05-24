package com.Clinica.demo.repository;

import com.Clinica.demo.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByNombreCompleto(String nombreCompleto);
}
