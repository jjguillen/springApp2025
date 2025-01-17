package com.example.demo.respositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByTitleContaining(String title);
    List<Product> findProductsByPriceGreaterThan(Double price);

}
