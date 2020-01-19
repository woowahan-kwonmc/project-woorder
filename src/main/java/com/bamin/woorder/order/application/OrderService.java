package com.bamin.woorder.order.application;

import com.bamin.woorder.order.domain.Order;
import com.bamin.woorder.order.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    List<Order> createOrders(final List<Order> orders) {
        return orderRepository.saveAll(orders);
    }
}
