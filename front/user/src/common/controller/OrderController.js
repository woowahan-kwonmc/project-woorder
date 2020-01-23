import {orderService} from "../service/OrderService";

const createOrder = async (orders) => {
    const response = await orderService.createOrders(orders)
    return response.data ? response.data : null
}

const selectConditionalOrders = async (status, orderedBy, page, num) => {
    const response = await orderService.selectConditionalOrders(status, orderedBy, page, num)
    return response.data ? response.data : null
}

export const orderController = {
    createOrder,
    selectConditionalOrders
}