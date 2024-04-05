package com.kdu.assesment.controller;

import com.kdu.assesment.entity.Product;
import com.kdu.assesment.entity.ShoppingCart;
import com.kdu.assesment.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping-cart")
public class ShoppingCartController {

    final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ShoppingCart cart){
        shoppingCartService.addCart(cart);
        return ResponseEntity.ok("cart created by " + cart.getCreatedBy());
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getAll(){
        return ResponseEntity.ok(shoppingCartService.getAll());
    }

}
