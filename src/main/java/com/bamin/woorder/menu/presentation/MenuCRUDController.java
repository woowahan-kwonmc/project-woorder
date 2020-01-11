package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.common.dto.PageReadRequestDto;
import com.bamin.woorder.common.presentation.PageRequestParams;
import com.bamin.woorder.menu.application.MenuCRUDService;
import com.bamin.woorder.menu.dto.MenuCreateRequestDto;
import com.bamin.woorder.menu.dto.MenuUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
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

    @GetMapping("/menus/{menuNo}")
    public ResponseEntity selectMenu(@PathVariable final Long menuNo) {
        return ResponseEntity.ok(menuCRUDService.selectMenu(menuNo));
    }

    @GetMapping("/menus")
    public ResponseEntity selectPageMenus(@PageRequestParams final PageReadRequestDto requestDto) {
        return ResponseEntity.ok(menuCRUDService.selectPageMenus(requestDto.getPage(), requestDto.getNum()));
    }

    @PutMapping("/menus")
    public ResponseEntity updateMenu(@RequestBody final MenuUpdateRequestDto requestDto) {
        return ResponseEntity.ok(menuCRUDService.updateMenu(requestDto));
    }

    @DeleteMapping("/menus")
    public ResponseEntity deleteMenu(@RequestParam final Long menuNo) {
        return ResponseEntity.ok(menuCRUDService.deleteMenu(menuNo));
    }
}
