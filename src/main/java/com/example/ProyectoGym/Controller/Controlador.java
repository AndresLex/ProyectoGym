package com.example.ProyectoGym.Controller;

import com.example.ProyectoGym.InterfaceService.*;
import com.example.ProyectoGym.Model.ControlIngreso;
import com.example.ProyectoGym.Model.Pago;
import com.example.ProyectoGym.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.time.LocalDate;
import java.util.List;

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

    //Variables Control acceso
    private  Date fechaActual;
    private  Date horaActual;
    private Date horafin;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Inicio de sesion");
        resetValues();
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

    //Vista template de pago
    @GetMapping("/registroPago")
    public String registroPago(Model model){
        model.addAttribute("title", "Registro de Pago ");
        model.addAttribute("usuario", this.userActual);
        model.addAttribute("pago", new Pago());
        return "registroPago";
    }

    //Vista template de Ingresos
    @GetMapping("/reporteAcceso")
    public String reporteAcceso(Model model){
        model.addAttribute("title", "Reporte de Ingresos");
        model.addAttribute("datosIngresos", servCont.listar());
        return "ingresos";
    }

    @GetMapping("/eliminarIngreso/{id}")
    public String eliminarIngreso(@PathVariable("id") int id){
        servCont.eliminar(id);
        return "redirect:/reporteAcceso";
    }

    //Vista template de RegistroPagos
    @GetMapping("/reportePago")
    public String reportePago(Model model){
        model.addAttribute("title", "Reporte de Pagos");
        model.addAttribute("datosPagos", servPag.listar());
        return "pagos";
    }

    @GetMapping("/eliminarPago/{id}")
    public String eliminarPago(@PathVariable("id") int id){
        servPag.eliminar(id);
        return "redirect:/reportePago";
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
        resetValues();
        return "redirect:/inicio";
    }


    //Control de Acceso vista 1
    @GetMapping("/controlAcceso")
    public String controlIngreso(Model model){
        model.addAttribute("title", "Contro de Ingreso y Salida");
        model.addAttribute("fecha", obtenerFechaLD());
        model.addAttribute("hora", obtenerHoraLT());
        return "controlAcceso";
    }


    //Captura de Fecha actual y horas de ingreso y salida del Usuario
    @PostMapping("/accesoUsuario")
    public String accesoUsuario( Model model) {
        ControlIngreso nuevoAcceso = new ControlIngreso(obtenerFechaLD(), obtenerHoraLT(), this.userActual);
        List<ControlIngreso> listaAccesos = servCont.buscarPorUsuario(this.userActual);
        if (listaAccesos.isEmpty()){
            servCont.guardar(nuevoAcceso);
            model.addAttribute("mensaje", "Bienvenid@ "+this.userActual.getNombre()+" !!");
            resetValues();
            return "login";
        }else {
            for (ControlIngreso accesos: listaAccesos) {
                if (nuevoAcceso.getFecha().isEqual(accesos.getFecha()) && accesos.getHoraSalida() == null){
                    model.addAttribute("mensaje", this.userActual.getNombre()+" se encuentra en el gimnasio !!");
                    resetValues();
                    return "login";
                } else if (nuevoAcceso.getUsuario().getId_usuario() == accesos.getUsuario().getId_usuario()
                        && nuevoAcceso.getFecha().isEqual(accesos.getFecha())
                        && accesos.getHoraSalida() != null) {
                    model.addAttribute("mensaje", "El Usuario "+this.userActual.getNombre()+" ya estuvo en el gimnasio el dia "+accesos.getFecha()+" Vuelve mañana !!");
                    resetValues();
                    return "login";
                } else {
                    servCont.guardar(nuevoAcceso);
                    model.addAttribute("mensaje", this.userActual.getNombre()+" Ingreso, Bienvenid@ !!");
                    resetValues();
                    return "login";
                }
            }
        }
        model.addAttribute("mensaje", "No Paso Nada !!");
        resetValues();
        return "login";
    }

    @PostMapping("/salidaUsuario")
    public String salidaUsuario( Model model) {
        List<ControlIngreso> listaAccesosUser = servCont.buscarPorUsuario(this.userActual);
        for (ControlIngreso accesos: listaAccesosUser) {
            if (accesos.getFecha().isEqual(obtenerFechaLD()) && accesos.getHoraSalida() == null){
                accesos.setHoraSalida(obtenerHoraLT());
                servCont.guardar(accesos);
                model.addAttribute("mensaje", this.userActual.getNombre()+" Salio !!");
                resetValues();
                return "login";
            }else if (accesos.getFecha().isEqual(obtenerFechaLD()) && accesos.getHoraSalida() != null){
                model.addAttribute("mensaje", this.userActual.getNombre()+" ya marco la salida");
                resetValues();
                return "login";
            }
        }
        resetValues();
        return "login";
    }


    @PostMapping("/validarUsu")
    public String validarUsu(@RequestParam("cedula") String cedula, Model model){
        Usuario user = servUsu.buscarCedula(cedula);
        if (user != null) {
            int  id_rol = user.getRol().getId_rol();
            if (id_rol == 111) {
                return "index";
            } else if (id_rol == 112) {
                this.userActual = user;
                if (user.isEstado() == true) {
                    model.addAttribute("usuario", user);
                    model.addAttribute("fecha", obtenerFechaLD());
                    model.addAttribute("hora", obtenerHoraLT());
                    return "controlAcceso";
                }else {
                    model.addAttribute("mensaje", this.userActual.getNombre()+" no ha realizado el Pago !!");
                    resetValues();
                    return "login";
                }
            }
        } else {
            model.addAttribute("mensaje", "Cedula no encontrada. Por favor, vuelva a digitarla.");
            resetValues();
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

    public String ObtenerFecha(){
        //Fecha Actual
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = new Date();
        String fechaFormateada = formatoFecha.format(fechaActual);
        return fechaFormateada;
    }
    public String ObtenerHora(){
        //Hora Actual
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        Date horaActual = new Date();
        String horaFormateada = formatoHora.format(horaActual);
        return horaFormateada;
    }

    public LocalDate obtenerFechaLD(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFormateada = LocalDate.parse(LocalDate.now().format(formato));
        return fechaFormateada;
    }

    public LocalTime obtenerHoraLT(){
        DateTimeFormatter foramato = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime horaFormateada = LocalTime.parse(LocalTime.now().format(foramato));
        return horaFormateada;
    }

    public void resetValues(){
        this.userActual = null;
        this.periodoTiempo = 0;
        this.fechaInicio = LocalDate.ofEpochDay(0);
        this.fechaFin = LocalDate.ofEpochDay(0);
        this.precioTotal = 0;
    }
}
