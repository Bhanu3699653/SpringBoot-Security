package com.pack.SpringBootSecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.SpringBootSecurity.model.Product;
import com.pack.SpringBootSecurity.repository.ProductRepository;



@Service
public class ProductServiceImpl  implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> fetchAllProduct() {
		List<Product> list=(List<Product>) productRepository.findAll();
		return list;
	}

	@Override
	public Product getProductById(Integer id) {
		Optional<Product> product=productRepository.findById(id);
		Product prod = product.get();
		return prod;
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}

}
