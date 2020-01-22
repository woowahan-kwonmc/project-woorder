import Api from './Api'

const create = (couponType) => (
  Api.base().post('couponTypes', couponType)
)

const select = (page, num) => (
  Api.base().get('couponTypes/all', {
    params: {num, page}
  })
)

const findById = (id) => (
  Api.base().get(`couponTypes/${id}`)
)

export const couponTypeService = {
  create,
  select,
  findById,
}
