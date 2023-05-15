package com.example.ProyectoGym.InterfaceService;

import com.example.ProyectoGym.Model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> listar();

    public Optional<Usuario> editar(int id);

    public void guardar(Usuario usu);

    public void eliminar(int id);
}
