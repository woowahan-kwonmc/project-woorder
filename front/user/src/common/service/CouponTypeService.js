import Api from './Api'

const selectPageCouponTypes = (page, num) => (
    Api.base().get('couponTypes/download', {
        params: {page, num}
    })
)

export const couponTypeService = {
    selectPageCouponTypes,
}