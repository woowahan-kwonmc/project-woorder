package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.common.dto.PageReadRequestDto;
import com.bamin.woorder.common.presentation.PageRequestParams;
import com.bamin.woorder.menu.application.MenuCRUDService;
import com.bamin.woorder.menu.dto.MenuCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("/menus")
//    public ResponseEntity selectPageMenus(@RequestParam int page, @RequestParam int num) {
//        return ResponseEntity.ok(menuCRUDService.selectPageMenus(page, num));
//    }

    @GetMapping("/menus")
    public ResponseEntity selectPageMenus(@PageRequestParams final PageReadRequestDto requestDto) {
        return ResponseEntity.ok(menuCRUDService.selectPageMenus(requestDto.getPage(), requestDto.getNum()));
    }
}
