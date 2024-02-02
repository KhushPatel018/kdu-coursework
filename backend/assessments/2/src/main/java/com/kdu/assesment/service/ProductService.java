package com.kdu.assesment.service;

import com.kdu.assesment.entity.Address;
import com.kdu.assesment.entity.Product;
import com.kdu.assesment.repository.AddressRepository;
import com.kdu.assesment.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllByCart(Long cartId) {
        return productRepository.findAllByCartId(cartId);
    }
}
