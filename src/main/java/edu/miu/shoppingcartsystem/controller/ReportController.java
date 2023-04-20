package edu.miu.shoppingcartsystem.controller;

import edu.miu.shoppingcartsystem.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {
        return reportService.downloadInvoice();

    }
}
