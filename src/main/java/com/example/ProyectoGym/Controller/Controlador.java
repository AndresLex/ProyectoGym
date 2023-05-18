package com.example.ProyectoGym.Controller;

import com.example.ProyectoGym.InterfaceService.*;
import com.example.ProyectoGym.Model.Rol;
import com.example.ProyectoGym.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class Controlador {
    @Autowired
    private IUsuarioService servUsu;
    @Autowired
    private IRolService servRol;
    @Autowired
    private IPagoService servPag;
    @Autowired
    private IMembresiaService servMem;
    @Autowired
    private IControlIngresoService servCont;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Inicio de sesion");
        model.addAttribute("datosUsu", servUsu.listar());
        return "login";
    }

    @GetMapping("/inicio")
    public String inicio(Model model){
        model.addAttribute("title", "Dashboard Administrador");
        model.addAttribute("datosUsuario", servUsu.listar());
        return "index";
    }

    @GetMapping("/acciones")
    public String acciones(Model model){
        model.addAttribute("title", "Acciones");
        model.addAttribute("datosUsuario", servUsu.listar());
        return "acciones";
    }

    @GetMapping("/registroUsu")
    public String registroUsu(Model model){
        model.addAttribute("title", "Registro de Usuario");
        model.addAttribute("usuario", new Usuario());
        return "registroUsu";
    }

    @GetMapping("/controlIngreso")
    public String controlIngreso(Model model){
        model.addAttribute("title", "Contro de Ingreso y Salida");

        return "controlAcceso";
    }

    @PostMapping("/validarUsu")
    public String validarUsu(@PathVariable("cedula") String cedula){
        /*Rol listaRol = servUsu.listarPorRol(id);*/
        Usuario user = servUsu.buscarCedula(cedula);
        if (user.getRol().getId_rol() == 1) {
            return "index";
        } else if (user.getRol().getId_rol() == 2) {
            return "controlAcceso";
        } else {
            return "login";
        }
    }

    @PostMapping("/guardarUsu")
    public String guardarCli(@ModelAttribute Usuario usu){
        servUsu.guardar(usu);
        return "redirect:/inicio";
    }
}
