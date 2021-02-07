package com.n11.coupon.datahelper;

import com.n11.coupon.dao.Coupon;
import com.n11.coupon.dto.CouponDTO;

import java.util.ArrayList;
import java.util.List;

public class TestDTOFactory {

    public CouponDTO couponDTO(){
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setId(1L);
        couponDTO.setName("test data");
        couponDTO.setDiscountRate(0.3);
        couponDTO.setActive(true);
        couponDTO.setAssignCount(50);
        return couponDTO;
    }

    public List<CouponDTO> couponDTOs(){
        List<CouponDTO> couponDTOs = new ArrayList<>();
        couponDTOs.add(couponDTO());
        return couponDTOs;
    }
}
