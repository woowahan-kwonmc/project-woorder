package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.common.presentation.PageRequestParams;
import com.bamin.woorder.common.utils.TypeUtils;
import com.bamin.woorder.menu.dto.MenuPageReadRequestDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class MenuPageRequestArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String CHECK_QUERY_STRING_EXCEPTION_MESSAGE = "요청 쿼리 스트링을 확인해주세요.";

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasMethodAnnotation(PageRequestParams.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String pageParam = request.getParameter("page");
        String numParam = request.getParameter("num");

        return new MenuPageReadRequestDto(
                TypeUtils.parseToIntegerOrThrow(pageParam, CHECK_QUERY_STRING_EXCEPTION_MESSAGE),
                TypeUtils.parseToIntegerOrThrow(numParam, CHECK_QUERY_STRING_EXCEPTION_MESSAGE)
        );
    }
}
