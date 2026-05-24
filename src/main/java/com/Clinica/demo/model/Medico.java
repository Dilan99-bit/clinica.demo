package com.Clinica.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicos")
public class Medico extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String especialidad;
    
    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Citas> citas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }
    
    public List<Citas> getCitas() {
        return citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }

 public Medico(){
    super();
    }
    
    public Medico(String nombreCompleto, int edad, String cedula, String telefono, String especialidad) {
        super(nombreCompleto, edad, cedula, telefono);
        this.especialidad = especialidad;
    }

   

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    
  @Override
    public void mostrarInformacion() {
    System.out.println("====== DATOS DE LOS MEDICOS ======");
    System.out.println("Nombre: " + getNombreCompleto());
    System.out.println("Cédula: " + getcedula());
    System.out.println("Edad: " + getEdad());
    System.out.println("Teléfono: " + getTelefono());
    System.out.println("Especialidad: " + this.especialidad);
    System.out.println("================================");
}
}
