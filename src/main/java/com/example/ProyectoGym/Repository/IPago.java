package com.example.ProyectoGym.Repository;

import com.example.ProyectoGym.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPago extends JpaRepository<Pago, Integer> {
}
