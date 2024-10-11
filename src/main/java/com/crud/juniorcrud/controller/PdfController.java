package com.crud.juniorcrud.controller;

import com.crud.juniorcrud.entity.Pasajero;
import com.crud.juniorcrud.service.IpasajeroService;
import com.crud.juniorcrud.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping
public class PdfController {

    @Autowired
    private IpasajeroService service;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/reporte")
    public ResponseEntity<InputStreamResource> generatePdfReport() {
        List<Pasajero> pasajeros = service.listar();
        ByteArrayInputStream bis = pdfService.generatePdf(pasajeros);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pasajeros_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
