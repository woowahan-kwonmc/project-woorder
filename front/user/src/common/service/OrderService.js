import Api from './Api'

const createOrders = (orders) => (
    // orders [menuNo, memberNo, quantity]
    Api.base().post('orders', orders)
)

const selectConditionalOrders = (status, orderedBy, page, num) => (
    Api.base().get('orders', {
        params: {status, orderedBy, page, num}
    })
)

export const orderService = {
    createOrders,
    selectConditionalOrders,
}