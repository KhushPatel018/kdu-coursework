package com.kdu.assesment.controller;

import com.kdu.assesment.entity.Product;
import com.kdu.assesment.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.ok("Product added with id " + product.getId());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getProductByCart(@PathVariable Long cartId){
        return ResponseEntity.ok(productService.getAllByCart(cartId));
    }

}
