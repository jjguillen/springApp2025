package com.example.demo;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.respositories.CategoryRepository;
import com.example.demo.respositories.ProductRepository;
import jakarta.transaction.TransactionScoped;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DemoApplication.class, args);
		var productRepository = context.getBean(ProductRepository.class);
		var categoryRepository = context.getBean(CategoryRepository.class);

		/*
        List<Category> categories = List.of(
				new Category("Electronics"),
				new Category("Books"),
				new Category("Computers")
		);
		categoryRepository.saveAll(categories);

		List<Product> products = List.of(
				new Product("TV", 2000.0, 1, categories.get(0)),
				new Product("Mouse", 50.0, 100, categories.get(0)),
				new Product("Keyboard", 100.0, 50, categories.get(0))
		);
		productRepository.saveAll(products);
		*/


	}

}
