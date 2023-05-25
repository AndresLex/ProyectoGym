package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol;

    private String tipoRol;

    /*@OneToOne(mappedBy = "rol")
    private Usuario usuario;*/
}
