package com.example.ProyectoGym.Repository;

import com.example.ProyectoGym.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {
   /* @Query("SELECT id_rol FROM usuario")
    List<Usuario> findAll();*/
}
