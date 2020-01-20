package com.bamin.woorder.order.presentation;

import com.bamin.woorder.common.exception.ParameterConditionException;
import com.bamin.woorder.common.utils.TypeUtils;
import com.bamin.woorder.order.dto.OrderConditionalRequestDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class OrderConditionalRequestArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(OrderConditionalRequestDto.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String status = request.getParameter("status");
        String orderedBy = request.getParameter("orderedBy");
        String page = request.getParameter("page");
        String num = request.getParameter("num");

        if (page == null || num == null) {
            throw new ParameterConditionException("요청 쿼리 스트링을 확인해주세요.");
        }

        if (status == null && orderedBy == null) {
            return OrderConditionalRequestDto.builder()
                    .status("ALL")
                    .orderedBy(0L)
                    .num(TypeUtils.parseToInteger(num))
                    .page(TypeUtils.parseToInteger(page))
                    .build();
        }

        if (status == null) {
            return OrderConditionalRequestDto.builder()
                    .status("ALL")
                    .orderedBy(TypeUtils.parseToLong(orderedBy))
                    .num(TypeUtils.parseToInteger(num))
                    .page(TypeUtils.parseToInteger(page))
                    .build();
        }

        if (orderedBy == null) {
            return OrderConditionalRequestDto.builder()
                    .status(status)
                    .orderedBy(0L)
                    .num(TypeUtils.parseToInteger(num))
                    .page(TypeUtils.parseToInteger(page))
                    .build();
        }

        return OrderConditionalRequestDto.builder()
                .status(status)
                .orderedBy(TypeUtils.parseToLong(orderedBy))
                .num(TypeUtils.parseToInteger(num))
                .page(TypeUtils.parseToInteger(page))
                .build();
    }
}
