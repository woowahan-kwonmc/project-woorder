import {memberService} from "../service/MemberService";

const createMember = async (memberName) => {
    const response = await memberService.createMember(memberName)
    return response.data ? response.data : null
}

const readMember = async (memberName) => {
    const response = await memberService.readMember(memberName)
    return response.data ? response.data : null
}

export const memberController = {
    createMember,
    readMember,
}