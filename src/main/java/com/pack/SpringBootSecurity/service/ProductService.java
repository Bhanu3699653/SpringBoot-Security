package com.pack.SpringBootSecurity.service;

import java.util.List;

import com.pack.SpringBootSecurity.model.Product;



public interface ProductService {
      Product saveProduct(Product product);
      List<Product> fetchAllProduct();
      Product getProductById(Integer id);
      Product updateProduct(Product product);
      void deleteProduct(Integer id);
}
