import {couponTypeService} from "../service/CouponTypeService";

const selectPageCouponTypes = async (page, num) => {
    const response = await couponTypeService.selectPageCouponTypes(page, num);
    return response.data ? response.data : null
}

export const couponTypeController = {
    selectPageCouponTypes
}