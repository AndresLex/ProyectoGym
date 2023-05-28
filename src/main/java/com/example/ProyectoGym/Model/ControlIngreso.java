package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

//import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "control_ingreso")
public class ControlIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_registro;

    private LocalDate fecha;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public ControlIngreso() {
    }

    public ControlIngreso(LocalDate fecha, LocalTime horaEntrada, Usuario usuario) {
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.usuario = usuario;
    }

    public ControlIngreso(LocalDate fecha, LocalTime horaEntrada, LocalTime horaSalida, Usuario usuario) {
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.usuario = usuario;
    }
}
