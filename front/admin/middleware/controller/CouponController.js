import {couponService} from "../service/CouponService";

const create = async (hasCode, couponTypeNo, requestCounts) => {
  const response = await couponService.create(hasCode, couponTypeNo, requestCounts)
  return response.data ? response.data : null
}

const findCouponByNo = async (couponNo) => {
  const response = await couponService.findCouponByNo(couponNo)
  return response.data ? response.data : null
}

const findCoupons = async (page, num, usable, expired) => {
  const response = await couponService.findCoupons(page, num, usable, expired)
  return response.data ? response.data : null
}

export const couponController = {
  create,
  findCouponByNo,
  findCoupons
}
