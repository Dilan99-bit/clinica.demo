package com.Clinica.demo.repository;

import com.Clinica.demo.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByNombreCompleto(String nombreCompleto);
}
