package com.kdu.assesment.service;

import com.kdu.assesment.entity.ShoppingCart;
import com.kdu.assesment.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    final ShoppingCartRepository shoppingCartRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addCart(ShoppingCart cart){
        shoppingCartRepository.save(cart);
    }

    public List<ShoppingCart> getAll()
    {
        return shoppingCartRepository.findAll();
    }
}
