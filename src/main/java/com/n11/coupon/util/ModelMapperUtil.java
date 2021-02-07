package com.n11.coupon.util;

import com.n11.coupon.dao.Coupon;
import com.n11.coupon.dto.CouponDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperUtil {

    public ModelMapperUtil(){

    }

    public Coupon convertCouponDTOtoCouponEntity(CouponDTO couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setName(couponDTO.getName());
        coupon.setDiscountRate(couponDTO.getDiscountRate());
        if (couponDTO.getActive() == null) {
            coupon.setActive(true);
        } else {
            coupon.setActive(couponDTO.getActive());
        }
        coupon.setAssignCount(couponDTO.getAssignCount());
        return coupon;

    }

    public CouponDTO convertCouponEntitytoCouponDTO(Coupon coupon){
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setId(coupon.getId());
        couponDTO.setName(coupon.getName());
        couponDTO.setDiscountRate(coupon.getDiscountRate());
        couponDTO.setActive(coupon.getActive());
        couponDTO.setAssignCount(coupon.getAssignCount());
        return couponDTO;

    }

    public List<CouponDTO> convertCouponEntityListtoCouponDTOList(List<Coupon> couponList){
        List<CouponDTO> couponDTOList = new ArrayList<CouponDTO>();

        for(Coupon coupon:couponList) {
            CouponDTO couponDTO = new CouponDTO();
            couponDTO.setId(coupon.getId());
            couponDTO.setName(coupon.getName());
            couponDTO.setDiscountRate(coupon.getDiscountRate());
            couponDTO.setActive(coupon.getActive());
            couponDTO.setAssignCount(coupon.getAssignCount());
            couponDTOList.add(couponDTO);
        }
        return couponDTOList;

    }
}
