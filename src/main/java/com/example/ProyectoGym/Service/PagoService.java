package com.example.ProyectoGym.Service;

import com.example.ProyectoGym.InterfaceService.IPagoService;
import com.example.ProyectoGym.Model.Pago;
import com.example.ProyectoGym.Repository.IPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService implements IPagoService {
    @Autowired
    private IPago data;

    @Override
    public List<Pago> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Pago> editar(int id) {
        return data.findById(id);
    }

    @Override
    public void guardar(Pago pag) {
        data.save(pag);
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
