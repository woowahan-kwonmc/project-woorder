import {menuService} from "../service/MenuService";

const selectPageMenus = async (page, num) => {
    const response = await menuService.selectPageMenus(num, page)
    return response.data ? response.data : null
}

export const menuController = {
    selectPageMenus,
}