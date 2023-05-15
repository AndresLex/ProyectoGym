package com.example.ProyectoGym.Service;

import com.example.ProyectoGym.InterfaceService.IRolService;
import com.example.ProyectoGym.Model.Rol;
import com.example.ProyectoGym.Repository.IRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {
    @Autowired
    private IRol data;

    @Override
    public List<Rol> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Rol> editar(int id) {
        return data.findById(id);
    }

    @Override
    public void guardar(Rol rol) {
        data.save(rol);
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
