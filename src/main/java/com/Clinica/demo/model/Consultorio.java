package com.Clinica.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "consultorios")
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private int numConsultorio;
    @Column(nullable = false)
    private int pisoConsultorio;
    
    @Column(nullable = false)
    private String estado;
    
    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL)
    private List<Medico> medicos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
  
    

    public Consultorio(){

    }

    public Consultorio(int numConsultorio, int pisoConsultorio, String estado) {
        this.numConsultorio = numConsultorio;
        this.pisoConsultorio = pisoConsultorio;
        this.estado = estado;
    }

    public int getNumConsultorio() {
        return numConsultorio;
    }

    public void setNumConsultorio(int numConsultorio) {
             this.numConsultorio = numConsultorio;
        
    }

    public int getPisoConsultorio() {
        return pisoConsultorio;
    }

    public void setPisoConsultorio(int pisoConsultorio) {
        this.pisoConsultorio = pisoConsultorio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    @Override
    public String toString() {
        
        return "Consultorio [Numero = " + numConsultorio + ", Piso =" + pisoConsultorio + ", Estado=" + estado + "]";
        
    }

    
    
}



