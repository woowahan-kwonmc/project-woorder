package com.bamin.woorder.menu.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuCreateRequestDto implements MenuRequestDto {

    private String name;
    private String price;

    public MenuCreateRequestDto(final String name, final String price) {
        this.name = name;
        this.price = price;
    }
}
