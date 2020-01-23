import Api from "./Api";

const create = (hasCode, couponTypeNo, requestCounts) => {
  if (hasCode) {
    return _createCodeCoupon(couponTypeNo, requestCounts)
  }
  return _createDownloadCoupon(couponTypeNo, requestCounts)
}

const _createDownloadCoupon = (couponTypeNo, requestCounts) => (
  Api.base().post('coupons/downloadMode', {
    couponTypeNo,
    requestCounts
  })
)

const _createCodeCoupon = (couponTypeNo, requestCounts) => (
  Api.base().post('coupons/codeMode', {
    couponTypeNo,
    requestCounts
  })
)

const findCouponByNo = (couponNo) => (
  Api.base().get(`coupons/${couponNo}`)
)

const findCoupons = (page, num, usable, expired) => (
  Api.base().get('coupons', {
    params: {page, num, usable, expired}
  })
)

export const couponService = {
  create,
  findCouponByNo,
  findCoupons
}
