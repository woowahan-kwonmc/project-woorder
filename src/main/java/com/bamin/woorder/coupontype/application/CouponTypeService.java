package com.bamin.woorder.coupontype.application;

import com.bamin.woorder.coupontype.domain.CouponType;
import com.bamin.woorder.coupontype.domain.CouponTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponTypeService {

    private final CouponTypeRepository couponTypeRepository;

    @Autowired
    public CouponTypeService(final CouponTypeRepository couponTypeRepository) {
        this.couponTypeRepository = couponTypeRepository;
    }

    public CouponType createCouponType(final String couponTypeName,
                                       final String couponTypeCount,
                                       final String couponTypeDiscount,
                                       final String startTime,
                                       final String endTime) {
        CouponType couponType = CouponType.builder()
                .couponTypeName(couponTypeName)
                .couponTypeCount(couponTypeCount)
                .couponTypeDiscount(couponTypeDiscount)
                .startTime(startTime)
                .endTime(endTime)
                .build();
        return couponTypeRepository.save(couponType);
    }

    public List<CouponType> selectPageCouponTypes(final int page, final int num) {
        return couponTypeRepository.findAll(PageRequest.of(page - 1, num))
                .stream()
                .collect(Collectors.toList());
    }

    public CouponType selectCouponType(final Long couponTypeNo) {
        return couponTypeRepository.findById(couponTypeNo)
                .orElseThrow(() -> new CouponNotFoundException(couponTypeNo));
    }
}
