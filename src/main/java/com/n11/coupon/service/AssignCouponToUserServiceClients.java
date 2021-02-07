package com.n11.coupon.service;

import com.n11.coupon.service.impl.CouponServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AssignCouponToUserServiceClients {

    private CouponServiceImpl couponService;

    public AssignCouponToUserServiceClients(CouponServiceImpl couponService) {
        this.couponService = couponService;
    }

    @Async
    public CompletableFuture<String> call(Long couponId) throws Exception {
        couponService.assignCouponToUser(couponId);
        return CompletableFuture.completedFuture("success");
    }
}
