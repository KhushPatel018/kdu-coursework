package com.kdu.assesment.controller;

import com.kdu.assesment.entity.Order;
import com.kdu.assesment.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
        return ResponseEntity.ok("Order placed with cart " + order.getCart().getId());
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAll()
    {
       return new ResponseEntity<>( orderService.getAll(), HttpStatus.OK);
    }


}
