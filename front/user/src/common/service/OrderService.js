import Api from './Api'

const createOrders = (orders) => (
    // orders [menuNo, memberNo, quantity]
    Api.base().post('orders', orders)
)

export const orderService = {
    createOrders,
}