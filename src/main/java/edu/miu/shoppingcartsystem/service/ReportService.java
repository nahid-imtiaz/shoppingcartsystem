package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.Product;
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
    public ResponseEntity<byte[]> downloadInvoice1() throws JRException, IOException {

       // List<Product> products = productRepository.findAll();

        List<ProductDTO> products=productRepository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
       //List<ProductDTO> products = productRepository.findProduct();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(products);
        Map<String, Object> parameters = new HashMap<>();

        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport compileReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        //System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

    /*public ResponseEntity<byte[]> downloadInvoice2() throws JRException, IOException {

        // List<Product> products = productRepository.findAll();

        List<ProductDTO> products1=productRepository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
        //List<ProductDTO> products = productRepository.findProduct();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(products1);
        Map<String, Object> parameters = new HashMap<>();

        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport compileReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        //System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }*/

}
