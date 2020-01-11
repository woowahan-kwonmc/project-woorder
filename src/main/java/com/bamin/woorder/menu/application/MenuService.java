package com.bamin.woorder.menu.application;

import com.bamin.woorder.menu.domain.Menu;
import com.bamin.woorder.menu.domain.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu createMenu(String name, String price) {
        Menu menu = new Menu(name, price);
        return menuRepository.save(menu);
    }
}
