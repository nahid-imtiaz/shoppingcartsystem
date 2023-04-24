package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.payload.request.CategorySaleReportDTO;
import edu.miu.shoppingcartsystem.payload.request.ProductDTO;
import edu.miu.shoppingcartsystem.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity<byte[]> downloadProductReport() throws JRException, IOException {

        List<ProductDTO> products=productRepository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(products);
        Map<String, Object> parameters = new HashMap<>();

        File file = ResourceUtils.getFile("classpath:productReport.jrxml");
        JasperReport compileReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=productReport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

    public ResponseEntity<byte[]> downloadCategoryWiseReport() throws JRException, IOException {

        List<CategorySaleReportDTO> categorySaleReport=productRepository.getCategorySaleReport();
        JRBeanCollectionDataSource beanCollectionSaleReport = new JRBeanCollectionDataSource(categorySaleReport);
        Map<String, Object> parameters = new HashMap<>();

        File file = ResourceUtils.getFile("classpath:csreport.jrxml");
        JasperReport compileSaleReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint1 = JasperFillManager.fillReport(compileSaleReport, parameters, beanCollectionSaleReport);
        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cs-report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
