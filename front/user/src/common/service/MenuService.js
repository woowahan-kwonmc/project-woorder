import Api from './Api'

const selectPageMenus = (num, page) => (
    Api.base().get('menus', {
        params: {num, page}
    })
)

export const menuService = {
    selectPageMenus,
}