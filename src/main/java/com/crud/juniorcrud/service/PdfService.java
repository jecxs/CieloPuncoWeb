package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Pasajero;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfService {

    public ByteArrayInputStream generatePdf(List<Pasajero> pasajeros) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc, PageSize.A4);

            // Título del documento
            Paragraph title = new Paragraph("Reporte de Pasajeros")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            // Tabla de pasajeros
            float[] columnWidths = {1, 3, 3, 1, 3, 3};
            Table table = new Table(columnWidths);
            table.setWidth(100);
            table.setMarginTop(20);

            // Encabezados de la tabla
            Stream.of("ID", "Nombre", "Apellido", "Edad", "DNI", "Teléfono").forEach(headerTitle -> {
                Cell header = new Cell();
                header.add(new Paragraph(headerTitle).setFontSize(12));
                header.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                table.addHeaderCell(header);
            });

            // Datos de los pasajeros
            for (Pasajero pasajero : pasajeros) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pasajero.getId())).setFontSize(12)));
                table.addCell(new Cell().add(new Paragraph(pasajero.getNombre()).setFontSize(12)));
                table.addCell(new Cell().add(new Paragraph(pasajero.getApellido()).setFontSize(12)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pasajero.getEdad())).setFontSize(12)));
                table.addCell(new Cell().add(new Paragraph(pasajero.getDni()).setFontSize(12)));
                table.addCell(new Cell().add(new Paragraph(pasajero.getTelefono()).setFontSize(12)));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
