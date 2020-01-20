package com.bamin.woorder.coupon.presentation;

import com.bamin.woorder.common.utils.TypeUtils;
import com.bamin.woorder.coupon.dto.CouponPageReadRequestDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class CouponPageRequestArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String CHECK_QUERY_STRING_EXCEPTION_MESSAGE = "요청 쿼리 스트링을 확인해주세요.";

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(CouponPageReadRequestDto.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String pageParam = request.getParameter("page");
        String numParam = request.getParameter("num");
        String usableParam = request.getParameter("usable");
        String expiredParam = request.getParameter("expired");

        int page = TypeUtils.parseToIntegerOrThrow(pageParam, CHECK_QUERY_STRING_EXCEPTION_MESSAGE);
        int num = TypeUtils.parseToIntegerOrThrow(numParam, CHECK_QUERY_STRING_EXCEPTION_MESSAGE);

        if (usableParam == null && expiredParam == null) {
            return new CouponPageReadRequestDto(page, num);
        }

        boolean expired = TypeUtils.parseToBooleanOrThrow(expiredParam, CHECK_QUERY_STRING_EXCEPTION_MESSAGE);
        if (usableParam == null) {
            return new CouponPageReadRequestDto(page, num, expired);
        }

        boolean usable = TypeUtils.parseToBooleanOrThrow(usableParam, CHECK_QUERY_STRING_EXCEPTION_MESSAGE);
        return new CouponPageReadRequestDto(page, num, usable, expired);
    }
}
