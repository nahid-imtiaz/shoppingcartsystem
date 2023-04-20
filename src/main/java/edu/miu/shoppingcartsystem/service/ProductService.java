package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.Category;
import edu.miu.shoppingcartsystem.model.Product;
import edu.miu.shoppingcartsystem.model.User;
import edu.miu.shoppingcartsystem.repository.ProductRepository;
import edu.miu.shoppingcartsystem.service.security.LoggedInUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LoggedInUserUtil loggedInUserUtil;
    @Autowired
    private CategoryService categoryService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {

    Optional<User> currentUser = loggedInUserUtil.getCurrentUser();
      if(currentUser.isPresent()){
          product.setUser(currentUser.get());
          return productRepository.save(product);
      }
      else
          return null;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}

