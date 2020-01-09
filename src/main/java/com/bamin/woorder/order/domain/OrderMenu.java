package com.bamin.woorder.order.domain;

import com.bamin.woorder.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderMenu {

    @OneToOne
    @JoinColumn(name = "\"order_menu_no\"")
    private Menu menu;

    public OrderMenu(final Menu menu) {
        this.menu = menu;
    }
}
