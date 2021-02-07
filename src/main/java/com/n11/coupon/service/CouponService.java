package com.n11.coupon.service;

import com.n11.coupon.dao.Coupon;
import com.n11.coupon.dto.CouponDTO;

import java.util.List;

public interface CouponService {

    List<CouponDTO> getCoupons();

    CouponDTO getCoupon(Long couponId) throws Exception;

    String createCoupon(CouponDTO createUpdateCouponDTO);

    String deleteCoupon(Long couponId) throws Exception;

    String updateCoupon(CouponDTO createUpdateCouponDTO) throws Exception;

    String assignCouponToUser(Long couponId) throws Exception;

}
