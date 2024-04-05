package com.kdu.assesment.service;

import com.kdu.assesment.entity.Order;
import com.kdu.assesment.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
