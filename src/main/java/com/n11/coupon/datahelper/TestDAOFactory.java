package com.n11.coupon.datahelper;

import com.n11.coupon.dao.Coupon;

import java.util.ArrayList;
import java.util.List;

public class TestDAOFactory {

    public Coupon coupon(){
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setName("test data");
        coupon.setDiscountRate(0.3);
        coupon.setActive(true);
        coupon.setAssignCount(50);
        return coupon;
    }

    public List<Coupon> coupons(){
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon());
        return coupons;
    }
}
