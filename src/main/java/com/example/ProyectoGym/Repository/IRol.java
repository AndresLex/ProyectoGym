package com.example.ProyectoGym.Repository;

import com.example.ProyectoGym.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRol extends JpaRepository<Rol, Integer> {

}
