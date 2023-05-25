package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "membresia")
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_membresia;

    private String tipo;

    private double valor;

    /*@OneToOne(mappedBy = "membresia")
    private Usuario usuario;*/
}
