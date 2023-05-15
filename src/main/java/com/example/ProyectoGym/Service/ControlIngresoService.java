package com.example.ProyectoGym.Service;

import com.example.ProyectoGym.InterfaceService.IControlIngresoService;
import com.example.ProyectoGym.Model.ControlIngreso;
import com.example.ProyectoGym.Repository.IControlIngreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControlIngresoService implements IControlIngresoService {
    @Autowired
    private IControlIngreso data;

    @Override
    public List<ControlIngreso> listar() {
        return data.findAll();
    }

    @Override
    public Optional<ControlIngreso> editar(int id) {
        return data.findById(id);
    }

    @Override
    public void guardar(ControlIngreso conIng) {
        data.save(conIng);
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
