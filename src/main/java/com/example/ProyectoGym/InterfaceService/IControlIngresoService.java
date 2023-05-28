package com.example.ProyectoGym.InterfaceService;

import com.example.ProyectoGym.Model.ControlIngreso;
import com.example.ProyectoGym.Model.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IControlIngresoService {
    public List<ControlIngreso> listar();

    public Optional<ControlIngreso> editar(int id);

    public void guardar(ControlIngreso conIng);

    public void eliminar(int id);

    public List<ControlIngreso> buscarPorUsuario(Usuario user);

    /*public void actualizar(int id, LocalTime horaSalida);

    public List<ControlIngreso> listarPendiente(int id, LocalDate fecha);*/
}
