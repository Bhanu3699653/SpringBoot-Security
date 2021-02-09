package com.pack.SpringBootSecurity.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.SpringBootSecurity.model.Product;
import com.pack.SpringBootSecurity.model.Role;
import com.pack.SpringBootSecurity.model.User;
import com.pack.SpringBootSecurity.repository.ProductRepository;
import com.pack.SpringBootSecurity.repository.RoleRepository;
import com.pack.SpringBootSecurity.repository.UserRepository;
import com.pack.SpringBootSecurity.service.ProductService;
import com.pack.SpringBootSecurity.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceLayerTest {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@MockBean
	private ProductRepository productRepo;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private RoleRepository roleRepo;

	@Test
	public void testCreateProduct() {
		Product product = new Product(12, "Mobile", "Redmi", "Japan", 20000.0);
		Mockito.when(productRepo.save(product)).thenReturn(product);
		assertThat(productService.saveProduct(product)).isEqualTo(product);
	}
	
	@Test
	public void testGetAllProducts() {
		List<Product> productList = null;
		Mockito.when(productRepo.findAll()).thenReturn(productList);
		assertThat(productService.fetchAllProduct()).isEqualTo(productList);
	}
	
	@Test
	public void testDeleteProduct() {
		Product product = new Product();
		Mockito.when(productRepo.findById(11)).thenReturn(Optional.of(product));
		productService.deleteProduct(product.getId());
		assertFalse(productRepo.existsById(product.getId()));
	}
	
	@Test
	public void testUpdateProduct() {
		Product product = new Product(20,"fan","orient","India",2500.0);
		Mockito.when(productRepo.save(product)).thenReturn(product);
		Mockito.when(productRepo.findById(20)).thenReturn(Optional.of(product));
		
		product.setBrand("Usha");
		Mockito.when(productRepo.save(product)).thenReturn(product);
		assertThat(productService.updateProduct(product)).isEqualTo(product);
	}
	
	@Test
	public void testSaveUser() {
		Role role = new Role();
		role.setId(1l);
		role.setName("USER");
		
		User user = new User();
		user.setPassword(bCryptPasswordEncoder.encode("abcd"));
		user.setPasswordConfirm("abcd");
		user.setUsername("Jimmy");
		user.setRole(role);
		
		Mockito.when(userRepo.save(user)).thenReturn(user);
		userService.save(user);
		Mockito.when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
		assertThat(userService.findByUsername(user.getUsername())).isEqualTo(user);
	}
	
//	@Test
//	public void testFindingUser() {
//		User user = new User();
//		Mockito.when(userRepo.findByUsername("Sree")).thenReturn(user);
//		System.out.println(user);
//		assertThat(userService.findByUsername(user.getUsername())).isEqualTo(user);
//	}

}
