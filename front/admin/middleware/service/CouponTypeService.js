import Api from './Api'

const create = (couponType) => (
  Api.base().post('couponTypes', couponType)
)

const select = (page, num) => (
  Api.base().get('couponTypes/all', {
    params: {num, page}
  })
)

export const couponTypeService = {
  create,
  select,
}
