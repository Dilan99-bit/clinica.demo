package com.Clinica.demo.repository;

import com.Clinica.demo.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    Consultorio findByNumConsultorio(int numConsultorio);
}
