package com.example.ProyectoGym.Service;

import com.example.ProyectoGym.InterfaceService.IMembresiaService;
import com.example.ProyectoGym.Model.Membresia;
import com.example.ProyectoGym.Repository.IMembresia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaService implements IMembresiaService {
    @Autowired
    private IMembresia data;

    @Override
    public List<Membresia> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Membresia> editar(int id) {
        return data.findById(id);
    }

    @Override
    public void guardar(Membresia memb) {
        data.save(memb);
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
