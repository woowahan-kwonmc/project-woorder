package com.bamin.woorder.member;

public interface MemberConstants {

    // base path
    String MEMBERS_BASE_SERVLET_PATH = "/members";
    String MEMBERS_LOGIN_SERVLET_PATH = "/members/login";

    // names
    String SUCCEED_CREATE_NAME = "woowaluffy";
    String ALREADY_SAVED_NAME = "already";
    String NOT_FOUND_NAME = "hello";
    String UPPER_BOUND_LENGTH_NAME = "012345678901234567891";
    String LOWER_BOUND_LENGTH_NAME = "";

    // response messages
    String SUCCEED_CREATE_RESPONSE_MESSAGE = "회원 가입 성공";
    String SUCCEED_LOGIN_MESSAGE = "로그인 성공";
    String FAILED_DUPLICATED_RESPONSE_MESSAGE = "중복된 이름을 사용할 수 없습니다.";
    String FAILED_LENGTH_RESPONSE_MESSAGE = "이름은 1자 이상, 20자 이하만 가능합니다.";
    String NOT_FOUND_MEMBER_RESPONSE_MESSAGE = "존재하지 않는 회원입니다.";

}
