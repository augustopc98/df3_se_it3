package com.example.demo.controller;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Payment;
import com.example.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customerOrders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        return customerOrderService.createOrder(order.getCustomerEmail(), order.getCustomerAddress(), order.getItems());
    }

    @GetMapping("/{orderId}")
    public CustomerOrder getOrder(@PathVariable Long orderId) {
        return customerOrderService.getOrderDetails(orderId);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal getOrderTotal(@PathVariable Long orderId) {
        return customerOrderService.getTotalAmount(orderId);
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        customerOrderService.addOrderItem(orderId, orderItem);
    }

    @DeleteMapping("/{orderId}/items")
    public void removeOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        customerOrderService.removeOrderItem(orderId, orderItem);
    }

    @PutMapping("/{orderId}/status")
    public void updateDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        customerOrderService.updateDeliveryStatus(orderId, status);
    }

    @PostMapping("/{orderId}/payment")
    public void processPayment(@PathVariable Long orderId, @RequestBody Payment payment) {
        customerOrderService.processPayment(orderId, payment);
    }
}
