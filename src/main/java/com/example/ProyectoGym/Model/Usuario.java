package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private String celular;
    private String correo;
    private double peso;
    private String cedula;

    @OneToOne
    @JoinColumn(name = "id_membresia")
    private Membresia membresia;

    /*@OneToMany
    @JoinColumn(name = "id_pago")
    private Pago pago;*/

    @OneToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    private boolean estado;
}
