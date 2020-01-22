import {couponService} from "../service/CouponService";

const findMemberCoupons = async (memberNo) => {
    const response = await couponService.findMemberCoupons(memberNo)
    return response.data ? response.data : null
}

export const couponController = {
    findMemberCoupons,
}
