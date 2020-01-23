import {couponService} from "../service/CouponService";

const findMemberCoupons = async (memberNo) => {
    const response = await couponService.findMemberCoupons(memberNo)
    return response.data ? response.data : null
}

const enrollCodeCoupon = async (memberNo, code) => {
    const response = await couponService.enrollCoupon(memberNo, code)
    return response.data ? response.data : null
}

const downloadCoupon = async (memberNo, couponTypeNo) => {
    const response = await couponService.downloadCoupon(memberNo, couponTypeNo)
    return response.data ? response.data : null
}

export const couponController = {
    findMemberCoupons,
    enrollCodeCoupon,
    downloadCoupon
}
