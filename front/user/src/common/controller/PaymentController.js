import {paymentService} from "../service/PaymentService";

const createPayment = async (method, memberNo, ordersNo, couponsNo) => {
    const response = await paymentService.createPayment(method, memberNo, ordersNo, couponsNo)
    return response.data ? response.data : null
}

const selectPayments = async (memberNo, page, num) => {
    const response = await paymentService.selectPayments(memberNo, page, num)
    return response.data ? response.data : null
}

export const paymentController = {
    createPayment,
    selectPayments,
}