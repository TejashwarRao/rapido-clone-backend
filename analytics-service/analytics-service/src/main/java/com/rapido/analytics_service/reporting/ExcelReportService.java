package com.rapido.analytics_service.reporting;

import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class ExcelReportService {

    private final FactRideRepository factRideRepository;

    public byte[] generateExcel() {

        try {

            Workbook workbook =
                    new XSSFWorkbook();

            Sheet sheet =
                    workbook.createSheet(
                            "Analytics"
                    );

            Row header =
                    sheet.createRow(0);

            header.createCell(0)
                    .setCellValue("Metric");

            header.createCell(1)
                    .setCellValue("Value");

            Row rides =
                    sheet.createRow(1);

            rides.createCell(0)
                    .setCellValue("Total Rides");

            rides.createCell(1)
                    .setCellValue(
                            factRideRepository.count()
                    );

            Row revenue =
                    sheet.createRow(2);

            revenue.createCell(0)
                    .setCellValue("Revenue");

            revenue.createCell(1)
                    .setCellValue(
                            factRideRepository.getTotalRevenue()
                    );

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            workbook.write(output);

            workbook.close();

            return output.toByteArray();

        } catch (Exception ex) {

            throw new RuntimeException(ex);
        }
    }
}