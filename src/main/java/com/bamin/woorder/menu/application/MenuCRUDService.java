package com.bamin.woorder.menu.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoMethod;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.menu.domain.Menu;
import com.bamin.woorder.menu.dto.MenuRequestDto;
import com.bamin.woorder.menu.dto.MenuResponseDto;
import com.bamin.woorder.menu.dto.MenuUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .method(ResponseDtoMethod.POST)
                .message("메뉴 등록 성공")
                .data(ResponseData.builder()
                        .insert("menu", mapMenuResponseDto(savedMenu))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    public ResponseDto selectPageMenus(final int page, final int num) {
        List<Menu> menus = menuService.selectPageMenus(page, num);
        List<MenuResponseDto> pageMenus = menus.stream()
                .map(this::mapMenuResponseDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .path(String.format("/menus?page=%d&num=%d", page, num))
                .method(ResponseDtoMethod.GET)
                .message(String.format("%d 페이지의 메뉴 목록 %d개", page, num))
                .data(ResponseData.builder()
                        .insert("menus", pageMenus).build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    public ResponseDto selectMenu(final Long menuNo) {
        Menu findMenu = menuService.findMenuById(menuNo);
        return ResponseDto.builder()
                .path(String.format("/menus/%d", menuNo))
                .method(ResponseDtoMethod.GET)
                .message("메뉴 조회 성공")
                .data(ResponseData.builder()
                        .insert("menu", mapMenuResponseDto(findMenu))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();

    }

    public ResponseDto updateMenu(final MenuUpdateRequestDto requestDto) {
        Menu updatedMenu = menuService.updateMenu(requestDto.getMenuNo(), requestDto.getMenuUpdateData());
        return ResponseDto.builder()
                .path("/menus")
                .method(ResponseDtoMethod.PUT)
                .message("메뉴 업데이트 성공")
                .data(ResponseData.builder()
                        .insert("updatedMenu", mapMenuResponseDto(updatedMenu))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    private MenuResponseDto mapMenuResponseDto(final Menu menu) {
        return new MenuResponseDto(menu.getNo(), menu.getName(), menu.getPrice());
    }
}
