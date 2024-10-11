package com.crud.juniorcrud.controller;

import com.crud.juniorcrud.entity.Pasajero; 
import com.crud.juniorcrud.service.IpasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
//



@org.springframework.stereotype.Controller
@RequestMapping()
public class Controller {

    @Autowired
    private IpasajeroService service;
    
    @GetMapping("/buscar")
    public String buscarPasajeros(@RequestParam("keyword") String keyword, Model model) {
        List<Pasajero> pasajeros = service.buscarPasajeros(keyword);
        model.addAttribute("pasajeros", pasajeros);
        return "lista-pasajero"; // Nombre correcto de la plantilla
    }
    @GetMapping("/listar")
    public String listar (Model model){

        List<Pasajero> pasajeros = service.listar();
        model.addAttribute("pasajeros", pasajeros);
        return "listarPasajeros";
    }

    @GetMapping("/new")
    public String agregar(Model model){
        model.addAttribute("pasajeros", new Pasajero());
        return "registrarPasajeros";

    }

    @PostMapping("/save")
    public String save (@Validated Pasajero p, Model model){
        service.save(p);
        return "redirect:/listar";

    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Optional<Pasajero> pasajero = service.listarId(id);
        if (pasajero.isPresent()) {
            model.addAttribute("pasajeros", pasajero.get());
        } else {
            // Maneja el caso cuando la persona no se encuentra
            return "redirect:/listar";
        }
        return "registrarPasajeros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar (@PathVariable int id, Model model){
        service.delete(id);
        return "redirect:/listar";
    }

}

