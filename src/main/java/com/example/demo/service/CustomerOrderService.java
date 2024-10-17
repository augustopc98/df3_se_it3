package com.example.demo.service;

import com.example.demo.model.Discount;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderService {

    CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items);

    void addOrderItem(Long orderId, OrderItem item);

    void removeOrderItem(Long orderId, OrderItem item);

    BigDecimal getTotalAmount(Long orderId);

    void applyDiscount(Long orderId, Discount discount);

    void processPayment(Long orderId, Payment payment);

    void updateDeliveryStatus(Long orderId, String status);

    CustomerOrder getOrderDetails(Long orderId);
}
