package com.n11.coupon.api;

import com.n11.coupon.dto.CouponDTO;
import com.n11.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponApiController {

    @Autowired
    private CouponService couponService;

    @GetMapping(value = "/coupons", consumes = MediaType.ALL_VALUE)
    public List<CouponDTO> getCoupons() {
        return couponService.getCoupons();
    }

    @GetMapping(value = "/coupon", consumes = MediaType.ALL_VALUE)
    public CouponDTO getCoupon(Long couponId) throws Exception {
        return couponService.getCoupon(couponId);
    }

    @PostMapping(value = "/coupon/create", consumes = MediaType.ALL_VALUE)
    public String createCoupon(@RequestBody CouponDTO createUpdateCoupon) {
        return couponService.createCoupon(createUpdateCoupon);
    }

    @PostMapping(value = "/coupon/update")
    public String updateCoupon(@RequestBody CouponDTO createUpdateCoupon) throws Exception {
        return couponService.updateCoupon(createUpdateCoupon);
    }

    @PostMapping(value = "/coupon/delete", consumes = MediaType.ALL_VALUE)
    public String deleteCoupon(Long couponId) throws Exception {
        return couponService.deleteCoupon(couponId);
    }

    @PostMapping(value = "/coupon/assign", consumes = MediaType.ALL_VALUE)
    public String assignCouponToUser(Long couponId) throws Exception {
        return couponService.assignCouponToUser(couponId);
    }

}
