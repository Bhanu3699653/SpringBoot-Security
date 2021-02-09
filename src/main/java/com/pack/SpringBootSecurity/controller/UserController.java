package com.pack.SpringBootSecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pack.SpringBootSecurity.model.Product;
import com.pack.SpringBootSecurity.model.Role;
import com.pack.SpringBootSecurity.model.User;
import com.pack.SpringBootSecurity.service.ProductService;
import com.pack.SpringBootSecurity.service.UserService;
import com.pack.SpringBootSecurity.validator.UserValidator;




@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private UserValidator userValidator;
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    	userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        //System.out.println(userForm.getUsername()+" "+userForm.getPassword());
        Role role=userForm.getRole();
      //  System.out.println(role);
        userForm.setRole(role);
      //  System.out.println(userForm.getRole().getName()+" "+userForm.getUsername()+" "+userForm.getPassword());
        userService.save(userForm);
        model.addAttribute("message1","User registered successfully");
        return "login";
    }
    
  

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	List<Product> list=productService.fetchAllProduct();
    	model.addAttribute("plist",list);
        return "welcome";
    }
   
   /* @ModelAttribute("roleList")
	public Map<Integer,String> initializeRoles() {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	map.put(1,"ADMIN");
		map.put(2,"USER");
		return map;
	}*/
    
    @RequestMapping(value="/new",method=RequestMethod.GET)
    public String createProduct(Model model) {
    	model.addAttribute("productForm", new Product());
    	return "new_product";
    }
    
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String addProduct(@ModelAttribute("productForm") Product product) {
    	Random rand=new Random();
    	Integer id=rand.nextInt(90000)+10000;
    	product.setId(id);
    	productService.saveProduct(product);
    	return "redirect:/welcome";   	
    }
    
    @RequestMapping(value="/editproduct/{pid}")
    public String fetchProduct(@PathVariable("pid") Integer prod_id,Model model) {
    	Product prod=productService.getProductById(prod_id);
    	model.addAttribute("editProduct", prod);
    	return "edit_product";
    }
    
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public String updateProduct(@ModelAttribute("editProduct") Product product) {
    	System.out.println("update"+product);
    	productService.updateProduct(product);
    	return "redirect:/welcome";  
    }
    
    @RequestMapping(value="/deleteproduct/{pid}")
    public String deleteProduct(@PathVariable("pid") Integer prod_id) {
    	productService.deleteProduct(prod_id);
    	return "redirect:/welcome";  
    }
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }

}