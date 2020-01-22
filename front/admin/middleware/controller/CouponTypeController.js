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
  return response.data ? response.data : null
}

const select = async (page, num) => {
  const response = await couponTypeService.select(page, num);
  return response.data ? response.data : null
}

const findById = async (id) => {
  const response = await couponTypeService.findById(id);
  return response.data ? response.data : null
}

export const couponTypeController = {
  create,
  select,
  findById
}
