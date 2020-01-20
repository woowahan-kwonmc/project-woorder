package com.bamin.woorder.order.application;

import com.bamin.woorder.order.domain.Order;
import com.bamin.woorder.order.domain.OrderRepository;
import com.bamin.woorder.order.domain.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final String NO_STATUS_CONDITION = "ALL";
    private static final Long NO_ORDERED_BY_CONDITION = 0L;

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    List<Order> createOrders(final List<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    Order findByOrderNo(final Long orderNo) {
        return orderRepository.findById(orderNo)
                .orElseThrow(() -> new NotFoundOrderException(orderNo));
    }

    List<Order> findConditionalOrders(final int num, final int page, final String status, final Long orderedBy) {
        if (NO_STATUS_CONDITION.equalsIgnoreCase(status) && NO_ORDERED_BY_CONDITION.equals(orderedBy)) {
            return getPageableOrders(num, page);
        }
        if (NO_STATUS_CONDITION.equalsIgnoreCase(status)) {
            return getPageableOrdersByMember(num, page, orderedBy);
        }
        if (NO_ORDERED_BY_CONDITION.equals(orderedBy)) {
            return getPageableOrdersByStatus(num, page, status);
        }
        return getPageableOrdersByMemberAndStatus(num, page, status, orderedBy);
    }

    private List<Order> getPageableOrders(final int num, final int page) {
        return mapToOrders(orderRepository.findAll(PageRequest.of(page - 1, num)));
    }

    private List<Order> getPageableOrdersByMember(final int num,
                                                  final int page,
                                                  final Long orderedBy) {
        return mapToOrders(orderRepository.findAllByOrderMemberMemberMemberNo(orderedBy, PageRequest.of(page - 1, num)));
    }

    private List<Order> getPageableOrdersByStatus(final int num,
                                                  final int page,
                                                  final String status) {
        return mapToOrders(orderRepository.findAllByOrderInfoOrderProcessStatusOrderStatus(OrderStatus.of(status),
                PageRequest.of(page - 1, num)));
    }

    private List<Order> getPageableOrdersByMemberAndStatus(final int num,
                                                           final int page,
                                                           final String status,
                                                           final Long orderedBy) {
        return mapToOrders(orderRepository.findAllByOrderMemberMemberMemberNoAndOrderInfoOrderProcessStatusOrderStatus(orderedBy,
                OrderStatus.of(status), PageRequest.of(page - 1, num)));
    }

    private List<Order> mapToOrders(final Page<Order> pages) {
        return pages.stream()
                .collect(Collectors.toList());
    }
}
