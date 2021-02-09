package com.pack.SpringBootSecurity.test;

//import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.SpringBootSecurity.model.Product;
import com.pack.SpringBootSecurity.model.Role;
import com.pack.SpringBootSecurity.model.User;
import com.pack.SpringBootSecurity.repository.ProductRepository;
import com.pack.SpringBootSecurity.repository.RoleRepository;
import com.pack.SpringBootSecurity.repository.UserRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoLayerTest {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
//	@Ignore
	public void testAddProduct() {
		Product  product = new Product(10,"TV","LG","China",20000.0);
		Product savedInDb = productRepo.save(product);
		Optional<Product> data = productRepo.findById(savedInDb.getId());
		Product getFromDb = (Product) data.get();
		assertEquals(savedInDb.getId(),getFromDb.getId());
	}
	
	@Test
//	@Ignore
	public void testBGetAllProducts() {
		Iterable<Product> allProductsFromDb = productRepo.findAll();
		List<Product> productList = new ArrayList<>();
		for(Product product: allProductsFromDb) {
			productList.add(product);
		}
		assertThat(productList.size()).isEqualTo(7);
	}
	
	@Test
//	@Ignore
	public void testDeleteProductById() {
		productRepo.deleteById(10);
		Iterable<Product> allProductsFromDb = productRepo.findAll();
		List<Product> productList = new ArrayList<>();
		for(Product product: allProductsFromDb) {
			productList.add(product);
		}
		assertThat(productList.size()).isEqualTo(6);
//		assertTrue("Id doesn't exists",productRepo.existsById(10));//if the condition fails then it shows the msg
	}
	
	@Test
	public void testUpdateProduct() {
		Product product = new Product(11,"Fridge","LG","India",30000.0);
		Product savedInDb = productRepo.save(product);
		Optional<Product> data = productRepo.findById(savedInDb.getId());
		Product getFromDb = (Product)data.get();
		getFromDb.setPrice(35000.0);
		productRepo.save(getFromDb);
		assertThat(getFromDb.getPrice()).isEqualTo(35000.0);
	}
	
	@Test
	public void testSaveUser() {
		Optional<Role> opt = roleRepo.findById(1l);
		User user = new User();
		user.setId(8l);
		user.setPassword(bCryptPasswordEncoder.encode("abcd"));
		user.setPasswordConfirm("abcd");
		user.setUsername("Jammy");
		user.setRole(opt.get());
		User savedInDb = userRepo.save(user);
		Optional<User> data = userRepo.findById(savedInDb.getId());
		User getFromDb = (User)data.get();
		assertEquals(savedInDb.getId(),getFromDb.getId());
	}
	
	@Test
	public void testUserFinding() {
		User userInDb = userRepo.findByUsername("Sree");
		boolean data = userRepo.existsById(userInDb.getId());
		assertTrue(data);
	}
}
