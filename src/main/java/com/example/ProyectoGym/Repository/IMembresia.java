package com.example.ProyectoGym.Repository;

import com.example.ProyectoGym.Model.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMembresia extends JpaRepository<Membresia, Integer> {
}
