package com.bamin.woorder.common.presentation;

import com.bamin.woorder.common.dto.PageReadRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SelectPageReadRequestDto implements PageReadRequestDto {

    private int page;
    private int num;

    public SelectPageReadRequestDto(final int page, final int num) {
        this.page = page;
        this.num = num;
    }
}
