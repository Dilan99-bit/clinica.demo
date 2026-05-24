package com.Clinica.demo.repository;

import com.Clinica.demo.model.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {
    Cuota findByNombrePaciente(String nombrePaciente);
}
