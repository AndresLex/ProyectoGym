package com.example.ProyectoGym.InterfaceService;

import com.example.ProyectoGym.Model.Membresia;

import java.util.List;
import java.util.Optional;

public interface IMembresiaService {
    public List<Membresia> listar();

    public Optional<Membresia> editar(int id);

    public void guardar(Membresia memb);

    public void eliminar(int id);
}
