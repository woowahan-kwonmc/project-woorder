import {orderService} from "../service/OrderService";

const createOrder = async (orders) => {
    const response = await orderService.createOrders(orders)
    return response.data ? response.data : null
}

export const orderController = {
    createOrder,
}