package com.example.ProyectoGym.Controller;

import com.example.ProyectoGym.InterfaceService.*;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "login";
    }

}
