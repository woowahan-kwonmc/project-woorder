package com.bamin.woorder.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByOrderMemberMemberMemberNo(final Long memberNo, final Pageable pageable);

    Page<Order> findAllByOrderInfoOrderProcessStatusOrderStatus(final OrderStatus orderStatus, final Pageable pageable);

    Page<Order> findAllByOrderMemberMemberMemberNoAndOrderInfoOrderProcessStatusOrderStatus(final Long memberNo, final OrderStatus orderStatus, final Pageable pageable);
}
