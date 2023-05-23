package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private String celular;
    private String correo;
    private double estatura;
    private double peso;
    private String cedula;
    private boolean estado;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pago> pago;

    @OneToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "id_membresia")
    private Membresia membresia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ControlIngreso> controlIngreso;
}
