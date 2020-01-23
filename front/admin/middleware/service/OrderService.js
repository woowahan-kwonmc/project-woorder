import Api from "./Api";

const findConditionalOrders = (page, num, status, orderedBy) => (
  Api.base().get('orders', {
    params: {status, orderedBy, page, num}
  })
)

const findOrderByNo = (no) => (
  Api.base().get(`orders/${no}`)
)

export const orderService = {
  findConditionalOrders,
  findOrderByNo
}
