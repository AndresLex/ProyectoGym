package com.example.ProyectoGym.Service;

import com.example.ProyectoGym.InterfaceService.IUsuarioService;
import com.example.ProyectoGym.Model.Usuario;
import com.example.ProyectoGym.Repository.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuario data;

    @Override
    public List<Usuario> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Usuario> editar(int id) {
        return data.findById(id);
    }

    @Override
    public void guardar(Usuario usu) {
        data.save(usu);
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
