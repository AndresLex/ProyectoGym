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

    /*@OneToOne(mappedBy = "membresia")
    private Usuario usuario;*/

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pago")
    private Pago pago;*/

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_membresia")
    private List<Pago> pago;*/

    @OneToOne(mappedBy = "membresia")
    private Usuario usuario;
    private double valor;
}
