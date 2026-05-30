package com.rapido.admin_service.controller;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    @GetMapping("/revenue/csv")
    public ResponseEntity<String> downloadRevenueCsv() {

        String csv = "Report,Daily Revenue,Weekly Revenue,Monthly Revenue\n"
                + "Revenue Report,15000,85000,350000\n";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=revenue-report.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }

    @GetMapping("/revenue/pdf")
    public ResponseEntity<byte[]> downloadRevenuePdf() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(new Paragraph("Rapido Clone Revenue Report"));
        document.add(new Paragraph("Daily Revenue: 15000"));
        document.add(new Paragraph("Weekly Revenue: 85000"));
        document.add(new Paragraph("Monthly Revenue: 350000"));
        document.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=revenue-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(outputStream.toByteArray());
    }
}
