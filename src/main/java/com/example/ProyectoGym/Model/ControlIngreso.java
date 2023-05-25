package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

//import java.sql.Date;
import java.util.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "control_ingreso")
public class ControlIngreso {

    public ControlIngreso() {
    }

    public ControlIngreso(Date fecha, Date horaEntrada, Date horaSalida, Usuario usuario) {
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_registro;

    private Date fecha;

    private Date horaEntrada;

    private Date horaSalida;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;



}
