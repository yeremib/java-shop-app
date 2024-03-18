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

}
