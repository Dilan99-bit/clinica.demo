package com.Clinica.demo.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Persona {
     @Column(nullable = false)
     private String nombreCompleto;
    @Column(nullable = false)
    private  int edad;
    @Column(nullable = false, unique = true)
    private String cedula;
    @Column(nullable = false)
    private String telefono;
    
    public Persona() {

    }

    public Persona(String nombreCompleto, int edad, String cedula, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.cedula = cedula;
        this.telefono = telefono;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
     public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 0) {
            this.edad = edad;
        } else {
            System.out.println("Error: La edad no puede ser negativa. Se asignará 0.");
            this.edad = 0;
        }
    }
    public String getcedula() {
        return cedula;
    }
    public void setcedula(String cedula) {
        this.cedula = cedula;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public abstract void mostrarInformacion();
    
    
}
