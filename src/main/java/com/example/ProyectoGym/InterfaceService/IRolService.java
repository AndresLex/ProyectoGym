package com.example.ProyectoGym.InterfaceService;

import com.example.ProyectoGym.Model.Rol;
import java.util.List;
import java.util.Optional;

public interface IRolService {
    public List<Rol> listar();

    public Optional<Rol> editar(int id);

    public void guardar(Rol rol);

    public void eliminar(int id);
}
