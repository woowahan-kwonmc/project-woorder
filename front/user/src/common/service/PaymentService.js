import Api from './Api'

const createPayment = (method, memberNo, ordersNo, couponsNo) => (
    Api.base().post('payments', {
        method,
        memberNo,
        ordersNo,
        couponsNo,
    })
)

const selectPayments = (memberNo, page, num) => (
    Api.base().get(`payments/members/${memberNo}`, {
        params: {page, num}
    })
)

export const paymentService = {
    createPayment,
    selectPayments
}