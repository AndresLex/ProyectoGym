package com.example.ProyectoGym.InterfaceService;

import com.example.ProyectoGym.Model.ControlIngreso;

import java.util.List;
import java.util.Optional;

public interface IControlIngresoService {
    public List<ControlIngreso> listar();

    public Optional<ControlIngreso> editar(int id);

    public void guardar(ControlIngreso conIng);

    public void eliminar(int id);
}
