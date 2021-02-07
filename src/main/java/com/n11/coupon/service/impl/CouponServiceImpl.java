package com.n11.coupon.service.impl;

import com.n11.coupon.ResponseMessages;
import com.n11.coupon.dao.Coupon;
import com.n11.coupon.dto.CouponDTO;
import com.n11.coupon.repository.CouponRepository;
import com.n11.coupon.service.CouponService;
import com.n11.coupon.util.ModelMapperUtil;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    private CouponRepository couponRepository;

    private ModelMapperUtil modelMapperUtil = new ModelMapperUtil();

    @Override
    public List<CouponDTO> getCoupons(){

        return modelMapperUtil.convertCouponEntityListtoCouponDTOList(couponRepository.findAll());
    }

    @Override
    public CouponDTO getCoupon(Long couponId) throws Exception {
        if(couponRepository.findOneById(couponId) != null){
            return modelMapperUtil.convertCouponEntitytoCouponDTO(couponRepository.findOneById(couponId));
        } else{
            logger.info(ResponseMessages.COUPON_NOT_FOUND);
            return null;
        }
    }

    @Override
    public String createCoupon(CouponDTO createUpdateCouponDTO){
        Coupon coupon = modelMapperUtil.convertCouponDTOtoCouponEntity(createUpdateCouponDTO);
        Coupon savedCoupon = couponRepository.save(coupon);
        if(savedCoupon!=null) {
            logger.info(ResponseMessages.COUPON_CREATE_SUCCESS);
            return ResponseMessages.COUPON_CREATE_SUCCESS;
        }
        logger.info(ResponseMessages.COUPON_CREATE_FAILED);
        return ResponseMessages.COUPON_CREATE_FAILED;
    }

    @Override
    public String deleteCoupon(Long couponId) throws Exception {
        Coupon coupon;
        if(couponRepository.findOneById(couponId) != null) {
            coupon = couponRepository.findOneById(couponId);
            couponRepository.delete(coupon);
            logger.info(ResponseMessages.COUPON_DELETE_SUCCESS);
            return ResponseMessages.COUPON_DELETE_SUCCESS;
        } else{
            logger.info(ResponseMessages.COUPON_NOT_FOUND);
            return ResponseMessages.COUPON_NOT_FOUND;
        }
    }

    @Override
    public String updateCoupon(CouponDTO createUpdateCouponDTO) throws Exception {
        if(couponRepository.findOneById(createUpdateCouponDTO.getId()) != null) {
            Coupon coupon = couponRepository.findOneById(createUpdateCouponDTO.getId());
            coupon.setName(createUpdateCouponDTO.getName());
            coupon.setDiscountRate(createUpdateCouponDTO.getDiscountRate());
            if(createUpdateCouponDTO.getActive() == null)
                coupon.setActive(coupon.getActive());
            else
                coupon.setActive(createUpdateCouponDTO.getActive());
            coupon.setAssignCount(createUpdateCouponDTO.getAssignCount());
            Coupon savedCoupon = couponRepository.save(coupon);
            if(savedCoupon!=null) {
                logger.info(ResponseMessages.COUPON_UPDATE_SUCCESS);
                return ResponseMessages.COUPON_UPDATE_SUCCESS;
            }
            logger.info(ResponseMessages.COUPON_UPDATE_FAILED);
            return ResponseMessages.COUPON_UPDATE_FAILED;
        } else {
            logger.info(ResponseMessages.COUPON_NOT_FOUND);
            return ResponseMessages.COUPON_NOT_FOUND;
        }


    }


    @Override
    @Transactional
    public synchronized String assignCouponToUser(Long couponId) throws Exception {
        if(couponRepository.findOneById(couponId) != null) {
            Coupon coupon = couponRepository.findOneById(couponId);
            logger.info(coupon.getAssignCount().toString());
            if(!coupon.getActive()) {
                logger.info(ResponseMessages.COUPON_IS_NOT_ACTIVE);
                return ResponseMessages.COUPON_IS_NOT_ACTIVE;
            }
            else {
                coupon.setAssignCount(coupon.getAssignCount() - 1);
                if (coupon.getAssignCount() == 0)
                    coupon.setActive(false);
                Coupon savedCoupon = couponRepository.save(coupon);
                if(savedCoupon!=null) {
                    logger.info(ResponseMessages.COUPON_ASSIGNED_TO_USER_SUCCCESS);
                    return ResponseMessages.COUPON_ASSIGNED_TO_USER_SUCCCESS;
                }
                logger.info(ResponseMessages.COUPON_ASSIGNED_TO_USER_FAILED);
                return ResponseMessages.COUPON_ASSIGNED_TO_USER_FAILED;
            }
        } else {
            logger.info(ResponseMessages.COUPON_NOT_FOUND);
            return ResponseMessages.COUPON_NOT_FOUND;
        }
    }
}
