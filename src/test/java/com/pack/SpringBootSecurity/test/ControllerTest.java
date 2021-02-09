package com.pack.SpringBootSecurity.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pack.SpringBootSecurity.controller.UserController;
import com.pack.SpringBootSecurity.model.Product;
import com.pack.SpringBootSecurity.service.ProductService;
import com.pack.SpringBootSecurity.service.UserService;
import com.pack.SpringBootSecurity.validator.UserValidator;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserController.class,secure=false)
//@SpringBootTest
//@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;	//test the controller class
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserValidator userValidator;
	
	@Test
	public void testLogin() throws Exception{
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}
	
	@Test
	public void testAddProduct() throws Exception{
		mockMvc.perform(post("/save")
				.param("id", "1")
				.param("name","Heater")
				.param("brand", "samsung")
				.param("madein", "India")
				.param("price", "20000.0"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/welcome"));
		
		ArgumentCaptor<Product> argCap = ArgumentCaptor.forClass(Product.class);
		Mockito.verify(productService).saveProduct(argCap.capture());
		assertEquals("Heater",argCap.getValue().getName());	
	}
	
//	@Test
//	public void testEditProduct() throws Exception{
////		mockMvc.perform(post("/edit"))
////		.andExpect(status().is3xxRedirection())
////		.andExpect(view().name("redirect:/welcome"));
//		Product product = new Product(30,"AC","LG","Japan",40000.0);
//		Mockito.when(productService.getProductById(Mockito.anyInt())).thenReturn(product);
//		ArgumentCaptor<Product> argCap = ArgumentCaptor.forClass(Product.class);
//		product.setBrand("Onida");
//		productService.updateProduct(product);
//		Mockito.verify(productService).updateProduct(argCap.capture());
//		assertEquals("Onida",argCap.getValue().getBrand());
//	}
	
	@Test
	public void testDeleteProductById() throws Exception{
		Integer id =1;
		mockMvc.perform(get("/deleteproduct/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/welcome"));
		verify(productService, times(1)).deleteProduct(id);
	}
	
	@Test
	public void testAccessDenied() throws Exception{
		mockMvc.perform(get("/403"))
		.andExpect(status().isOk())
		.andExpect(view().name("403"));	
	}
	
	@Test
	public void testCreateproduct() throws Exception{
		mockMvc.perform(get("/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("new_product"))
		.andExpect(model().size(1))
		.andExpect(model().attributeExists("productForm"));
	}
	
	@Test
	public void testFetchProduct() throws Exception{
		Product product = new Product(32,"Laptop","HCL","US",40000.0);
		Mockito.when(productService.getProductById(Mockito.anyInt())).thenReturn(product);
		mockMvc.perform(get("/editproduct/32"))
		.andExpect(status().isOk())
		.andExpect(view().name("edit_product"))
		.andExpect(model().size(1))
		.andExpect(model().attributeExists("editProduct"));
	}
	
	@Test
	public void testRegistration() throws Exception{
		mockMvc.perform(get("/registration"))
		.andExpect(status().isOk())
		.andExpect(view().name("registration"))
		.andExpect(model().attributeExists("userForm"));
	}
	
	@Test
	public void testWelcome() throws Exception{
		List<Product> plist = new ArrayList<>();
		Product product1 = new Product(40,"Laptop","HCL","US",40000.0);
		Product product2 = new Product(41,"Laptop","HCL","US",40000.0);
		plist.add(product1);
		plist.add(product2);
		Mockito.when(productService.fetchAllProduct()).thenReturn(plist);
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("welcome"))
		.andExpect(model().attributeExists("plist"));
	}
	
	@Test
	public void testUpdateProduct() throws Exception{
		Product product = new Product(33,"Laptop","HCL","US",40000.0);
		Mockito.when(productService.updateProduct(product)).thenReturn(product);
		mockMvc.perform(post("/edit"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/welcome"));
	}
	
}
