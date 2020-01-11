package com.bamin.woorder.menu.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuUpdateData {

    private String updateName;
    private String updatePrice;

    public MenuUpdateData(final String updateName, final String updatePrice) {
        this.updateName = updateName;
        this.updatePrice = updatePrice;
    }
}
