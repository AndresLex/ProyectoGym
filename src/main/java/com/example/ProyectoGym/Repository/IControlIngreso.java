package com.example.ProyectoGym.Repository;

import com.example.ProyectoGym.Model.ControlIngreso;
import com.example.ProyectoGym.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface IControlIngreso extends JpaRepository<ControlIngreso, Integer> {
    List<ControlIngreso> findByUsuario(Usuario user);
/*
    @Modifying
    @Query("UPDATE control_ingreso c SET c.horaSalida = :horaSalida WHERE c.id_usuario = :idUsuario")
    void actualizarHoraSalida(int idUsuario, LocalTime horaSalida);

    @Query("SELECT c FROM control_ingreso c WHERE c.id_usuario = :idUsuario " +
            "AND c.fecha = :fecha " +
            "AND c.horaEntrada > 0 " +
            "AND c.horaSalida = 0")
    List<ControlIngreso> obtenerIngresosPendientes(int idUsuario, LocalDate fecha);*/
}
