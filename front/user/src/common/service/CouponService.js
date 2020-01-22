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

const downloadCoupon = (memberNo, couponTypeNo) => (
    Api.base().put('coupons/downloadMode', {
        memberNo,
        couponTypeNo
    })
)

export const couponService = {
    findMemberCoupons,
    enrollCoupon,
    downloadCoupon
}