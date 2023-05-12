package com.example.ProyectoGym.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "control_ingreso")
public class ControlIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_registro;

    private Date fecha;

    private Time horaEntrada;

    private Time horaSalida;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
