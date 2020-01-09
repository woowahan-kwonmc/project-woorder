package com.bamin.woorder.menu.domain;


import com.bamin.woorder.common.domain.DeletableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "menuNo", callSuper = false)
@Table(name = "\"MENU\"", schema = "woorder")
@Entity
public class Menu extends DeletableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"menu_no\"")
    private Long menuNo;

    @Embedded
    private MenuName menuName;

    @Embedded
    private MenuPrice menuPrice;

    public Menu(final MenuName menuName, final MenuPrice menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}
