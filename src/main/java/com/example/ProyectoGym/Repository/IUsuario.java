package com.example.ProyectoGym.Repository;

import com.example.ProyectoGym.Model.Rol;
import com.example.ProyectoGym.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {
    /*@Query(value = "SELECT id_rol FROM usuario WHERE id_usuario = :id", nativeQuery = true)
    Rol buscarPorRol(@Param("id") int id);*/

    Usuario findByCedula(String cedula);
}
