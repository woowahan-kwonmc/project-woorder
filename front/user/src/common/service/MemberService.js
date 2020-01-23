import Api from './Api'

const createMember = (memberName) => (
    Api.base().post('members', {
        name: memberName
    })
)

const readMember = (memberName) => (
    Api.base().post('members/login', {
        memberName
    })
)

export const memberService = {
    createMember,
    readMember
}