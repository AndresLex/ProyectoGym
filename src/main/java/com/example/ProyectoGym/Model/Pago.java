package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precio;
    private int periodoTiempo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /*@ManyToOne(mappedBy = "pago")
    private List<Pago> Pago;*/
    public Pago() {
    }

    public Pago(LocalDate fechaInicio, LocalDate fechaFin, double precio, int periodoTiempo, Usuario usuario) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.periodoTiempo = periodoTiempo;
        this.usuario = usuario;
    }

}
