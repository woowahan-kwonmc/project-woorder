import {paymentService} from "../service/PaymentService";

const createPayment = async (method, memberNo, ordersNo, couponsNo) => {
    const response = await paymentService.createPayment(method, memberNo, ordersNo, couponsNo)
    return response.data ? response.data : null
}

export const paymentController = {
    createPayment,
}