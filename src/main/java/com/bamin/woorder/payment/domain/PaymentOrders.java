package com.bamin.woorder.payment.domain;

import com.bamin.woorder.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class PaymentOrders {

    @OneToMany(mappedBy = "orderPayment")
    private List<Order> orders = new ArrayList<>();
}
