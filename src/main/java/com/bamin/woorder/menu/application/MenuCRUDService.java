package com.bamin.woorder.menu.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.menu.domain.Menu;
import com.bamin.woorder.menu.dto.MenuRequestDto;
import com.bamin.woorder.menu.dto.MenuResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuCRUDService {

    private final MenuService menuService;

    @Autowired
    public MenuCRUDService(final MenuService menuService) {
        this.menuService = menuService;
    }

    public ResponseDto createMenu(MenuRequestDto requestDto) {
        Menu savedMenu = menuService.createMenu(requestDto.getName(), requestDto.getPrice());
        return ResponseDto.builder()
                .path("/menus")
                .method("POST")
                .message("메뉴 등록 성공")
                .data(ResponseData.builder()
                        .insert("menu", new MenuResponseDto(savedMenu.getNo(), savedMenu.getName(), savedMenu.getPrice()))
                        .build())
                .statusCode("200")
                .build();
    }
}
