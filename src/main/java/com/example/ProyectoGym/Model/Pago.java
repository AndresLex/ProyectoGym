package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago;

    private Date fechaInicio;
    private Date fechaFin;
    private double precio;
    private int periodoTiempo;

    @ManyToOne
    private List<Membresia> membresia;
    /*@JoinColumn(name = "id_membresia")*/


    /*@ManyToOne(mappedBy = "pago")
    private List<Pago> Pago;*/
}
