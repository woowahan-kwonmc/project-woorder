package com.bamin.woorder.payment.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoMethod;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.coupon.application.CouponService;
import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.member.application.MemberService;
import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.order.application.OrderService;
import com.bamin.woorder.order.domain.Order;
import com.bamin.woorder.payment.domain.Payment;
import com.bamin.woorder.payment.domain.exception.InvalidOrdersException;
import com.bamin.woorder.payment.dto.PaymentCreateRequestDto;
import com.bamin.woorder.payment.dto.PaymentCreateResponseDto;
import com.bamin.woorder.payment.dto.PaymentDescResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentCRUDService {

    private PaymentService paymentService;
    private MemberService memberService;
    private OrderService orderService;
    private CouponService couponService;

    public PaymentCRUDService(final PaymentService paymentService,
                              final MemberService memberService,
                              final OrderService orderService,
                              final CouponService couponService) {
        this.paymentService = paymentService;
        this.memberService = memberService;
        this.orderService = orderService;
        this.couponService = couponService;
    }

    private static void checkEmptyOrders(final List<Long> orders) {
        if (orders.isEmpty()) {
            throw new InvalidOrdersException();
        }
    }

    @Transactional
    public ResponseDto createPayment(final PaymentCreateRequestDto requestDto) {
        checkEmptyOrders(requestDto.getOrdersNo());
        Member payMember = memberService.findMemberByNo(requestDto.getMemberNo());
        List<Order> payOrders = orderService.findAllOrdersByNo(requestDto.getOrdersNo());
        List<Coupon> payCoupons = couponService.findAllCouponsByNo(requestDto.getCouponsNo());
        payOrders.forEach(order -> order.updatePaymentInfo(payMember));
        Payment createdPayment = paymentService.createPayment(Payment.builder()
                .method(requestDto.getMethod())
                .payMember(payMember)
                .payOrders(payOrders)
                .payCoupons(payCoupons)
                .build());
        payCoupons.forEach(coupon -> coupon.useCouponForPaymentByMember(createdPayment, payMember));
        return ResponseDto.builder()
                .path("/api/v1/payments")
                .method(ResponseDtoMethod.POST)
                .message("결제 정보 생성 성공")
                .data(ResponseData.builder()
                        .insert("payment", mapToCreateResponseDto(createdPayment))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    public ResponseDto selectPagePayment(final Long memberNo, final int page, final int num) {
        List<Payment> payments = paymentService.selectPagePayment(memberNo, page, num);
        List<PaymentDescResponseDto> selectPayments = payments.stream()
                .map(this::mapToDescResponseDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .path("/api/v1/payments")
                .method(ResponseDtoMethod.GET)
                .message("결제 정보 조회 성공")
                .data(ResponseData.builder()
                        .insert("payments", selectPayments)
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    private PaymentCreateResponseDto mapToCreateResponseDto(final Payment payment) {
        return PaymentCreateResponseDto.builder()
                .no(payment.getPaymentNo())
                .price(payment.getPrice())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .createTime(payment.getCreateTime())
                .modifiedTime(payment.getModifiedTime())
                .build();
    }

    private PaymentDescResponseDto mapToDescResponseDto(final Payment payment) {
        return PaymentDescResponseDto.builder()
                .no(payment.getPaymentNo())
                .price(payment.getPrice())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .createTime(payment.getCreateTime())
                .build();
    }
}
