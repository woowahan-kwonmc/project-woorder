import Api from './Api'

const findMemberCoupons = (memberNo) => (
    Api.base().get(`/coupons/members/${memberNo}`)
)

export const couponService = {
    findMemberCoupons
}