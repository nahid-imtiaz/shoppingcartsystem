package edu.miu.shoppingcartsystem.controller;

import edu.miu.shoppingcartsystem.model.Product;
import edu.miu.shoppingcartsystem.payload.response.MessageResponse;
import edu.miu.shoppingcartsystem.payload.response.ProductResponse;
import edu.miu.shoppingcartsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponse> fr= new ArrayList<ProductResponse>();
        for(Product p: products){
            fr.add(new ProductResponse(p));
        }
        return ResponseEntity.ok(fr);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            ProductResponse productResponse = new ProductResponse(product.get());
            return ResponseEntity.ok(productResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('VENDOR') or hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        if (savedProduct == null){
            ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Product Couldn't be created !!"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(savedProduct));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR') or hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            Product updatedProduct = productService.updateProduct(product);
            return ResponseEntity.ok(new ProductResponse(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        Optional<Product> product1 = productService.getProductById(id);
        if (product1.isPresent()) {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/toggleActivation/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean toggleActivation(@PathVariable Long id){
        Optional<Product> product1 = productService.getProductById(id);
        if (product1.isPresent()) {
            if(product1.get().getActive()==true){
                product1.get().setActive(false);
                productService.saveProduct(product1.get());
                return false;
            }else{
                product1.get().setActive(true);
                productService.saveProduct(product1.get());
                return true;
            }
        } else {
            return false;
        }

    }



}

