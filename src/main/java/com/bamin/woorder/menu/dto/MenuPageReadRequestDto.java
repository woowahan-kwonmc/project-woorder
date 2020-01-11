package com.bamin.woorder.menu.dto;

import com.bamin.woorder.common.dto.PageReadRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuPageReadRequestDto implements PageReadRequestDto {

    private int page;
    private int num;

    public MenuPageReadRequestDto(final int page, final int num) {
        this.page = page;
        this.num = num;
    }
}
