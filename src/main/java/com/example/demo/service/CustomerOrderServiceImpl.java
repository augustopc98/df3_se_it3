package com.example.demo.service;

import com.example.demo.model.Discount;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Payment;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(customerEmail, customerAddress, new java.util.Date());
        items.forEach(order::addOrderItem);
        return customerOrderRepository.save(order);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = getOrderDetails(orderId);
        order.addOrderItem(item);
        customerOrderRepository.save(order);
    }

    @Override
    public void removeOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = getOrderDetails(orderId);
        order.removeOrderItem(item);
        customerOrderRepository.save(order);
    }

    @Override
    public BigDecimal getTotalAmount(Long orderId) {
        return getOrderDetails(orderId).calculateTotal();
    }

    @Override
    public void applyDiscount(Long orderId, Discount discount) {
        CustomerOrder order = getOrderDetails(orderId);
        order.applyDiscount(discount);
        customerOrderRepository.save(order);
    }

    @Override
    public void processPayment(Long orderId, Payment payment) {
        CustomerOrder order = getOrderDetails(orderId);
        payment.processPayment();
        order.getPayments().add(payment);
        customerOrderRepository.save(order);
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = getOrderDetails(orderId);
        order.updateDeliveryStatus(status);
        customerOrderRepository.save(order);
    }

    @Override
    public CustomerOrder getOrderDetails(Long orderId) {
        return customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("CustomerOrder not found"));
    }
}
