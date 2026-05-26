package com.Clinica.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String areaEncargada; 
    @Column(nullable = false)
    private int nivelAcceso;

     public Administrador() {
        super();
    }

    public Administrador(String nombreCompleto, int edad, String cedula, String telefono, String areaEncargada, int nivelAcceso) {
        super(nombreCompleto, edad, cedula, telefono);
        this.areaEncargada = areaEncargada;
        this.nivelAcceso = nivelAcceso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAreaEncargada() {
        return areaEncargada;
    }

    public void setAreaEncargada(String areaEncargada) {
        this.areaEncargada = areaEncargada;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

   

    @Override
    public void mostrarInformacion() {
        System.out.println("====== DATOS DEL ADMINISTRADOR ======");
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Área: " + this.areaEncargada);
        System.out.println("Nivel de Acceso: " + this.nivelAcceso);
        System.out.println("=====================================");
    }
}

