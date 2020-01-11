package com.bamin.woorder.menu.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuResponseDto {

    private Long menuNo;
    private String menuName;
    private int menuPrice;

    public MenuResponseDto(final Long menuNo, final String menuName, final int menuPrice) {
        this.menuNo = menuNo;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}
