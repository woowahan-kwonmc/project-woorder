import Api from './Api'

const findMemberCoupons = (memberNo) => (
    Api.base().get(`/coupons/members/${memberNo}`)
)

const enrollCoupon = (memberNo, code) => (
    Api.base().put('coupons/codeMode', {
        memberNo,
        code,
    })
)

export const couponService = {
    findMemberCoupons,
    enrollCoupon
}