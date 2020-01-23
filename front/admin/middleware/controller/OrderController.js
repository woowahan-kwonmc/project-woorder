import {orderService} from "../service/OrderService";

const findConditionalOrders = async (page, num, status, orderedBy) => {
  const response = await orderService.findConditionalOrders(page, num, status, orderedBy)
  return response.data ? response.data : null
}

const findOrderByNo = async (no) => {
  const response = await orderService.findOrderByNo(no)
  return response.data ? response.data : null
}

export const orderController = {
  findConditionalOrders,
  findOrderByNo
}
