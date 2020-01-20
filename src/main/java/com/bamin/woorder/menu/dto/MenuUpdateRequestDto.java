package com.bamin.woorder.menu.dto;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuUpdateRequestDto implements MenuRequestDto {

    private Long menuNo;
    private MenuUpdateData menuUpdateData;

    public MenuUpdateRequestDto(final Long menuNo, final MenuUpdateData menuUpdateData) {
        this.menuNo = menuNo;
        this.menuUpdateData = menuUpdateData;
    }

    public Long getMenuNo() {
        return menuNo;
    }

    public MenuUpdateData getMenuUpdateData() {
        return menuUpdateData;
    }

    @Override
    public String getName() {
        return menuUpdateData.getUpdateName();
    }

    @Override
    public String getPrice() {
        return menuUpdateData.getUpdatePrice();
    }
}
