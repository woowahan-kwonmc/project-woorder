package com.bamin.woorder.menu.application;

import com.bamin.woorder.menu.domain.Menu;
import com.bamin.woorder.menu.domain.MenuRepository;
import com.bamin.woorder.menu.dto.MenuUpdateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Menu> selectPageMenus(final int page, final int num) {
        return menuRepository.findAll(PageRequest.of(page - 1, num))
                .stream()
                .collect(Collectors.toList());
    }

    public Menu findMenuById(final Long menuNo) {
        return menuRepository.findById(menuNo)
                .orElseThrow(() -> new MenuNotFoundException(menuNo));
    }

    @Transactional
    public Menu updateMenu(final Long menuNo, final MenuUpdateData menuUpdateData) {
        Menu savedMenu = findMenuById(menuNo);
        savedMenu.updateName(menuUpdateData.getUpdateName());
        savedMenu.updatePrice(menuUpdateData.getUpdatePrice());
        return savedMenu;
    }

    public void deleteMenu(final Menu menu) {
        menuRepository.delete(menu);
    }
}
