package com.Clinica.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String EPS;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cuota> cuotas;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Citas> citas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }
    
    public List<Citas> getCitas() {
        return citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }

    public Paciente(){
        super();
    }

        public Paciente(String nombreCompleto, int edad, String cedula, String telefono, String EPS) {
        super(nombreCompleto, edad, cedula, telefono);
        this.EPS = EPS;
    }

  
    public String getEPS() {
        return EPS;
    }

    public void setEPS(String EPS) {
        this.EPS = EPS;
    }

 
  @Override
    public void mostrarInformacion() {
    System.out.println("====== DATOS DEL PACIENTE ======");
    System.out.println("Nombre: " + getNombreCompleto());
    System.out.println("Cédula: " + getcedula());
    System.out.println("Edad: " + getEdad());
    System.out.println("Teléfono: " + getTelefono());
    System.out.println("EPS: " + this.EPS);
    System.out.println("================================");
}

}
