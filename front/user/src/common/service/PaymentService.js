import Api from './Api'

const createPayment = (method, memberNo, ordersNo, couponsNo) => (
    Api.base().post('payments', {
        method,
        memberNo,
        ordersNo,
        couponsNo,
    })
)

export const paymentService = {
    createPayment,
}