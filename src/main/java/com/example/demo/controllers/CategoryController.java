package com.example.demo.controllers;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.respositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        var categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/form";
    }

    @PostMapping
    public String create(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/products/{id}")
    public String products(Model model, @PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        //Sacar los productos de esa categoría
        List<Product> products = category.getProducts();

        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "categories/products";
    }

}
