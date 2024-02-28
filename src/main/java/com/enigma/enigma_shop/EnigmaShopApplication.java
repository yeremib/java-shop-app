package com.enigma.enigma_shop;

import com.enigma.enigma_shop.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class EnigmaShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmaShopApplication.class, args);
	}

//	// GET
//	// Cara lama
//	// @RequestMapping(method = RequestMehtod.GET, path = "/hello-world")
//
//	// Cara Baru
//	@GetMapping(path = "/hello-world")
//	public String helloWorld() {
//		return "<h1>Hello World</h1>";
//	}
//
//	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Map<String, Object> getProduct() {
//		return Map.of(
//				"id", "1",
//				"name", "apel",
//				"price", 3000
//		);
//	}
//
//	// RequestParam
//	@GetMapping(path = "/menus")
//	public String getMenuFilter(
//			@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "maxPrice", required = false ) Integer maxPrice
//	 ) {
//		return name + " " + maxPrice;
//	}
//
//	// Path Variable
//	@GetMapping(path = "/menus/{id}")
//	public String getMenuById(@PathVariable String id){// @PathVariable(name = "id") String menuId
//		return "Product" + id;
//	}
//
//	@PostMapping(
//			path = "/products",
//			produces = MediaType.APPLICATION_JSON_VALUE,
//			consumes = MediaType.APPLICATION_JSON_VALUE
//	)
//	public Product createNewProduct(@RequestBody Product product) {
//		return product;
//	}
}
