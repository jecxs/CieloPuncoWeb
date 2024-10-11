package com.crud.juniorcrud.controller;


import com.crud.juniorcrud.entity.Viaje;
import com.crud.juniorcrud.service.*;
import com.itextpdf.io.IOException;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private IViajeService viajeService;

    @Autowired
    private IRutaService rutaService;

    @Autowired
    private IpasajeroService pasajeroService;

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping("/listar")
    public String getAllViajes(Model model) {
        List<Viaje> viajes = viajeService.getAllViajes();
        model.addAttribute("viajes", viajes);
        return "viajeslist";
    }

    @GetMapping("/nuevo")
    public String createViajeForm(Model model) {
        model.addAttribute("viaje", new Viaje());
        model.addAttribute("rutas", rutaService.listarRutas());
        model.addAttribute("pasajeros", pasajeroService.listar());
        model.addAttribute("vehiculos", vehiculoService.obtenerTodosLosVehiculos());
        return "viajesform";
    }

    @PostMapping
    public String saveViaje(@ModelAttribute Viaje viaje, Model model) {
        try {
            viajeService.saveViaje(viaje);
            return "redirect:/viajes/listar";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el viaje: " + e.getMessage());
            model.addAttribute("viaje", viaje);
            model.addAttribute("rutas", rutaService.listarRutas());
            model.addAttribute("pasajeros", pasajeroService.listar());
            model.addAttribute("vehiculos", vehiculoService.obtenerTodosLosVehiculos());
            return "viajesform";
        }
    }

    @GetMapping("/editar/{id}")
    public String editViajeForm(@PathVariable Long id, Model model) {
        Viaje viaje = viajeService.getViajeById(id);
        model.addAttribute("viaje", viaje);
        model.addAttribute("rutas", rutaService.listarRutas());
        model.addAttribute("pasajeros", pasajeroService.listar());
        model.addAttribute("vehiculos", vehiculoService.obtenerTodosLosVehiculos());
        return "viajesform";
    }

    @PostMapping("/{id}")
    public String updateViaje(@PathVariable Long id, @ModelAttribute Viaje viaje) {
        viaje.setId(id);
        viajeService.saveViaje(viaje);
        return "redirect:/viajes/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteViaje(@PathVariable Long id) {
        viajeService.deleteViaje(id);
        return "redirect:/viajes/listar";
    }

    @GetMapping("/download-pdf")
    public void descargarViajesPdf(HttpServletResponse response) throws IOException, DocumentException, java.io.IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=lista_viajes_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Viaje> viajes = viajeService.getAllViajes();

        ViajePdfExporter exporter = new ViajePdfExporter(viajes);
        exporter.export(response);
    }
}