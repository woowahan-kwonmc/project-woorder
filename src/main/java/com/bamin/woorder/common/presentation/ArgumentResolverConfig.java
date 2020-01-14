package com.bamin.woorder.common.presentation;

import com.bamin.woorder.coupon.presentation.CouponPageRequestArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ArgumentResolverConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SelectPageRequestArgumentResolver());
        resolvers.add(new CouponPageRequestArgumentResolver());
    }
}
