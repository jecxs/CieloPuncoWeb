package com.crud.juniorcrud.controller;

import com.crud.juniorcrud.entity.Vehiculo;
import com.crud.juniorcrud.repository.VehiculoRepository;
import com.crud.juniorcrud.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoService.obtenerTodosLosVehiculos());
        return "vehiculoslistar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculosformulario";
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "vehiculosformulario";
        }
        vehiculoService.guardarVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVehiculo(@PathVariable("id") Long id, @ModelAttribute("vehiculo") Vehiculo vehiculo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "vehiculosformulario";
        }
        vehiculo.setId(id);
        vehiculoService.guardarVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Vehiculo> vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        if (vehiculo.isPresent()) {
            model.addAttribute("vehiculo", vehiculo.get());
            return "vehiculosformulario";
        } else {
            return "redirect:/vehiculos";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable("id") Long id) {
        vehiculoService.eliminarVehiculo(id);
        return "redirect:/vehiculos";
    }
}
