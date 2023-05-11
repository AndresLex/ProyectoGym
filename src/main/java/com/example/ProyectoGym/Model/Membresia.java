package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "membresia")
public class Membresia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_membresia;

    private String tipo;

    @OneToMany(mappedBy = "membresia")
    private List<Pago> pago;

    private double valor;
}
