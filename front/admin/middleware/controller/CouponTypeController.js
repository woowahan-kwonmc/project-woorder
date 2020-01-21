/**
 *
 name: null,
 count: null,
 discount: null,
 hasCode: false,
 startTime: null,
 endTime: null
 */
import {couponTypeService} from "../service/CouponTypeService";

const create = async (couponType) => {
  const response = await couponTypeService.create(couponType);
  return response.data
}

const select = async (page, num) => {
  const response = await couponTypeService.select(page, num);
  return response.data
}

export const couponTypeController = {
  create,
  select
}
