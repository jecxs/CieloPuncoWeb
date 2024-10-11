package com.crud.juniorcrud.controller;


import com.crud.juniorcrud.entity.Ruta;
import com.crud.juniorcrud.service.IRutaService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.util.List;
import java.util.List;

@Controller
@RequestMapping("/rutas")
public class RutaController {

    @Autowired
    private IRutaService rutaService;

    @GetMapping("/new")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("ruta", new Ruta());
        return "registro-ruta";
    }

    @PostMapping("/new")
    public String crearRuta(@ModelAttribute Ruta ruta) {
        rutaService.guardarRuta(ruta);
        return "redirect:/rutas/listar";
    }

    @GetMapping("/{id}")
    public Ruta obtenerRuta(@PathVariable Long id) {
        return rutaService.obtenerRutaPorId(id);
    }

    @GetMapping("/listar")
    public String listarRutas(Model model) {
        model.addAttribute("rutas", rutaService.listarRutas());
        return "listar-rutas";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Long id, Model model) {
        Ruta ruta = rutaService.obtenerRutaPorId(id);
        model.addAttribute("ruta", ruta);
        return "registro-ruta";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarRuta(@PathVariable Long id, @ModelAttribute Ruta ruta) {
        rutaService.actualizarRuta(id, ruta);
        return "redirect:/rutas/listar";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
        return "redirect:/rutas/listar";
    }
    @GetMapping("/download/pdf")
    public void downloadPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=rutas.pdf");

        List<Ruta> rutas = rutaService.listarRutas();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Listado de Rutas"));
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        String[] headers = {"ID", "Nombre", "Origen", "Destino", "Duración", "Distancia", "Precio"};
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Paragraph(header));
            table.addCell(headerCell);
        }

        for (Ruta ruta : rutas) {
            table.addCell(String.valueOf(ruta.getId()));
            table.addCell(ruta.getNombre());
            table.addCell(ruta.getOrigen());
            table.addCell(ruta.getDestino());
            table.addCell(ruta.getDuracion());
            table.addCell(String.valueOf(ruta.getDistancia()));
            table.addCell(String.valueOf(ruta.getPrecio()));
        }

        document.add(table);
        document.close();
    }
}