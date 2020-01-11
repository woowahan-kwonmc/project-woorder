package com.bamin.woorder.menu.domain;


import com.bamin.woorder.common.domain.DeletableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "menuNo", callSuper = false)
@Table(name = "MENU")
@Entity
public class Menu extends DeletableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_no")
    private Long menuNo;

    @Embedded
    private MenuName menuName;

    @Embedded
    private MenuPrice menuPrice;

    public Menu(final String menuName, final String menuPrice) {
        this.menuName = new MenuName(menuName);
        this.menuPrice = new MenuPrice(menuPrice);
    }

    public Long getNo() {
        return menuNo;
    }

    public String getName() {
        return menuName.getMenuName();
    }

    public int getPrice() {
        return menuPrice.getMenuPrice();
    }
}
