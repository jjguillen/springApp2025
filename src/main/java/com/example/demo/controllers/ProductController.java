package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.respositories.CategoryRepository;
import com.example.demo.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping()
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "products/show";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {

        productRepository.deleteById(id);

        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/form";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/form";
    }

    @PostMapping
    public String create(@ModelAttribute Product product) {

        if (product.getId() != null) {
            //Update
            productRepository.findById(product.getId()).ifPresent(p -> {
                p.setTitle(product.getTitle());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());
                p.setCategory(product.getCategory());
                productRepository.save(p);
            });
        } else {
            //Create
            productRepository.save(product);
        }

        return "redirect:/products";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        List<Product> products = productRepository.findProductsByTitleContaining(keyword);
        model.addAttribute("products", products);
        return "products/index";
    }

}
