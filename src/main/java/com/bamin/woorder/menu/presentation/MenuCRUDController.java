package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.menu.application.MenuCRUDService;
import com.bamin.woorder.menu.dto.MenuCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuCRUDController {

    private final MenuCRUDService menuCRUDService;

    @Autowired
    public MenuCRUDController(final MenuCRUDService menuCRUDService) {
        this.menuCRUDService = menuCRUDService;
    }

    @PostMapping("/menus")
    public ResponseEntity createMenu(@RequestBody final MenuCreateRequestDto requestDto) {
        return ResponseEntity.ok(menuCRUDService.createMenu(requestDto));
    }
}
