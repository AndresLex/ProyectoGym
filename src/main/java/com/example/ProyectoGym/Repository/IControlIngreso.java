package com.example.ProyectoGym.Repository;

import com.example.ProyectoGym.Model.ControlIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IControlIngreso extends JpaRepository<ControlIngreso, Integer> {
}
