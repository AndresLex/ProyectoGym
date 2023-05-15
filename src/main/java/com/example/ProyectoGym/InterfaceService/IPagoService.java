package com.example.ProyectoGym.InterfaceService;

import com.example.ProyectoGym.Model.Pago;
import java.util.List;
import java.util.Optional;

public interface IPagoService {
    public List<Pago> listar();

    public Optional<Pago> editar(int id);

    public void guardar(Pago pag);

    public void eliminar(int id);
}
