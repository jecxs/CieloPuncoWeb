package com.crud.juniorcrud.service;
import com.crud.juniorcrud.entity.Viaje;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ViajePdfExporter {

    private List<Viaje> viajes;

    public ViajePdfExporter(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Lista de Viajes", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1f, 3f, 3f, 2f, 3f, 2f};
        table.setWidths(columnWidths);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        cell.setPhrase(new Paragraph("ID", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Ruta", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Pasajero", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Vehículo", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Fecha", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Precio Total", headerFont));
        table.addCell(cell);

        for (Viaje viaje : viajes) {
            table.addCell(String.valueOf(viaje.getId()));
            table.addCell(viaje.getRuta().getNombre());
            table.addCell(viaje.getPasajero().getNombre() + " " + viaje.getPasajero().getApellido());
            table.addCell(viaje.getVehiculo().getModelo()); // Ajustado según la clase Viaje
            table.addCell(viaje.getFechaViaje().toString());
            table.addCell(String.valueOf(viaje.getPrecioTotal()));
        }

        document.add(table);

        document.close();
    }
}