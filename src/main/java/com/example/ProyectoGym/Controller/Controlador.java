package com.example.ProyectoGym.Controller;

import com.example.ProyectoGym.InterfaceService.*;
import com.example.ProyectoGym.Model.Pago;
import com.example.ProyectoGym.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    private Usuario userActual;
    private int periodoTiempo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;

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
        model.addAttribute("head", "Registro Nuevo Usuario");
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("datosMem", servMem.listar());
        model.addAttribute("datosRol", servRol.listar());
        return "registroUsu";
    }

    //Para renderizar el template de pago
    @GetMapping("/registroPago")
    public String registroPago(Model model){
        model.addAttribute("title", "Registro de Pago ");
        model.addAttribute("usuario", this.userActual);
        model.addAttribute("pago", new Pago());
        return "registroPago";
    }

    @PostMapping("/buscarUsuario")
    public String buscarUsuarioPorCedula(@RequestParam("cedula") String cedula, Model model) {
        model.addAttribute("title", "Registro de Pago ");
        Usuario usuario = servUsu.buscarCedula(cedula);
        this.userActual = usuario;
        model.addAttribute("usuario", usuario);
        model.addAttribute("pago", new Pago());
        return "registroPago"; // Retorna el nombre de la vista para mostrar el resultado de la búsqueda
    }

    @PostMapping("/calcularPrecio")
    public String calcularPrecio(@RequestParam("valor") double valor, @RequestParam("tiempo") int tiempo, Model model) {
        model.addAttribute("title", "Registro de Pago ");
        double valorTotal = valor * tiempo;
        this.precioTotal = valorTotal;
        this.periodoTiempo = tiempo;

        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();
        this.fechaInicio = currentDate;

        // Fecha Futura
        LocalDate futureDate = currentDate.plusMonths(tiempo);
        this.fechaFin = futureDate;
        model.addAttribute("usuario", this.userActual);
        model.addAttribute("precio", valorTotal);
        model.addAttribute("fechaActual", currentDate);
        model.addAttribute("fechaFutura", futureDate);
        model.addAttribute("periodoTiempo", tiempo);
        model.addAttribute("pago", new Pago());
        //this.userActual = null;
        return "registroPago"; //Retorna la vista con el calculo
    }

    @PostMapping("/guardarPago")
    public String guardarPago(@RequestParam("estado") boolean estado){
        Pago pago = new Pago(this.fechaInicio, this.fechaFin, this.precioTotal, this.periodoTiempo, this.userActual);
        servPag.guardar(pago);
        Usuario user = servUsu.buscarCedula(userActual.getCedula());
        user.setEstado(estado);
        servUsu.guardar(user);
        this.userActual = null;
        this.fechaInicio = LocalDate.ofEpochDay(0);
        this.fechaFin = LocalDate.ofEpochDay(0);
        this.precioTotal = 0;
        this.periodoTiempo = 0;
        return "redirect:/inicio";
    }

    @GetMapping("/controlAcceso")
    public String controlIngreso(Model model){
        model.addAttribute("title", "Contro de Ingreso y Salida");
        return "controlAcceso";
    }

    @PostMapping("/validarUsu")
    public String validarUsu(@RequestParam("cedula") String cedula, Model model){
        /*Rol listaRol = servUsu.listarPorRol(id);*/
        Usuario user = servUsu.buscarCedula(cedula);
        if (user != null) {
            int  id_rol = user.getRol().getId_rol();
            if (id_rol == 111) {
                return "index";
            } else if (id_rol == 112) {
                model.addAttribute("usuario", user);
                return "controlAcceso";
            }
        } else {
            model.addAttribute("mensaje", "Cedula no encontrada. Por favor, vuelva a digitarla.");
            return "login";
        }
        return cedula;
    }

    @PostMapping("/guardarUsu")
    public String guardarCli(@ModelAttribute Usuario usu){
        servUsu.guardar(usu);
        return "redirect:/inicio";
    }

    @GetMapping("/editarUsu/{id}")
    public String editarUsu(@PathVariable("id")int id, Model model){
        model.addAttribute("title", "Edicion de Usuario");
        model.addAttribute("head", "Edicion de Usuario");
        model.addAttribute("usuario", servUsu.editar(id));
        model.addAttribute("datosMem", servMem.listar());
        model.addAttribute("datosRol", servRol.listar());
        return "registroUsu";
    }

    @GetMapping("/eliminarUsu/{id}")
    public String eliminarUsuario(@PathVariable("id") int id){
        servUsu.eliminar(id);
        return "redirect:/acciones";
    }
}
